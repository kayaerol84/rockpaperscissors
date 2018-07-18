package com.shaban.games.rockpaperscissors.domain;

import org.junit.Test;

import java.util.Optional;
import java.util.Scanner;

import static com.shaban.games.rockpaperscissors.domain.Choice.PAPER;
import static com.shaban.games.rockpaperscissors.domain.Choice.ROCK;
import static com.shaban.games.rockpaperscissors.domain.Choice.SCISSORS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonTest {
    @Test
    public void getChoice_shouldReturnROCK_when0Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(ROCK.getCode());

        Player person = new Person();
        Optional<Choice> choice = person.getChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(ROCK, choice.get());
    }

    @Test
    public void getChoice_shouldReturnPAPER_when1Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(PAPER.getCode());

        Player person = new Person();
        Optional<Choice> choice = person.getChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(PAPER, choice.get());
    }

    @Test
    public void getChoice_shouldReturnSCISSORS_when2Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(SCISSORS.getCode());

        Player person = new Person();
        Optional<Choice> choice = person.getChoice(scanner);
        assertTrue(choice.isPresent());
        assertEquals(SCISSORS, choice.get());
    }

    @Test
    public void getChoice_shouldReturnEmpty_when3Entered(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(3);

        Player person = new Person();
        Optional<Choice> choice = person.getChoice(scanner);
        assertFalse(choice.isPresent());
    }

}
