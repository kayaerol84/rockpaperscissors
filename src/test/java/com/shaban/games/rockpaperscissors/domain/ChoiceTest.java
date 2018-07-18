package com.shaban.games.rockpaperscissors.domain;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ChoiceTest {

    @Test
    public void get_shouldReturnROCK_when0Entered(){
        assertEquals(Choice.ROCK, Choice.get(0).get());
    }
    @Test
    public void get_shouldReturnPAPER_when0Entered(){
        assertEquals(Choice.PAPER, Choice.get(1).get());
    }
    @Test
    public void get_shouldReturnSCISSORS_when0Entered(){
        assertEquals(Choice.SCISSORS, Choice.get(2).get());
    }
    @Test
    public void get_shouldReturnOptionalEmpty_when4Entered(){
        assertEquals(Optional.empty(), Choice.get(4));
    }


}
