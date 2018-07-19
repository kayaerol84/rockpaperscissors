package com.shaban.games.rockpaperscissors.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public enum Choice {

    ROCK("0"){

        @Override
        public boolean beats(final Choice other){
            return other == SCISSORS;

        }
    },
    PAPER("1"){

        @Override
        public boolean beats(final Choice other){
            return other == ROCK;

        }
    },
    SCISSORS ("2") {

        @Override
        public boolean beats(final Choice other){
            return other == PAPER;

        }
    };

    private static final Map<String, Choice> lookup = new HashMap<>();

    static {
        Arrays.stream(Choice.values())
                .forEach(choice -> lookup.put(choice.getCode(), choice));
    }

    protected String code;

    Choice(final String code) {
        this.code = code;
    }

    public abstract boolean beats(Choice other);

    public static Optional<Choice> get(final String code) {
        return Optional.ofNullable(lookup.get(code));
    }

    public String getCode() {
        return code;
    }
}

