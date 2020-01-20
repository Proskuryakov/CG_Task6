package task6.matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils {

    //Matrix

    public static Matrix4 getRotateXMatrix(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        return new Matrix4(new double[][]
                {{1, 0, 0, 0},
                {0, cos, sin, 0},
                {0, -sin, cos, 0},
                {0,0,0,1}});
    }

    public static Matrix4 getRotateYMatrix(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        return new Matrix4(new double[][]
                {{cos, 0, sin, 0},
                {0, 1, 0, 0},
                {-sin, 0, cos, 0},
                {0,0,0,1}});
    }

    public static Matrix4 getRotateZMatrix(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        return new Matrix4(new double[][]
                {{cos, -sin, 0, 0},
                {sin, cos, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}});
    }

    public static Matrix4 getRotateMatrix(Vector4 v, double angle){
        double s = Math.sin(angle);
        double c = Math.cos(angle);
        double t = 1 - c;

        double x = v.x();
        double y = v.y();
        double z = v.z();

        Matrix4 res = new Matrix4(
            t*x*x + c,   t*x*y - s*z, t*x*z + s*y, 0,
            t*x*y + s*z, t*y*y + c,   t*y*z - s*x, 0,
            t*x*z - s*y, t*y*z + s*x, t*z*z + c,   0,
            0,           0,           0,           1
        );

        return res;
    }

    public static Matrix4 getScalingMatrix4(double scaling){
        return new Matrix4(new double[][]
                {{1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, scaling}});
    }

    public static Matrix4 getProjectionZMatrix(){
        return new Matrix4(new double[][]
                {{1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}});
    }

    public static Matrix3 getRotateMatrix(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        return new Matrix3(new double[][]
                {{cos, sin, 0},
                {-sin, cos, 0},
                {0,0,1}});
    }

    public static Matrix3 getScalingMatrix3(double scaling){
        return new Matrix3(new double[][]
                {{1, 0, 0},
                {0, 1, 0},
                {0, 0, scaling}});
    }

    public static Matrix3 getParallelTransferMatrix3(double x, double y){
        return new Matrix3(1, 0, 0,
                            0, 1, 0,
                            x, y, 1);
    }

    public static Matrix3 getReflectionXMatrix3(){
        return new Matrix3(1, 0, 0,
                            0, -1, 0,
                            0, 0, 1);
    }

    public static Matrix3 getReflectionYMatrix3(){
        return new Matrix3(-1, 0, 0,
                            0, 1, 0,
                            0, 0, 1);
    }

    public static Matrix3 getExpansionMatrix3(double x, double y){
        return new Matrix3(x, 0, 0,
                    0, y, 0,
                    0, 0, 1);
    }

    //Logic

    public static Matrix4 getMultiplyMatrix(Matrix4 matrix1, Matrix4 matrix2){

        double[][] matrix1Value = matrix1.getValue();
        double[][] matrix2Value = matrix2.getValue();

        if(matrix1Value[0].length != matrix2Value.length) return null;

        return new Matrix4(getResultMultiplyMatrix(matrix1Value, matrix2Value));

    }

    public static Matrix3 getMultiplyMatrix(Matrix3 matrix1, Matrix3 matrix2){

        double[][] matrix1Value = matrix1.getValue();
        double[][] matrix2Value = matrix2.getValue();

        if(matrix1Value[0].length != matrix2Value.length) return null;

        return new Matrix3(getResultMultiplyMatrix(matrix1Value, matrix2Value));

    }

    public static double[][] getResultMultiplyMatrix(double[][] value, double[][] input){

        if(value[0].length != input.length) return null;

        double[][] result = new double[value.length][value[0].length];

        for(int i = 0; i < value.length; i++){

            for(int j = 0; j < value[0].length; j++){
                double sum = 0;
                for (int k = 0; k < value[0].length; k++) {
                    sum += value[i][k] * input[k][j];
                }
                result[i][j] = sum;
            }

        }
        return result;
    }

    public static List<Vector4> listVectorOnMatrix(List<Vector4> vectorList, Matrix4 matrix){
        List<Vector4> newList = new ArrayList<>();
        for(Vector4 v: vectorList){
            newList.add(vectorOnMatrix(v, matrix));
        }
        return newList;
    }

    public static Vector4 vectorOnMatrix(Vector4 vector, Matrix4 matrix){
        double[][] value = matrix.getValue();

        double x = vector.x()*value[0][0] + vector.y()*value[1][0] + vector.z()*value[2][0] + vector.getNormal()*value[3][0];
        double y = vector.x()*value[0][1] + vector.y()*value[1][1] + vector.z()*value[2][1] + vector.getNormal()*value[3][1];
        double z = vector.x()*value[0][2] + vector.y()*value[1][2] + vector.z()*value[2][2] + vector.getNormal()*value[3][2];
        double normal = vector.x()*value[0][3] + vector.y()*value[1][3] + vector.z()*value[2][3] + vector.getNormal()*value[3][3];

        return new Vector4(x,y,z,normal);
    }

    public static List<Vector3> listVectorOnMatrix(List<Vector3> vectorList, Matrix3 matrix){
        List<Vector3> newList = new ArrayList<>();
        for(Vector3 v: vectorList){
            newList.add(vectorOnMatrix(v, matrix));
        }
        return newList;
    }

    public static Vector3 vectorOnMatrix(Vector3 vector, Matrix3 matrix){
        double[][] value = matrix.getValue();

        double x = vector.getX()*value[0][0] + vector.getY()*value[1][0] + vector.getNormal()*value[2][0];
        double y = vector.getX()*value[0][1] + vector.getY()*value[1][1] + vector.getNormal()*value[2][1];
        double normal = vector.getX()*value[0][2] + vector.getY()*value[1][2] + vector.getNormal()*value[2][2];

        return new Vector3(x,y,normal);
    }


}
