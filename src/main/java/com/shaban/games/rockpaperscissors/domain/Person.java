package com.shaban.games.rockpaperscissors.domain;

import java.util.Optional;
import java.util.Scanner;

import static com.shaban.games.rockpaperscissors.domain.ScannerUtil.getNextInt;

public class Person implements Player {
    @Override
    public Optional<Choice> getChoice(Scanner in) {
        System.out.print("Choose your weapon Rock(0),Paper(1),Scissors(2) ; ");
        final int intChoice = getNextInt(in, "Please enter one of these numbers. Choose your weapon Rock(0),Paper(1),Scissors(2) ; ");
        final Optional<Choice> yourChoice = Choice.get(intChoice);

        return yourChoice;
    }
}
