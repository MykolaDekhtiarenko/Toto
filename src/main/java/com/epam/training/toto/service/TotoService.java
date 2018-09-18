package com.epam.training.toto.service;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.Round;
import com.epam.training.toto.vo.Distribution;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TotoService {

    Collection<Round> findAllRounds();

    Collection<Distribution> getDistributions();

    Integer getLargestPrize();

    Hit getHitByDateAndOutcomes(LocalDate date, List<Outcome> outcomes);

    Round findRoundByDate(LocalDate localDate);
}
