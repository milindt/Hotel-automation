import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class SubCorridor implements MotionSensible {
    private final Light light;
    private final Ac ac;
    private final Map<String, ElectronicEquipment> electronicEquipmentMap;
    private MotionSensorControl motionSensor;

    public SubCorridor(Light light, Ac ac, Collection<ElectronicEquipment> equipments) {
        this.light = light;
        this.ac = ac;
        this.motionSensor = motionSensor;
        electronicEquipmentMap = equipments.stream().collect(Collectors.toMap(ElectronicEquipment::getType, v -> v));
    }

    public Light getLight() {
        return light;
    }

    public Ac getAc() {
        return ac;
    }

    public MotionSensorControl getSensor() {
        return motionSensor;
    }

    public int getTotalPowerConsumption() {
        return getLight().getUnits() + getAc().getUnits();
    }

    public ElectronicEquipment getElectronicEquipment(String name) {
        return electronicEquipmentMap.get(name);
    }
}
