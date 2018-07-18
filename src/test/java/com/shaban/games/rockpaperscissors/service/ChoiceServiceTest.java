package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ChoiceServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @InjectMocks
    private ChoiceService choiceService;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void decideWhoWins_shouldPrintNobodyWins_whenChoicesAreSame(){

        choiceService.decideWhoWins(Choice.PAPER, Choice.PAPER);

        assertEquals("Nobody wins, try again!\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintYouWon_whenScissorsToPaper(){

        choiceService.decideWhoWins(Choice.SCISSORS, Choice.PAPER);

        assertEquals("You won\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintYouWon_whenPaperToRock(){

        choiceService.decideWhoWins(Choice.PAPER, Choice.ROCK);

        assertEquals("You won\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintYouWon_whenRockToScissors(){

        choiceService.decideWhoWins(Choice.ROCK, Choice.SCISSORS);

        assertEquals("You won\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintComputerWon_whenScissorsToRock(){

        choiceService.decideWhoWins(Choice.SCISSORS, Choice.ROCK);

        assertEquals("Computer won\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintComputerWon_whenRockToPaper(){

        choiceService.decideWhoWins(Choice.ROCK, Choice.PAPER);

        assertEquals("Computer won\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintComputerWon_whenPaperToScissors(){

        choiceService.decideWhoWins(Choice.PAPER, Choice.SCISSORS);

        assertEquals("Computer won\n", outContent.toString());
    }
}
