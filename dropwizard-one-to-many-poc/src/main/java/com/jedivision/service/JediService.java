package com.jedivision.service;

import com.jedivision.entity.Equipment;
import com.jedivision.entity.Jedi;

import java.util.List;

public interface JediService {
    void indexJedi();
    List<Jedi> findAllJedi();
    Jedi findJediById(int jediId);
    Jedi createJedi(Jedi jedi);
    Jedi updateJedi(int jediId, Jedi jedi);
    void deleteJedi(int jediId);
    Equipment addEquipmentToJedi(int jediId, int equipmentId);
    void removeEquipmentsFromJedi(int jediId);
}
