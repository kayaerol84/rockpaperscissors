package com.shaban.games.rockpaperscissors.domain;

import java.util.Scanner;

@Deprecated
public class ScannerUtil {

    public static int getNextInt(Scanner scanner, String askIfAlphaEntered) {
        while (!scanner.hasNextInt()){
            System.out.print(askIfAlphaEntered);
            scanner.next();
        }
        return scanner.nextInt();
    }
}
