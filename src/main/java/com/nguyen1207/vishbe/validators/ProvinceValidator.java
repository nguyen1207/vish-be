package com.nguyen1207.vishbe.validators;

import com.nguyen1207.vishbe.annotations.ValidateProvince;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.io.FileReader;
import java.util.List;

public class ProvinceValidator implements ConstraintValidator<ValidateProvince, String> {
    @Override
    public boolean isValid(String province, ConstraintValidatorContext constraintValidatorContext) {
        if (province == null) return true;

        try {
            FileReader filereader = new FileReader("src/main/resources/static/provinces.csv");

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();

            String[] provinceSplit = province.split(",");

            if (provinceSplit.length != 3) return false;

            String city = provinceSplit[0].trim();
            String district = provinceSplit[1].trim();
            String ward = provinceSplit[2].trim();


            for (String[] row : allData) {
                if (row[0].equals(city) && row[1].equals(district) && row[2].equals(ward)) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error in reading CSV file: " + e);
        }
    }
}
