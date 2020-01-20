package task6.data;

import task6.matrix.Vector4;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Icosahedron extends Shape{

    @Override
    void fillList() {
        double radius = 100d;
        //перевод в радианы
        double magicAngle = Math.PI * 26.565d/180;
        double segmentAngle = Math.PI * 72 / 180;
        double currentAngle = 0d;

        Vector4 [] v = new Vector4 [12];

        v[0] = new Vector4 (0, radius, 0);
        v[11] = new Vector4 (0, -radius, 0);

        for (int i = 1; i < 6; i++)
        {
            v[i] = new Vector4 (radius * Math.sin(currentAngle) * Math.cos(magicAngle),
                    radius * Math.sin(magicAngle),
                    radius * Math.cos(currentAngle) * Math.cos(magicAngle));
            currentAngle += segmentAngle;
        }

        currentAngle = Math.PI*36/180;

        for (int i=6; i<11; i++)
        {
            v[i] = new Vector4 (radius * Math.sin(currentAngle) * Math.cos(-magicAngle),
                    radius * Math.sin(-magicAngle),
                    radius * Math.cos(currentAngle) * Math.cos(-magicAngle));
            currentAngle += segmentAngle;
        }

        triangleList.add(new Triangle(v[0], v[1], v[2], new Color(0x07CA00)));
        triangleList.add(new Triangle(v[0], v[2], v[3], new Color(241, 255, 0)));
        triangleList.add(new Triangle(v[0], v[3], v[4], new Color(255, 6, 0)));
        triangleList.add(new Triangle(v[0], v[4], v[5], new Color(255, 0, 205)));
        triangleList.add(new Triangle(v[0], v[5], v[1], new Color(0, 4, 255)));

        triangleList.add(new Triangle(v[11], v[7], v[6], new Color(0, 255, 252)));
        triangleList.add(new Triangle(v[11], v[8], v[7], new Color(154, 0, 150)));
        triangleList.add(new Triangle(v[11], v[9], v[8], new Color(0, 18, 134)));
        triangleList.add(new Triangle(v[11], v[10], v[9], new Color(0, 127, 5)));
        triangleList.add(new Triangle(v[11], v[6], v[10], new Color(0, 126, 132)));

        triangleList.add(new Triangle(v[2], v[1], v[6], new Color(147, 0, 179)));
        triangleList.add(new Triangle(v[3], v[2], v[7], new Color(160, 192, 212)));
        triangleList.add(new Triangle(v[4], v[3], v[8], new Color(186, 136, 74)));
        triangleList.add(new Triangle(v[5], v[4], v[9], new Color(24, 200, 113)));
        triangleList.add(new Triangle(v[1], v[5], v[10], new Color(187, 34, 50)));

        triangleList.add(new Triangle(v[6], v[7], v[2], new Color(207, 240, 255)));
        triangleList.add(new Triangle(v[7], v[8], v[3], new Color(48, 154, 127)));
        triangleList.add(new Triangle(v[8], v[9], v[4], new Color(0, 191, 136)));
        triangleList.add(new Triangle(v[9], v[10], v[5], new Color(199, 137, 117)));
        triangleList.add(new Triangle(v[10], v[6], v[1], new Color(235, 61, 114)));
    }
}
