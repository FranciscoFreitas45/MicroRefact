package com.webapp.controllers;
 import com.webapp.models;
import com.webapp.payload.request.AddToTrainingSessionRequest;
import com.webapp.payload.request.CreateSessionRequest;
import com.webapp.payload.response.MessageResponse;
import com.webapp.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('USER')")
@RequestMapping("/session")
public class TrainingSessionController {

@Autowired
 private UserRepository userRepository;

@Autowired
 private WordRepository wordRepository;

@Autowired
 private TrainingSessionRepository trainingSessionRepository;

@Autowired
 private ResultRepository resultRepository;

@Autowired
 private AnswerRepository answerRepository;


@DeleteMapping("/delete")
public ResponseEntity<?> deleteTrainingSession(Long id){
    TrainingSession trainingSession = trainingSessionRepository.findById(id).orElseThrow();
    List<Result> results = trainingSession.getResults();
    for (Result r : results) {
        if (resultRepository.existsById(r.getId())) {
            List<Answer> answers = r.getAnswer();
            for (Answer a : answers) {
                if (answerRepository.existsById(a.getId()))
                    answerRepository.delete(a);
                else
                    return ResponseEntity.status(500).build();
            }
            resultRepository.delete(r);
        } else
            return ResponseEntity.status(500).build();
    }
    trainingSessionRepository.delete(trainingSession);
    return ResponseEntity.ok(new MessageResponse("Session deleted"));
}


@DeleteMapping("/remove")
public ResponseEntity<?> removeWordFromSession(Long wordId,Long sessionId){
    TrainingSession session = trainingSessionRepository.findById(sessionId).orElseThrow();
    Set<Word> words = session.getWords();
    words.removeIf(word -> word.getId().equals(wordId));
    session.setWords(words);
    trainingSessionRepository.save(session);
    return ResponseEntity.ok(new MessageResponse("Word removed from session"));
}


@GetMapping("/get-test")
public List<Word> getShuffledSession(Long id){
    TrainingSession session = trainingSessionRepository.findById(id).orElseThrow();
    // shuffle words order
    Set<Word> wordSet = session.getWords();
    int n = wordSet.size();
    List<Word> aList = new ArrayList<>(n);
    aList.addAll(wordSet);
    Collections.shuffle(aList);
    return aList;
}


@PostMapping("/add")
public ResponseEntity<?> addToTrainingSession(AddToTrainingSessionRequest request){
    TrainingSession trainingSession = trainingSessionRepository.findById(request.getSessionId()).orElseThrow();
    Word word = wordRepository.findById(request.getWordId()).orElseThrow();
    Set<Word> words = trainingSession.getWords();
    if (words.contains(word))
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Word already added"));
    words.add(word);
    trainingSession.setWords(words);
    trainingSessionRepository.save(trainingSession);
    return ResponseEntity.ok(new MessageResponse("Word added to session"));
}


@GetMapping("/get-all")
public List<TrainingSession> getTrainingSessions(Long id){
    User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id " + id));
    return trainingSessionRepository.findByUser(user);
}


@GetMapping("/get")
public Set<Word> getSession(Long id){
    TrainingSession session = trainingSessionRepository.findById(id).orElseThrow();
    return session.getWords();
}


@PostMapping("/create")
public ResponseEntity<?> createTrainingSession(CreateSessionRequest request){
    User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found with id " + request.getUserId()));
    TrainingSession trainingSession = new TrainingSession(user, request.getName());
    if (trainingSessionRepository.existsByNameAndUserId(request.getName(), request.getUserId()))
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Session with this name already exists"));
    trainingSessionRepository.save(trainingSession);
    return ResponseEntity.ok(new MessageResponse("Training session created"));
}


}