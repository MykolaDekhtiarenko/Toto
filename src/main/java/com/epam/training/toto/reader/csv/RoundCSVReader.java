package com.epam.training.toto.reader.csv;

import com.epam.training.toto.domain.Round;
import com.epam.training.toto.reader.HitReader;
import com.epam.training.toto.reader.OutcomeReader;
import com.epam.training.toto.reader.RoundReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.toto.utilities.GeneralUtils.getValueOfDateColumn;
import static com.epam.training.toto.utilities.GeneralUtils.getValueOfNumberColumn;

@Component
public class RoundCSVReader implements RoundReader {
    private static final int WEEK_COLUMN_INDEX = 1;
    private static final int ROUND_COLUMN_INDEX = 2;
    private static final int DATE_COLUMN_INDEX = 3;
    private static final int HIT_FIRST_COLUMN_INDEX = 4;
    private static final int HIT_AMOUNT_OF_RECORDS = 5;
    private static final int OUTCOME_FIRST_COLUMN_INDEX = 14;
    private static final int OUTCOME_AMOUNT_OF_RECORDS = 13;

    @Autowired
    private HitReader hitReader;
    @Autowired
    private OutcomeReader outcomeReader;

    public List<Round> readRoundRecords(String path) {
        List<Round> rounds = new ArrayList<>();
        try (CSVParser csvParser = new CSVParser(getReader(path), CSVFormat.newFormat(';'))) {
            for (CSVRecord csvRecord : csvParser) {
                rounds.add(readRoundRecord(csvRecord));
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong!");
        }
        return rounds;
    }

    public Round readRoundRecord(CSVRecord csvRecord) {
        return Round.builder()
                .week(getValueOfNumberColumn(csvRecord.get(WEEK_COLUMN_INDEX)))
                .round(getValueOfNumberColumn(csvRecord.get(ROUND_COLUMN_INDEX)))
                .date(getValueOfDateColumn(csvRecord.get(DATE_COLUMN_INDEX), "yyyy.MM.dd."))
                .hits(hitReader.readHitRecords(csvRecord, HIT_FIRST_COLUMN_INDEX, HIT_AMOUNT_OF_RECORDS))
                .outcomes(outcomeReader.readOutcomeRecords(csvRecord, OUTCOME_FIRST_COLUMN_INDEX,
                        OUTCOME_AMOUNT_OF_RECORDS))
                .build();
    }

    private Reader getReader(String path) throws IOException {
        return new FileReader(new ClassPathResource(path).getFile());
    }
}
