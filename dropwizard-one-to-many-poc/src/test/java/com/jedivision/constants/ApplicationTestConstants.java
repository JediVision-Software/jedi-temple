package com.jedivision.constants;

import com.jedivision.entity.EquipmentType;
import com.jedivision.entity.Rank;

public class ApplicationTestConstants {

    // Resource URL
    public static final String JEDI_BASE_URL = "http://localhost:8080/jedi/";
    public static final String EQUIPMENT_BASE_URL = "http://localhost:8080/equipment/";
    public static final String INDEX_BASE_URL = "http://localhost:8080/index/";

    // Database
    public static final Integer JEDIS_NUMBER = 1;
    public static final Integer PRESENT_JEDI = 1;
    public static final Integer MISSING_JEDI = 2;
    public static final String JEDI_NAME = "Yoda";
    public static final Rank JEDI_RANK = Rank.MASTER;
    public static final Integer EQUIPMENTS_NUMBER = 1;
    public static final Integer PRESENT_EQUIPMENT = 1;
    public static final Integer MISSING_EQUIPMENT = 2;
    public static final EquipmentType EQUIPMENT_TYPE = EquipmentType.ARMOR;
    public static final Integer EQUIPMENT_VALUE = 15;
    public static final Integer EQUIPMENT_JEDI_ID = 1;

    private ApplicationTestConstants() {}
}
