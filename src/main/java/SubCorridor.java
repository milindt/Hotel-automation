public class SubCorridor {
    private final Light light;
    private final Ac ac;

    public SubCorridor(Light light, Ac ac) {
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
