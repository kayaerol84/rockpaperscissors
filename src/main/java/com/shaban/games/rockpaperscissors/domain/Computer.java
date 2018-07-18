package com.shaban.games.rockpaperscissors.domain;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

@Deprecated
public class Computer implements Player {

    @Override
    public Optional<Choice> getChoice(Scanner in) {

        Optional<Choice> computerChoice = Choice.get(new Random().nextInt(3));
        System.out.println("Computer's choice is "+ computerChoice.get());
        return computerChoice;
    }
}
