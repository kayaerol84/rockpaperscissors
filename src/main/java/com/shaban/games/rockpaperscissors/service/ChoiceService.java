package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import org.springframework.stereotype.Service;

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
