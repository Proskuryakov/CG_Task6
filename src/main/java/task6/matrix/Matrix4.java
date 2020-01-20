package task6.matrix;

public class Matrix4 {

    private final int SIZE = 4;
    private double[][] value;

    public Matrix4(){
        value = new double[SIZE][SIZE];
    }

    public Matrix4(double[][] value) {
        this.value = value;
    }

    public Matrix4(double m11, double m12, double m13, double m14,
                   double m21, double m22, double m23, double m24,
                   double m31, double m32, double m33, double m34,
                   double m41, double m42, double m43, double m44){
        value = new double[][]
                {{m11, m12, m13, m14},
                {m21, m22, m23, m24},
                {m31, m32, m33, m34},
                {m41, m42, m43, m44}};
    }

    public void multiply(Matrix4 matrix){
        this.value = MatrixUtils.getResultMultiplyMatrix(value, matrix.getValue());
    }

    public double[][] getValue() {
        return value;
    }

    public void setValue(double[][] value) {
        this.value = value;
    }

    public int getSIZE() {
        return SIZE;
    }

    public double getElement(int i, int j){
        return value[i][j];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < value.length; i++){
            for (int j = 0; j < value[0].length; j++) {
                sb.append(value[i][j]);
                sb.append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
