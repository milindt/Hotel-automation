import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

class SubCorridor implements MotionSensible {
    private final Light light;
    private final Ac ac;
    private final Map<String, ElectronicEquipment> electronicEquipmentMap;

    SubCorridor(Light light, Ac ac, Collection<ElectronicEquipment> equipments) {
        this.light = light;
        this.ac = ac;
        electronicEquipmentMap = equipments.stream().collect(Collectors.toMap(ElectronicEquipment::getType, v -> v));
    }

    Light getLight() {
        return light;
    }

    Ac getAc() {
        return ac;
    }

    int getTotalPowerConsumption() {
        return getLight().getUnits() + getAc().getUnits();
    }

    ElectronicEquipment getElectronicEquipment(String name) {
        return electronicEquipmentMap.get(name);
    }
}
