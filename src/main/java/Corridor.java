public class Corridor {

    private final Light light;
    private final Ac ac;
    private final MotionSensor motionSensor;

    public Corridor(Light light, Ac ac, MotionSensor motionSensor) {
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

    public MotionSensor getSensor() {
        return motionSensor;
    }
}
