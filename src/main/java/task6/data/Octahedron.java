package task6.data;

import task6.matrix.Vector4;

import java.awt.*;

public class Octahedron extends Shape {

    @Override
    void fillList() {

        Vector4[] v = new Vector4[6];
        v[0] = new Vector4(0, -radius, 0);
        v[1] = new Vector4(-radius, 0, 0);
        v[2] = new Vector4(0, 0, -radius);
        v[3] = new Vector4(radius, 0, 0);
        v[4] = new Vector4(0, 0, radius);
        v[5] = new Vector4(0, radius, 0);

        triangleList.add(new Triangle(v[0], v[1], v[2], Color.WHITE));

        triangleList.add(new Triangle(v[0], v[2], v[3], Color.BLUE));

        triangleList.add(new Triangle(v[0], v[3], v[4], Color.GREEN));

        triangleList.add(new Triangle(v[0], v[4], v[1], Color.YELLOW));

        triangleList.add(new Triangle(v[5], v[1], v[4], Color.orange));

        triangleList.add(new Triangle(v[5], v[4], v[3], Color.PINK));

        triangleList.add(new Triangle(v[5], v[3], v[2], Color.MAGENTA));

        triangleList.add(new Triangle(v[5], v[2], v[1], Color.CYAN));

    }
}
