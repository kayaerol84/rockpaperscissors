package com.shaban.games.rockpaperscissors.service;

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

        rockPaperScissorsService.playGame(scanner);

        verify(choiceService, times(1)).decideWhoWins(ROCK, SCISSORS);
    }

    @Test
    public void playGame_personShouldBeatComputer_whenSCISSORStoPAPER(){

        when(choiceService.getPersonChoice(any())).thenReturn(Optional.of(SCISSORS));
        when(choiceService.getComputerChoice()).thenReturn(PAPER);

        rockPaperScissorsService.playGame(scanner);

        verify(choiceService, times(1)).decideWhoWins( SCISSORS, PAPER);
    }

    @Test
    public void playGame_personShouldBeatComputer_whenPAPERtoROCK(){

        when(choiceService.getPersonChoice(any())).thenReturn(Optional.of(PAPER));
        when(choiceService.getComputerChoice()).thenReturn(ROCK);

        rockPaperScissorsService.playGame(scanner);

        verify(choiceService, times(1)).decideWhoWins( PAPER, ROCK);
    }

    @Test
    public void playGame_shouldPrintMessageAgain_whenPersonChoiceIsInvalid() {
        when(choiceService.getPersonChoice(any())).thenReturn(Optional.empty());

        rockPaperScissorsService.playGame(scanner);

        verify(choiceService, times(0)).decideWhoWins( any(), any());

        assertEquals("Please enter one of these numbers. ", outContent.toString());
    }
}
