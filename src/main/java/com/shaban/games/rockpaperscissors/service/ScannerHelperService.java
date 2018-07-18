package com.shaban.games.rockpaperscissors.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ScannerHelperService {

    public int getNextIntUntilNumberEntered(Scanner scanner, String askIfAlphaEntered) {
        while (!scanner.hasNextInt()){
            System.out.print(askIfAlphaEntered);
            scanner.next();
        }
        return scanner.nextInt();
    }
}
