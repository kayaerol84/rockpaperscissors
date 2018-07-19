package com.shaban.games.rockpaperscissors.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public enum Choice {

    ROCK(0){

        @Override
        public boolean beats(final Choice other){
            return other == SCISSORS;

        }
    },
    PAPER(1){

        @Override
        public boolean beats(final Choice other){
            return other == ROCK;

        }
    },
    SCISSORS (2) {

        @Override
        public boolean beats(final Choice other){
            return other == PAPER;

        }
    };

    private static final Map<Integer, Choice> lookup = new HashMap<>();

    static {
        Arrays.stream(Choice.values())
                .forEach(choice -> lookup.put(choice.getCode(), choice));
    }

    protected int code;

    Choice(final int code) {
        this.code = code;
    }

    public abstract boolean beats(Choice other);

    public static Optional<Choice> get(final Integer code) {
        return Optional.ofNullable(lookup.get(code));
    }

    public int getCode() {
        return code;
    }
}

