package task6.data;

import task6.matrix.Vector4;

import java.awt.*;

public class Tetrahedron extends Shape {
    @Override
    void fillList() {

        double tetrahedralAngle = Math.PI * 109.4712f / 180;
        double segmentAngle = Math.PI * 2 / 3;
        double currentAngle = 0f;

        Vector4[] v = new Vector4[4];

        v[0] = new Vector4(0, radius, 0);

        for (int i = 1; i <= 3; i++)
        {
            v[i] = new Vector4(radius * Math.sin(currentAngle) * Math.sin(tetrahedralAngle),
                    radius * Math.cos(tetrahedralAngle),
                    radius * Math.cos(currentAngle) * Math.sin(tetrahedralAngle));
            currentAngle = currentAngle + segmentAngle;
        }

        triangleList.add(new Triangle(v[0], v[1], v[2], Color.WHITE));
        triangleList.add(new Triangle(v[1], v[3], v[2], Color.RED));
        triangleList.add(new Triangle(v[0], v[2], v[3], Color.GREEN));
        triangleList.add(new Triangle(v[0], v[3], v[1], Color.BLUE));

    }
}
