package com.shaban.games.rockpaperscissors.domain;

import java.util.Optional;
import java.util.Scanner;

public interface Player {

    Optional<Choice> getChoice(Scanner in);
}
