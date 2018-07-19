package com.shaban.games.rockpaperscissors;

import com.shaban.games.rockpaperscissors.service.RockPaperScissorsService;
import com.shaban.games.rockpaperscissors.service.ScannerHelperService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.rule.OutputCapture;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RockpaperscissorsApplicationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("How many times do you want to play this game? ".getBytes());

    private final PrintStream originalOut = System.out;

    @Mock
    private RockPaperScissorsService gameService;

    @Mock
    private ScannerHelperService scannerHelperService;

    @InjectMocks
    private RockpaperscissorsApplication application;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(inContent);
    }

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void run_shouldRun3times_whenInputIs3() {

        String data = "3";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        when(scannerHelperService.getNextIntUntilNumberEntered(any(), any())).thenReturn(3);
        application.run();

        Scanner scanner = new Scanner(System.in);
        System.setIn(stdin);

        verify(gameService, times(3)).playGame(any());

    }


    @Test
    public void run_shouldNotRun_untilNumberEntered() {

        String data = "E";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        when(scannerHelperService.getNextIntUntilNumberEntered(any(), any())).thenReturn(2);
        application.run();

        Scanner scanner = new Scanner(System.in);
        System.setIn(stdin);

        verify(gameService, times(2)).playGame(any());

    }

}
