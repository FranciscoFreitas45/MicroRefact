package com.webapp.controllers;
 import com.webapp.models;
import com.webapp.payload.request.CreateWordRequest;
import com.webapp.payload.request.WordRequest;
import com.webapp.payload.response.MessageResponse;
import com.webapp.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('USER')")
public class WordController {

@Autowired
 private  WordRepository wordRepository;

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  AnswerRepository answerRepository;

@Autowired
 private  ResultRepository resultRepository;

@Autowired
 private  TrainingSessionRepository trainingSessionRepository;


@GetMapping("/get-all")
public List<Word> getAllWord(Long id){
    User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
    return wordRepository.findByUser(user);
}


@GetMapping("/get")
public Word getWord(Long id){
    return wordRepository.findById(id).orElseThrow();
}


@PostMapping("/create")
public ResponseEntity<?> createWord(CreateWordRequest createRequest){
    if (wordRepository.existsByOriginalAndUserId(createRequest.getOriginal(), createRequest.getUserId())) {
        return ResponseEntity.badRequest().body(new MessageResponse("Error: word is already exists!"));
    }
    User user = userRepository.findById(createRequest.getUserId()).orElseThrow();
    Word word = new Word(createRequest.getOriginal(), createRequest.getTranslation(), user);
    wordRepository.save(word);
    return ResponseEntity.ok(new MessageResponse("Word created"));
}


@DeleteMapping("/delete")
public ResponseEntity<?> deleteWord(Long id){
    Word word = wordRepository.findById(id).orElseThrow(// lol
    () -> new UsernameNotFoundException("Word not found"));
    List<TrainingSession> session = trainingSessionRepository.findAll();
    // Remove word from sessions
    for (TrainingSession trainingSession : session) {
        if (trainingSession.containsWord(id)) {
            trainingSession.removeWord(id);
            trainingSessionRepository.save(trainingSession);
        }
    }
    List<Result> results = resultRepository.findAll();
    List<Answer> answers = answerRepository.findAll();
    // Remove answers with this word
    for (Answer answer : answers) {
        if (answer.containsWord(id)) {
            // Remove results with this answers
            for (Result result : results) {
                if (result.containsAnswer(answer.getId()))
                    resultRepository.delete(result);
            }
            answerRepository.delete(answer);
        }
    }
    wordRepository.delete(word);
    return ResponseEntity.ok(new MessageResponse("Word deleted"));
}


@PostMapping("/update")
public ResponseEntity<?> updateWord(CreateWordRequest updateRequest){
    if (!wordRepository.existsByOriginal(updateRequest.getOriginal())) {
        return ResponseEntity.badRequest().body(new MessageResponse("Error: word not found"));
    }
    Word word = wordRepository.findByOriginal(updateRequest.getOriginal()).orElseThrow();
    word.setTranslation(updateRequest.getTranslation());
    wordRepository.save(word);
    return ResponseEntity.ok(new MessageResponse("Word updated"));
}


}