package com.shaban.games.rockpaperscissors.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScannerHelperServiceTest {

    @InjectMocks
    private ScannerHelperService scannerHelperService;

    @Test
    public void getNextIntUntilNumberEnteredUntilNumberEntered_shouldReturnIntInput_whenNumericValueEntered(){

        String data = "3";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);

        int nextInt = scannerHelperService.getNextIntUntilNumberEntered(scanner, "no need to ask again");
        System.setIn(stdin);

        assertEquals(3, nextInt);
    }

    @Test
    public void getNextIntUntilNumberEntered_shouldKeepTryToGetInteger_untilNumericValueEntered(){

        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.hasNextInt()).thenAnswer(new Answer<Boolean>() {
            int count = 0;
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock){
                count ++;
                // First input will be treated as alphanumeric, and the next one as numeric
                return count != 1;

            }
        });
        when(mockScanner.next()).thenReturn("3");
        when(mockScanner.nextInt()).thenReturn(3);

        int nextInt = scannerHelperService.getNextIntUntilNumberEntered(mockScanner, "ask number again");

        assertEquals(3, nextInt);
    }

    @Test
    public void getNext_returnsString(){
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.next()).thenReturn("input");
        assertEquals("input", scannerHelperService.getNext(mockScanner));
    }
}
