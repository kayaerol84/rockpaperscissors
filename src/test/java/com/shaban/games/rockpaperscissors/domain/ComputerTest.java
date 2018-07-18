package com.shaban.games.rockpaperscissors.domain;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Deprecated
public class ComputerTest {

    @Test
    public void getChoice_shouldReturnROCKPAPERorSCISSORS(){

        Player computer = new Computer();
        assertTrue(computer.getChoice(null).isPresent());
    }
}
