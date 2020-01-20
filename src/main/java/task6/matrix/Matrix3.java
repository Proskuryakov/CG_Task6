package task6.matrix;

public class Matrix3 {

    private final int SIZE = 3;
    private double[][] value;

    public Matrix3() {
        value = new double[SIZE][SIZE];
    }

    public Matrix3(double[][] matrix) {
        this.value = matrix;
    }

    public Matrix3(double m11, double m12, double m13,
                  double m21, double m22, double m23,
                  double m31, double m32, double m33){
        value = new double[][]{{m11, m12, m13},
                {m21, m22, m23},
                {m31, m32, m33}};
    }


    public void multiply(Matrix3 matrix){
        this.value = MatrixUtils.getResultMultiplyMatrix(value, matrix.getValue());
    }

    public int getSize() {
        return SIZE;
    }

    public double[][] getValue() {
        return value;
    }

    public void setValue(double[][] value) {
        this.value = value;
    }

    public double getElement(int i, int j){
        return value[i][j];
    }
}
