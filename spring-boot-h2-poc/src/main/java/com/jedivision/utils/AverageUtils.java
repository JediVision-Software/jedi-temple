package com.jedivision.utils;

import com.jedivision.entity.Equipment;
import com.jedivision.entity.Rating;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.OptionalDouble;

import static com.jedivision.constants.ApplicationConstants.DEFAULT_AVG_VALUE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AverageUtils {
    public static Double getEquipmentAvgRating(Equipment equipment) {
        OptionalDouble average = equipment.getRatings().stream().
                mapToInt(Rating::getRate)
                .average();
        if (average.isPresent()) {
            return average.getAsDouble();
        } else {
            return DEFAULT_AVG_VALUE;
        }
    }
}
