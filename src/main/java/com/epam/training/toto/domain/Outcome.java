package com.epam.training.toto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Outcome {
    TEAM_ONE_WON("1"), TEAM_TWO_WON("2"), DRAW("X");

    private String value;

    public static Outcome getOutcome(String value) {
        return Arrays.stream(Outcome.values())
                .filter(outcome -> outcome.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }
}
