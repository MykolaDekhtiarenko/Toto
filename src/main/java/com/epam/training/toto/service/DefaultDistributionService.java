package com.epam.training.toto.service;

import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.Round;
import com.epam.training.toto.vo.Distribution;
import org.springframework.stereotype.Service;

@Service
public class DefaultDistributionService implements DistributionService {

    @Override
    public Distribution calculateDistribution(Round round) {
        Distribution result = new Distribution();
        for (Outcome outcome : round.getOutcomes()) {
            if (outcome == Outcome.TEAM_ONE_WON) {
                result.teamOneWon();
            } else if (outcome == Outcome.TEAM_TWO_WON) {
                result.teamTwoWon();
            } else {
                result.draw();
            }
        }
        return result;
    }

}
