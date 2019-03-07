public class Light {
    private int units;
    private boolean on;

    public Light(int units, boolean on) {
        this.units = units;
        this.on = on;
    }

    public int getUnits() {
        return on ? units : 0;
    }

    public Light switchOff() {
        this.on = false;
        return this;
    }

    public Light switchOn() {
        this.on = true;
        return this;
    }

    public boolean isOn() {
        return on;
    }
}
