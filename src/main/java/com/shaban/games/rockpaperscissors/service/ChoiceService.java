package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

@Service
class ChoiceService {

    @Autowired
    private ScannerHelperService scannerHelperService;

    void decideWhoWins(final Choice yourChoice, final Choice computerChoice) {
        // Outputs colored. Equal as yellow, computer wins as red & person wins as green
        if (yourChoice == computerChoice) {
            System.out.println("\033[33;1;2m Nobody wins!\033[0m");
        } else {
            boolean wins = yourChoice.beats(computerChoice);
            System.out.println(wins ?  "\033[32;1;2m You won\033[0m" : "\033[31;1m Computer won\033[0m");
        }

    }

    public Choice getComputerChoice() {

        Optional<Choice> computerChoice = Choice.get(new Random().nextInt(3));
        System.out.println("Computer's choice is "+ computerChoice.get());

        // This optional will always be present
        return computerChoice.get();
    }


    public Optional<Choice> getPersonChoice(Scanner in) {
        System.out.print("Choose your weapon Rock(0),Paper(1),Scissors(2) ; ");
        final int intChoice = scannerHelperService.getNextIntUntilNumberEntered(
                in, "Please enter one of these numbers. Choose your weapon Rock(0),Paper(1),Scissors(2) ; ");

        // When it is an invalid choice this method will return Optional empty
        return Choice.get(intChoice);
    }
}
