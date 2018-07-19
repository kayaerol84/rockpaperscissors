package com.shaban.games.rockpaperscissors.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ScannerHelperService {

    /**
     * Keep prints <askIfAlphaEntered> until a number entered
     * @param scanner
     * @param askIfAlphaEntered
     * @return int
     */
    public int getNextIntUntilNumberEntered(Scanner scanner, String askIfAlphaEntered) {

        while (!scanner.hasNextInt()){
            System.out.print(askIfAlphaEntered);
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Implemented in order to improve testability & readability
     * @param scanner
     * @return String
     */
    String getNext(Scanner scanner) {
        return scanner.next();
    }
}
