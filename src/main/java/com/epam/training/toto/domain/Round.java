package com.epam.training.toto.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Round {
    private Integer week;
    private Integer round;
    private LocalDate date;
    private List<Hit> hits;
    private List<Outcome> outcomes;

    public int getYear() {
        return date.getYear();
    }
}
