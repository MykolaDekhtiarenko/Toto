package com.epam.training.toto.reader;

import com.epam.training.toto.domain.Hit;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface HitReader {
    List<Hit> readHitRecords(CSVRecord csvRecord, int firstColumnIndex, int amount);
}
