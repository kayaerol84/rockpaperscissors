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
import static com.shaban.games.rockpaperscissors.domain.Result.EQUAL;
import static com.shaban.games.rockpaperscissors.domain.Result.PERSON_WON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChoiceServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private ScannerHelperService scannerHelperService;

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

        assertNobodyWins(Choice.PAPER, Choice.PAPER);

        assertEquals("\n\033[33;1;2m Nobody wins!\033[0m\n", outContent.toString());

        assertNobodyWins(Choice.ROCK, Choice.ROCK);

        assertNobodyWins(Choice.SCISSORS, Choice.SCISSORS);

    }

    @Test
    public void decideWhoWins_shouldPrintYouWon_whenScissorsToPaper(){

        assertPersonWon(Choice.SCISSORS, Choice.PAPER);

        assertEquals("\n\033[32;1;2m You won\033[0m\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintYouWon_whenPaperToRock(){

        assertPersonWon(PAPER, ROCK);

        assertEquals("\n\033[32;1;2m You won\033[0m\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintYouWon_whenRockToScissors(){

        assertPersonWon(ROCK, SCISSORS);

        assertEquals("\n\033[32;1;2m You won\033[0m\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintComputerWon_whenScissorsToRock(){

        assertComputerWon(SCISSORS, ROCK);

        assertEquals("\n\033[31;1m Computer won\033[0m\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintComputerWon_whenRockToPaper(){

        assertComputerWon(ROCK, PAPER);

        assertEquals("\n\033[31;1m Computer won\033[0m\n", outContent.toString());
    }

    @Test
    public void decideWhoWins_shouldPrintComputerWon_whenPaperToScissors(){

        assertComputerWon(PAPER, SCISSORS);

        assertEquals("\n\033[31;1m Computer won\033[0m\n", outContent.toString());
    }

    @Test
    public void getComputerChoice_shouldAlwaysReturnROCKPAPERorSCISSORS(){

        Choice computerChoice = choiceService.getComputerChoice();
        assertNotNull(computerChoice);
    }

    @Test
    public void getPersonChoice_shouldReturnROCK_when0Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scannerHelperService.getNext(any())).thenReturn(ROCK.getCode());

        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(ROCK, choice.get());
    }

    @Test
    public void getPersonChoice_shouldReturnPAPER_when1Entered(){

        Scanner scanner = mock(Scanner.class);

        when(scannerHelperService.getNext(any())).thenReturn(PAPER.getCode());
        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(PAPER, choice.get());
    }

    @Test
    public void getPersonChoice_shouldReturnSCISSORS_when2Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scannerHelperService.getNext(any())).thenReturn(SCISSORS.getCode());

        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(SCISSORS, choice.get());
    }

    @Test
    public void getPersonChoice_shouldReturnEmpty_when3Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scannerHelperService.getNext(any())).thenReturn("3");

        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertFalse(choice.isPresent());
    }

    @Test
    public void getPersonChoice_shouldReturnEmpty_whenAlphabeticEntered(){

        Scanner scanner = mock(Scanner.class);
        when(scannerHelperService.getNext(any())).thenReturn("E");

        Optional<Choice> choice = choiceService.getPersonChoice(scanner);
        assertFalse(choice.isPresent());
    }

    private void assertPersonWon(Choice personChoice, Choice computerChoice) {
        Result result = choiceService.decideWhoWins(personChoice, computerChoice);
        assertEquals(PERSON_WON, result);
    }

    private void assertNobodyWins(Choice personChoice, Choice computerChoice) {
        Result result = choiceService.decideWhoWins(personChoice, computerChoice);
        assertEquals(EQUAL, result);
    }

    private void assertComputerWon(Choice personChoice, Choice computerChoice) {
        Result result = choiceService.decideWhoWins(personChoice, computerChoice);
        assertEquals(COMPUTER_WON, result);
    }
}
