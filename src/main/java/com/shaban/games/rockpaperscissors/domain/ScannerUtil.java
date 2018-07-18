package com.shaban.games.rockpaperscissors.domain;

import java.util.Scanner;

public class ScannerUtil {

    public static int getNextInt(Scanner scanner, String input) {
        while (!scanner.hasNextInt()){
            System.out.print(input);
            scanner.next();
        }
        return scanner.nextInt();
    }
}
