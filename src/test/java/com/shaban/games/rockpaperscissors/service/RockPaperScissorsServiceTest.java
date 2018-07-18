package com.shaban.games.rockpaperscissors.service;

import com.shaban.games.rockpaperscissors.domain.Choice;
import com.shaban.games.rockpaperscissors.domain.Computer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Scanner;

import static com.shaban.games.rockpaperscissors.domain.Choice.ROCK;
import static com.shaban.games.rockpaperscissors.domain.Choice.SCISSORS;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RockPaperScissorsServiceTest {

    @Mock
    private ChoiceService choiceService;

    /*@InjectMocks
    private RockPaperScissorsService rockPaperScissorsService;
*/
    @Test
    public void playRockPaperScissors_personShouldBeatComputer_whenROCKtoSCISSORS(){

        Scanner scanner = mock(Scanner.class);
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(ROCK.getCode());
        Computer computer = spy(new Computer());
        RockPaperScissorsService rockPaperScissorsService = mock(RockPaperScissorsService.class);

        when(computer.getChoice(any())).thenReturn(Optional.of(SCISSORS));

        rockPaperScissorsService.playRockPaperScissors(scanner);

        verify(choiceService, times(1)).decideWhoWins(ROCK, SCISSORS);

    }
}
