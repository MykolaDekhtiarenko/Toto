package com.epam.training.toto.reader.csv;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.reader.HitReader;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.training.toto.utilities.GeneralUtils.getValueOfCurrencyColumn;
import static com.epam.training.toto.utilities.GeneralUtils.getValueOfNumberColumn;

@Component
public class HitCSVReader implements HitReader {

    public List<Hit> readHitRecords(CSVRecord csvRecord, int firstColumnIndex, int amount) {
        List<Hit> hits = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            hits.add(Hit.builder()
                    .hits(getValueOfNumberColumn(csvRecord.get(firstColumnIndex + i)))
                    .price(getValueOfCurrencyColumn(csvRecord.get(firstColumnIndex + i + 1)))
                    .build()
            );
        }
        return hits;
    }

}
