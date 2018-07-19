package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import com.shaban.games.rockpaperscissors.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import static com.shaban.games.rockpaperscissors.domain.Result.COMPUTER_WON;
import static com.shaban.games.rockpaperscissors.domain.Result.EQUAL;
import static com.shaban.games.rockpaperscissors.domain.Result.PERSON_WON;

@Service
class ChoiceService {

    @Autowired
    private ScannerHelperService scannerHelperService;

    /**
     * Based on the choices, returns who the winner is
     *
     * @param yourChoice
     * @param computerChoice
     * @return Result
     */
    Result decideWhoWins(final Choice yourChoice, final Choice computerChoice) {
        // Outputs colored. Equal as yellow, computer wins as red & person wins as green
        if (yourChoice == computerChoice) {
            System.out.println("\n\033[33;1;2m Nobody wins!\033[0m");
            return EQUAL;
        } else {
            boolean wins = yourChoice.beats(computerChoice);
            System.out.println(wins ?  "\n\033[32;1;2m You won\033[0m" : "\n\033[31;1m Computer won\033[0m");
            return wins ? PERSON_WON : COMPUTER_WON;
        }

    }

    /**
     * returns computer's random choice
     * @return Choice
     */
    Choice getComputerChoice() {

        Optional<Choice> computerChoice = Choice.get(String.valueOf(new Random().nextInt(3)));
        System.out.println("Computer's choice is "+ computerChoice.get());

        // This optional will always be present
        return computerChoice.get();
    }


    /**
     * returns person's choice. if an invalid choice entered, returns Optional.empty()
     * @param in
     * @return Optional<Choice>
     */
    Optional<Choice> getPersonChoice(Scanner in) {
        System.out.print("Choose your weapon Rock(0),Paper(1),Scissors(2) ; ");
        final String intChoice = scannerHelperService.getNext(in);

        return Choice.get(intChoice);
    }
}
