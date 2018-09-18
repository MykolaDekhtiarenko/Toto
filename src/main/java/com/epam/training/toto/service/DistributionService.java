package com.epam.training.toto.service;

import com.epam.training.toto.domain.Round;
import com.epam.training.toto.vo.Distribution;

public interface DistributionService {
    Distribution calculateDistribution(Round round);
}
