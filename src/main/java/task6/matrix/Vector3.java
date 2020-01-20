package task6.matrix;

public class Vector3 {

    private double x;
    private double y;
    private double normal;

    public Vector3() {
    }

    public Vector3(double x, double y) {
        this.x = x;
        this.y = y;
        this.normal = 1;
    }

    public Vector3(double x, double y, double normal) {
            this.x = x/normal;
            this.y = y/normal;
            this.normal = 1;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public boolean isNormal(){
        return normal == 1;
    }

}

