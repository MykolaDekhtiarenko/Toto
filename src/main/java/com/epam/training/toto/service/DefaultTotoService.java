package com.epam.training.toto.service;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.Round;
import com.epam.training.toto.reader.RoundReader;
import com.epam.training.toto.vo.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultTotoService implements TotoService {

    @Autowired
    private RoundReader roundReader;

    @Autowired
    private DistributionService distributionService;

    @Value("${file.path}")
    private String path;

    public Collection<Round> findAllRounds() {
        return roundReader.readRoundRecords(path);
    }

    public Collection<Distribution> getDistributions() {
        return findAllRounds().stream()
                .map(distributionService::calculateDistribution)
                .collect(Collectors.toList());
    }

    public Integer getLargestPrize() {
        return findAllRounds().stream()
                .flatMap(round -> round.getHits().stream())
                .mapToInt(Hit::getPrice)
                .max()
                .orElse(0) / 100;
    }

    @Override
    public Hit getHitByDateAndOutcomes(LocalDate date, List<Outcome> outcomes) {
        Round round = findRoundByDate(date);
        int result = calculateGuessedResults(round.getOutcomes(), outcomes);
        return round.getHits().stream()
                .filter(hit -> hit.getHits().equals(result))
                .findFirst().orElse(new Hit(result, 0));
    }

    @Override
    public Round findRoundByDate(LocalDate localDate) {
        return findAllRounds().stream()
                .filter(round -> round.getDate().isEqual(localDate))
                .findFirst()
                .orElse(null);
    }

    private int calculateGuessedResults(List<Outcome> actual, List<Outcome> guessed) {
        int score = 0;
        for (int i = 0; i < actual.size(); i++) {
            if (actual.get(i) == guessed.get(i)) {
                score++;
            }
        }
        return score;
    }

}
