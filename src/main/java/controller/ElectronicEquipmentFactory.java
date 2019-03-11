package controller;

import java.util.Arrays;
import java.util.Collection;

public class ElectronicEquipmentFactory {

    public static final String LIGHT = "Light";
    public static final String AC = "Ac";

    public static Collection<ElectronicEquipment> getSubCorridorDevices() {
        return Arrays.asList(new ElectronicEquipment(LIGHT, 5, false),
                new ElectronicEquipment(AC, 10, true));
    }

    public static Collection<ElectronicEquipment> getCorridorDevices() {
        return Arrays.asList(new ElectronicEquipment(LIGHT, 5, true),
                new ElectronicEquipment(AC, 10, true));
    }
}
