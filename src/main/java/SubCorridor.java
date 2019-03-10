
public class SubCorridor implements MotionSensible {
    private final Light light;
    private final Ac ac;
    private MotionSensorControl motionSensor;

    public SubCorridor(Light light, Ac ac) {
        this.light = light;
        this.ac = ac;
        this.motionSensor = motionSensor;
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

}
