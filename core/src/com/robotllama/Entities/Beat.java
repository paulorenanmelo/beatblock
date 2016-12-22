package com.robotllama.Entities;

/**
 * Created by Paulo on 10-Sep-15.
 */
public class Beat {
    private static enum Intensity { Low, Medium, High; }
    private static enum Type {  }

    private double time;
    private Intensity intensity;

    public Beat(double _time, Intensity _intensity){
        time = _time;
        intensity = _intensity;
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public double getTime() {
        return time;
    }
}
