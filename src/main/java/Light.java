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

    public void switchOn() {
        this.on = true;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isOff() {
        return !on;
    }

    public void switchOff() {
        this.on = false;
    }
}
