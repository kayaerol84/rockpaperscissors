package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import com.shaban.games.rockpaperscissors.domain.Computer;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class ChoiceService {

    void decideWhoWins(final Choice yourChoice, final Choice computerChoice) {



        if (yourChoice == computerChoice) {
            System.out.println("Nobody wins, try again!");
        } else {
            boolean wins = yourChoice.beats(computerChoice);
            System.out.println(wins ? "You won" : "Computer won");
        }

    }
}
