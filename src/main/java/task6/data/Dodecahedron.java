package task6.data;

import task6.matrix.Vector4;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dodecahedron extends Shape {
    @Override
    void fillList() {
        double radius = 100d;
        //перевод в радианы

        double upperLayerAngle = Math.toRadians(52.625776d);
        double bottomLayerAngle = Math.toRadians(10.812576d);

        double segmentAngle = Math.toRadians(72);
        double currentAngle = 0d;

        Vector4[] v = new Vector4 [20];

        for (int i = 0; i < 5; i++)
        {
            v[i] = new Vector4 (radius * Math.sin(currentAngle) * Math.cos(upperLayerAngle),
                    radius * Math.sin(upperLayerAngle),
                    radius * Math.cos(currentAngle) * Math.cos(upperLayerAngle));
            currentAngle += segmentAngle;
        }
        currentAngle = 0d;
        for (int i = 5; i < 10; i++)
        {
            v[i] = new Vector4 (radius * Math.sin(currentAngle) * Math.cos(bottomLayerAngle),
                    radius * Math.sin(bottomLayerAngle),
                    radius * Math.cos(currentAngle) * Math.cos(bottomLayerAngle));
            currentAngle += segmentAngle;
        }

        currentAngle = Math.toRadians(36);

        for (int i=10; i < 15; i++)
        {
            v[i] = new Vector4 (radius * Math.sin(currentAngle) * Math.cos(-bottomLayerAngle),
                    radius * Math.sin(-bottomLayerAngle),
                    radius * Math.cos(currentAngle) * Math.cos(-bottomLayerAngle));
            currentAngle += segmentAngle;
        }
        currentAngle = Math.toRadians(36);
        for (int i=15; i < 20; i++)
        {
            v[i] = new Vector4 (radius * Math.sin(currentAngle) * Math.cos(-upperLayerAngle),
                    radius * Math.sin(-upperLayerAngle),
                    radius * Math.cos(currentAngle) * Math.cos(-upperLayerAngle));
            currentAngle += segmentAngle;
        }

        addPentagon(v[0], v[1], v[4], v[2], v[3], new Color(255, 255, 255));
        addPentagon(v[0], v[1], v[5], v[6], v[10], new Color(255, 255, 0));
        addPentagon(v[1], v[2], v[6], v[7], v[11], new Color(0, 0, 200));
        addPentagon(v[2], v[3], v[7], v[8], v[12], new Color(255, 0, 0));
        addPentagon(v[3], v[4], v[8], v[9], v[13], new Color(0, 177, 0));
        addPentagon(v[4], v[0], v[9], v[5], v[14], new Color(166, 0, 168));
        addPentagon(v[15], v[16], v[19], v[17], v[18], new Color(142, 142, 142));
        addPentagon(v[16], v[15], v[11], v[10], v[6], new Color(52, 255, 46));
        addPentagon(v[17], v[16], v[12], v[11], v[7], new Color(255, 197, 225));
        addPentagon(v[18], v[17], v[13], v[12], v[8], new Color(255, 254, 147));
        addPentagon(v[19], v[18], v[14], v[13], v[9], new Color(0, 255, 245));
        addPentagon(v[15], v[19], v[10], v[14], v[5], new Color(255, 155, 0));
    }

    private void addPentagon(Vector4 v1, Vector4 v2, Vector4 v3, Vector4 v4, Vector4 v5, Color color){

        triangleList.add(new Triangle(v1, v2, v3, color));
        triangleList.add(new Triangle(v3, v4, v5, color));
        triangleList.add(new Triangle(v2, v3, v4, color));

    }

}
