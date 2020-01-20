package task6.data;

import task6.matrix.Vector4;

public class Options {

    private double speed;
    private double thisAngle;
    private double scale;
    private Vector4 vector;
    private String type;

    public Options(double speed, double scale, Vector4 vector, String type) {
        this.speed = speed;
        this.thisAngle = 0;
        this.scale = scale;
        this.vector = vector;
        this.type = type;
    }

    public void setAll(double speed, double thisAngle, double scale, Vector4 vector, String type) {
        this.speed = speed;
        this.thisAngle = thisAngle;
        this.scale = scale;
        this.vector = vector;
        this.type = type;
    }

    public void incAngle(){
        thisAngle = (thisAngle + speed)%360;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getThisAngle() {
        return thisAngle;
    }

    public void setThisAngle(double thisAngle) {
        this.thisAngle = thisAngle;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Vector4 getVector() {
        return vector;
    }

    public void setVector(Vector4 vector) {
        this.vector = vector;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
