public class Corridor {

    private final Light light;
    private final Ac ac;

    public Corridor(Light light, Ac ac) {
        this.light = light;
        this.ac = ac;
    }

    public Light getLight() {
        return light;
    }

    public Ac getAc() {
        return ac;
    }
}
