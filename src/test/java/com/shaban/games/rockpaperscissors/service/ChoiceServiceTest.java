package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import com.shaban.games.rockpaperscissors.domain.Computer;
import com.shaban.games.rockpaperscissors.domain.Person;
import com.shaban.games.rockpaperscissors.domain.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import static com.shaban.games.rockpaperscissors.domain.Choice.PAPER;
import static com.shaban.games.rockpaperscissors.domain.Choice.ROCK;
import static com.shaban.games.rockpaperscissors.domain.Choice.SCISSORS;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChoiceServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @InjectMocks
    private ChoiceService choiceService;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restore() {
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

    @Test
    public void getComputerChoice_shouldAlwaysReturnROCKPAPERorSCISSORS(){

        Choice computerChoice = choiceService.getComputerChoice();
        assertNotNull(computerChoice);
    }

    @Test
    public void getChoice_shouldReturnROCK_when0Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(ROCK.getCode());

        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(ROCK, choice.get());
    }

    @Test
    public void getChoice_shouldReturnPAPER_when1Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(PAPER.getCode());

        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(PAPER, choice.get());
    }

    @Test
    public void getChoice_shouldReturnSCISSORS_when2Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(SCISSORS.getCode());

        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(SCISSORS, choice.get());
    }

    @Test
    public void getChoice_shouldReturnEmpty_when3Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(3);

        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertFalse(choice.isPresent());
    }
}
