package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class RockPaperScissorsService {

    @Autowired
    private ChoiceService choiceService;

    public void playRockPaperScissors(Scanner scanner){

        final Optional<Choice> personChoice = choiceService.getPersonChoice(scanner);

        if(personChoice.isPresent()) {
            System.out.println("Your choice is " + personChoice);
            final Choice computerChoice = choiceService.getComputerChoice();

            choiceService.decideWhoWins(personChoice.get(), computerChoice);
        } else  {
            System.out.print("Please enter one of these numbers. ");
        }
    }
}
