package com.shaban.games.rockpaperscissors;


import com.shaban.games.rockpaperscissors.domain.Result;
import com.shaban.games.rockpaperscissors.service.RockPaperScissorsService;
import com.shaban.games.rockpaperscissors.service.ScannerHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;
import java.util.stream.IntStream;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class RockpaperscissorsApplication implements CommandLineRunner {


    @Autowired
    private ScannerHelperService scannerHelperService;

    @Autowired
    private RockPaperScissorsService rockPaperScissorsService;

    public static void main(String[] args) {
        SpringApplication.run(RockpaperscissorsApplication.class, args);
    }

    @Override
    public void run(String... args) {

        final Scanner scanner = new Scanner(System.in);

        System.out.print("How many times do you want to play this game? ");
        final int nTimes = scannerHelperService.getNextIntUntilNumberEntered(
                scanner, "Please enter a number. How many times do you want to play this game? ");

        IntStream
                .rangeClosed(1, nTimes)
                .forEach(e -> {
                    final Result result = rockPaperScissorsService.playGame(scanner);
                    if (result == Result.INVALID && e != nTimes)
                        System.out.println("Please enter one of these numbers. " );
                });

        // This is a blue output
        System.out.println("\n\033[34;1;2m Bye bye!!!\033[0m");
    }

}