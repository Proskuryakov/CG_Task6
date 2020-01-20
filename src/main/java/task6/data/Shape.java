package task6.data;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {

    protected Options options;
    protected double radius = 30;
    protected List<Triangle> triangleList;

    public Shape(){
        triangleList = new ArrayList<>();
        fillList();
    }

    public Shape(double radius){
        this();
        this.radius = radius;
    }

    public List<Triangle> getTriangleList() {
        return triangleList;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Options options() {
        return options;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    abstract void fillList();
}
