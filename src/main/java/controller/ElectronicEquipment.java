package controller;

import java.util.Objects;

public class ElectronicEquipment {
    private String type;
    private int units;
    private boolean on;

    public ElectronicEquipment(String type, int units, boolean on) {
        this.type = type;
        this.units = units;
        this.on = on;
    }

    public int getUnits() {
        return on ? units : 0;
    }

    void switchIt(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isOff() {
        return !on;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectronicEquipment that = (ElectronicEquipment) o;
        return getUnits() == that.getUnits() &&
                isOn() == that.isOn() &&
                getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getUnits(), isOn());
    }
}
