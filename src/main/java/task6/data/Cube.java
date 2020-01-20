package task6.data;

import task6.matrix.Vector4;

import java.awt.*;

public class Cube extends Shape {
    @Override
    void fillList() {

        double a = radius * Math.cos(Math.toRadians(45));

        Vector4[] v = new Vector4[8];

        triangleList.add(new Triangle(new Vector4(a, a, a), new Vector4 (a, -a, a),
                new Vector4 (-a, a, a), Color.BLUE));

        triangleList.add(new Triangle(new Vector4 (-a, -a, a), new Vector4 (a, -a, a),
                new Vector4 (-a, a, a), Color.BLUE));

        //нижние
        triangleList.add(new Triangle(new Vector4 (a, a, -a), new Vector4 (a, -a, -a),
                new Vector4 (-a, a, -a), Color.RED));

        triangleList.add(new Triangle(new Vector4 (-a, -a, -a), new Vector4 (a, -a, -a),
                new Vector4 (-a, a, -a), Color.RED));

        //бок лево
        triangleList.add(new Triangle(new Vector4 (-a, -a, a), new Vector4 (-a, a, a),
                new Vector4 (-a, -a, -a), Color.GREEN));

        triangleList.add(new Triangle(new Vector4 (-a, -a, -a), new Vector4 (-a, a, -a),
                new Vector4 (-a, a, a), Color.GREEN));

        //бок право
        triangleList.add(new Triangle(new Vector4 (a, a, a), new Vector4 (a, -a, a),
                new Vector4 (a, -a, -a), Color.WHITE));

        triangleList.add(new Triangle(new Vector4 (a, -a, -a), new Vector4 (a, a, -a),
                new Vector4 (a, a, a), Color.WHITE));

        //бок верх
        triangleList.add(new Triangle(new Vector4 (-a, a, a), new Vector4 (a, a, a),
                new Vector4 (-a, a, -a), Color.YELLOW));

        triangleList.add(new Triangle(new Vector4 (-a, a, -a), new Vector4 (a, a, -a),
                new Vector4 (a, a, a), Color.YELLOW));

        //бок низ
        triangleList.add(new Triangle(new Vector4 (-a, -a, a), new Vector4 (a, -a, a),
                new Vector4 (-a, -a, -a), Color.MAGENTA));

        triangleList.add(new Triangle(new Vector4 (-a, -a, -a), new Vector4 (a, -a, -a),
                new Vector4 (a, -a, a), Color.MAGENTA));
    }
}
