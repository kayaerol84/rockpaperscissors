package com.shaban.games.rockpaperscissors;


import com.shaban.games.rockpaperscissors.service.RockPaperScissorsService;
import com.shaban.games.rockpaperscissors.service.ScannerHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.stream.IntStream;

@Component
public class RPSCommandLineRunner implements CommandLineRunner {


    @Autowired
    private ScannerHelperService scannerHelperService;

    @Autowired
    private RockPaperScissorsService rockPaperScissorsService;

    @Override
    public void run(String... args)  {

        final Scanner scanner = new Scanner(System.in);

        System.out.print("How many times do you want to play this game? ");
        final int nTimes = scannerHelperService.getNextIntUntilNumberEntered(
                scanner, "Please enter a number. How many times do you want to play this game? ");

        IntStream.rangeClosed(1, nTimes)
                .forEach(e -> rockPaperScissorsService.playGame(scanner) );

        System.out.println("\n\033[34;1;2m Bye bye!!!\033[0m");

    }

}