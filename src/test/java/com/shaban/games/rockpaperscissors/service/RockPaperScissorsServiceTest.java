package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import com.shaban.games.rockpaperscissors.domain.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import static com.shaban.games.rockpaperscissors.domain.Choice.PAPER;
import static com.shaban.games.rockpaperscissors.domain.Choice.ROCK;
import static com.shaban.games.rockpaperscissors.domain.Choice.SCISSORS;
import static com.shaban.games.rockpaperscissors.domain.Result.COMPUTER_WON;
import static com.shaban.games.rockpaperscissors.domain.Result.INVALID;
import static com.shaban.games.rockpaperscissors.domain.Result.PERSON_WON;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RockPaperScissorsServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private ChoiceService choiceService;
    private Scanner scanner;

    @Before
    public void setup(){
        scanner = mock(Scanner.class);
        System.setOut(new PrintStream(outContent));
    }
    @After
    public void restore() {
        System.setOut(originalOut);
    }

    @InjectMocks
    private RockPaperScissorsService rockPaperScissorsService;

    @Test
    public void playGame_personShouldBeatComputer_whenROCKtoSCISSORS(){

        when(choiceService.getPersonChoice(any())).thenReturn(Optional.of(ROCK));
        when(choiceService.getComputerChoice()).thenReturn(SCISSORS);

        personShouldWon(ROCK, SCISSORS);
    }


    @Test
    public void playGame_personShouldBeatComputer_whenSCISSORStoPAPER(){

        when(choiceService.getPersonChoice(any())).thenReturn(Optional.of(SCISSORS));
        when(choiceService.getComputerChoice()).thenReturn(PAPER);

        personShouldWon(SCISSORS, PAPER);
    }

    @Test
    public void playGame_personShouldBeatComputer_whenPAPERtoROCK(){

        when(choiceService.getPersonChoice(any())).thenReturn(Optional.of(PAPER));
        when(choiceService.getComputerChoice()).thenReturn(ROCK);

        personShouldWon(PAPER, ROCK);
    }

    @Test
    public void playGame_computerShouldBeatPerson_whenROCKtoPAPER(){

        when(choiceService.getPersonChoice(any())).thenReturn(Optional.of(ROCK));
        when(choiceService.getComputerChoice()).thenReturn(PAPER);

        computerShouldWon(ROCK, PAPER);
    }

    @Test
    public void playGame_shouldPrintMessageAgain_whenPersonChoiceIsInvalid() {
        when(choiceService.getPersonChoice(any())).thenReturn(Optional.empty());

        Result result = rockPaperScissorsService.playGame(scanner);

        verify(choiceService, times(0)).decideWhoWins( any(), any());

        assertEquals(INVALID, result);
    }

    private void personShouldWon(Choice personChoice, Choice computerChoice) {
        when(choiceService.decideWhoWins(personChoice, computerChoice)).thenReturn(PERSON_WON);
        Result result = rockPaperScissorsService.playGame(scanner);
        assertEquals(PERSON_WON, result);
    }

    private void computerShouldWon(Choice personChoice, Choice computerChoice) {
        when(choiceService.decideWhoWins(personChoice, computerChoice)).thenReturn(COMPUTER_WON);
        Result result = rockPaperScissorsService.playGame(scanner);
        assertEquals(COMPUTER_WON, result);
    }

}
