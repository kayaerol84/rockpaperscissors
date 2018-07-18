package com.shaban.games.rockpaperscissors;


import com.shaban.games.rockpaperscissors.service.RockPaperScissorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.stream.IntStream;

import static com.shaban.games.rockpaperscissors.domain.ScannerUtil.getNextInt;

@Component
public class RPSCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RockPaperScissorsService gameService;

    @Override
    public void run(String... args)  {

        final Scanner scanner = new Scanner(System.in);

        System.out.print("How many times do you want to play this game? ");
        final int nTimes = getNextInt(scanner, "Please enter a number. How many times do you want to play this game? ");

        IntStream.rangeClosed(1, nTimes)
                .forEach(e -> gameService.playRockPaperScissors(scanner) );

        System.out.println("Bye bye!");

    }

}