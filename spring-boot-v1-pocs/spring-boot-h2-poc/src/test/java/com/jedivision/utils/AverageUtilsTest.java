package com.jedivision.utils;

import com.jedivision.entity.Equipment;
import com.jedivision.test.CoberturaUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import static com.jedivision.constants.ApplicationConstants.DEFAULT_AVG_VALUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AverageUtilsTest {

    @BeforeClass
    public static void beforeClass() {
        CoberturaUtils.classCoverageHook(AverageUtils.class);
    }

    @Test
    public void getEquipmentAvgRatingNotPresent() throws Exception {
        // Arrange
        Equipment equipment = new Equipment();
        equipment.setRatings(Collections.emptyList());

        // Act
        Double avgRating = AverageUtils.getEquipmentAvgRating(equipment);

        // Assert
        assertThat(avgRating, is(notNullValue()));
        assertThat(avgRating, is(DEFAULT_AVG_VALUE));
    }
}
