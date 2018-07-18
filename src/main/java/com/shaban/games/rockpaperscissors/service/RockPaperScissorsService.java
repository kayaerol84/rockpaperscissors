package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import com.shaban.games.rockpaperscissors.domain.Computer;
import com.shaban.games.rockpaperscissors.domain.Person;
import com.shaban.games.rockpaperscissors.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class RockPaperScissorsService {

    @Autowired
    private ChoiceService choiceService;

    public void playRockPaperScissors(Scanner scanner){
        final Player person = new Person();
        final Optional<Choice> personChoice = person.getChoice(scanner);

        if(personChoice.isPresent()) {
            System.out.println("Your choice is " + personChoice);
            final Player computer = new Computer();
            final Choice computerChoice = computer.getChoice(null).get();
            choiceService.decideWhoWins(personChoice.get(), computerChoice);
        } else  {
            System.out.print("Please enter one of these numbers. ");
        }
    }
}
