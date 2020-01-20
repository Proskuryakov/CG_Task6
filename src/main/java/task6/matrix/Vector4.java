package task6.matrix;

public class Vector4 {

    private double x;
    private double y;
    private double z;
    private double normal;

    public Vector4() {
    }

    public Vector4(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.normal = 1;
    }

    public Vector4(double x, double y, double z, double normal) {
        this.x = x/normal;
        this.y = y/normal;
        this.z = z/normal;
        this.normal = 1;
    }

    public Vector4 copy(){
        return new Vector4(x, y, z, normal);
    }

    public void normalization(){
        double len = getLenght();
        x = x/len;
        y = y/len;
        z = z/len;
    }

    public double getLenght(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public double x() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double y() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double z() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
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

    @Override
    public String toString() {
        return String.format("[%f, %f, %f, %f]", x, y, z, normal);
    }
}
