package com.epam.training.toto.reader.csv;

import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.reader.OutcomeReader;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.training.toto.utilities.GeneralUtils.getValueOfOutcomeColumn;

@Component
public class OutcomeCSVReader implements OutcomeReader {

    public List<Outcome> readOutcomeRecords(CSVRecord csvRecord, int firstColumnIndex, int amount) {
        List<Outcome> outcomes = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            outcomes.add(Outcome.getOutcome(getValueOfOutcomeColumn(csvRecord.get(firstColumnIndex + i))));
        }
        return outcomes;
    }
}
