package br.com.wtag.lottery.controller;
 import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.wtag.lottery.model.entity.Bets;
import br.com.wtag.lottery.model.entity.RandomNumbers;
import br.com.wtag.lottery.model.input.BetsInput;
import br.com.wtag.lottery.model.output.BetsOutput;
import br.com.wtag.lottery.model.repository.BetsRepository;
import br.com.wtag.lottery.model.repository.RandomNumbersRepository;
import br.com.wtag.lottery.service.LotteryService;
import br.com.wtag.lottery.Interface.BetsRepository;
import br.com.wtag.lottery.DTO.BetsInput;
@RestController
@RequestMapping("/bets")
public class BetsController {

@Autowired
 private  BetsRepository betsRepository;

@Autowired
 private  RandomNumbersRepository randomNumbersRepository;

@Autowired
 private  ModelMapper modelMapper;

 private  Integer ONE;

 private  Integer SIX;

 private  Integer SIXTY;

 private  Integer ZERO;


@GetMapping("/history/{email}")
public List<BetsOutput> read(String email){
    return betsRepository.findByEmailOrderByRegisteredAsc(email).stream().map(bets -> modelMapper.map(bets, BetsOutput.class)).collect(Collectors.toList());
}


@PostMapping
public BetsOutput create(BetsInput betsInput){
    Bets bets = betsRepository.save(betsInput.toBets());
    RandomNumbers randomNumbers;
    List<RandomNumbers> randomNumbersList = new ArrayList<>();
    for (Integer x = ZERO; x < SIX; x++) {
        randomNumbers = new RandomNumbers();
        randomNumbers.setRandomNumber(LotteryService.getRandomNumber(ONE, SIXTY, randomNumbersList));
        randomNumbers.setBets(bets);
        randomNumbersRepository.save(randomNumbers);
        randomNumbersList.add(randomNumbers);
    }
    bets.setRandomNumbers(randomNumbersList);
    modelMapper.typeMap(Bets.class, BetsOutput.class).addMappings(mapper -> {
        mapper.map(Bets::getRandomNumbers, BetsOutput::setRandomNumbers);
    });
    return modelMapper.map(bets, BetsOutput.class);
}


}