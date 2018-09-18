package com.epam.training.toto.reader;

import com.epam.training.toto.domain.Round;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface RoundReader {
    List<Round> readRoundRecords(String path);

    Round readRoundRecord(CSVRecord csvRecord);
}
