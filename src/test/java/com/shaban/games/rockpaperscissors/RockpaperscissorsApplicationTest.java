package com.shaban.games.rockpaperscissors;

import com.shaban.games.rockpaperscissors.service.RockPaperScissorsService;
import com.shaban.games.rockpaperscissors.service.ScannerHelperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RockpaperscissorsApplicationTest {

    @Mock
    private RockPaperScissorsService gameService;

    @Mock
    private ScannerHelperService scannerHelperService;

    @InjectMocks
    private RockpaperscissorsApplication application;

    @Test
    public void run_shouldRun3times_whenInputIs3() {

        when(scannerHelperService.getNextIntUntilNumberEntered(any(), any())).thenReturn(3);
        application.run();

        verify(gameService, times(3)).playGame(any());

    }


    @Test
    public void run_shouldNotRun_untilNumberEntered() {

        when(scannerHelperService.getNextIntUntilNumberEntered(any(), any())).thenReturn(2);
        application.run();

        verify(gameService, times(2)).playGame(any());

    }

}
