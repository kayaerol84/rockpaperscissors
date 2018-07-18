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
        if (yourChoice == computerChoice) {
            System.out.println("Nobody wins, try again!");
        } else {
            boolean wins = yourChoice.beats(computerChoice);
            System.out.println(wins ? "You won" : "Computer won");
        }

    }

    public Choice getComputerChoice() {

        Optional<Choice> computerChoice = Choice.get(new Random().nextInt(3));
        System.out.println("Computer's choice is "+ computerChoice.get());
        return computerChoice.get();
    }


    public Optional<Choice> getPersonChoice(Scanner in) {
        System.out.print("Choose your weapon Rock(0),Paper(1),Scissors(2) ; ");
        final int intChoice = scannerHelperService.getNextIntUntilNumberEntered(
                in, "Please enter one of these numbers. Choose your weapon Rock(0),Paper(1),Scissors(2) ; ");
        final Optional<Choice> yourChoice = Choice.get(intChoice);

        return yourChoice;
    }
}
