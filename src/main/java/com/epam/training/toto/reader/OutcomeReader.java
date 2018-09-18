package com.epam.training.toto.reader;

import com.epam.training.toto.domain.Outcome;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface OutcomeReader {
    List<Outcome> readOutcomeRecords(CSVRecord csvRecord, int firstColumnIndex, int amount);
}
