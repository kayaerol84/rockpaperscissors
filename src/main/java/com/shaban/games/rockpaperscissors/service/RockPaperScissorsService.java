package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import com.shaban.games.rockpaperscissors.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

import static com.shaban.games.rockpaperscissors.domain.Result.INVALID;

@Service
public class RockPaperScissorsService {

    @Autowired
    private ChoiceService choiceService;

    /**
     * returns the result of a single game and prints out who won
     * @param scanner
     * @return Result
     */
    public Result playGame(Scanner scanner){

        final Optional<Choice> personChoice = choiceService.getPersonChoice(scanner);

        if(personChoice.isPresent()) {
            System.out.println("Your choice is " + personChoice.get());
            final Choice computerChoice = choiceService.getComputerChoice();

            return choiceService.decideWhoWins(personChoice.get(), computerChoice);
        } else  {
            return INVALID;
        }
    }
}
