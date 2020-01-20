package task6.gui;

import task6.data.*;
import task6.data.Shape;
import task6.matrix.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class RenderPanel extends JPanel {

    private List<Triangle> triangleList;
    private Shape[] shapes;
    private boolean isDemo = false;
    private final int fps = 30;

    public RenderPanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        shapes = new Shape[5];
        for(Shape shape: shapes){
            shape = null;
        }
        triangleList = new ArrayList<>();
    }

    public RenderPanel(int width, int height) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        this();
        setSize(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        if(isDemo) transformAllShape();

        BufferedImage img = paintAllShape();

        g2.drawImage(img, 0, 0, null);

        if(isDemo){
            try {
                Thread.sleep(1000 / fps);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage paintAllShape(){

        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        double[] zBuffer = new double[img.getWidth() * img.getHeight()];

        for (int q = 0; q < zBuffer.length; q++) {
            zBuffer[q] = Double.NEGATIVE_INFINITY;
        }

        for (Triangle t : triangleList) {

                double coefficient = Math.abs(getNormal(t.v1(), t.v2(), t.v3()).z());

                int minX = (int) Math.max(0, Math.ceil(Math.min(t.v1().x(), Math.min(t.v2().x(), t.v3().x()))));
                int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(t.v1().x(), Math.max(t.v2().x(), t.v3().x()))));
                int minY = (int) Math.max(0, Math.ceil(Math.min(t.v1().y(), Math.min(t.v2().y(), t.v3().y()))));
                int maxY = (int) Math.min(img.getHeight() - 1, Math.floor(Math.max(t.v1().y(), Math.max(t.v2().y(), t.v3().y()))));

                double triangleArea = (t.v1().y() - t.v3().y()) * (t.v2().x() - t.v3().x())
                                    + (t.v2().y() - t.v3().y()) * (t.v3().x() - t.v1().x());

                for (int y = minY; y <= maxY; y++) {
                    for (int x = minX; x <= maxX; x++) {
                        Vector4 b = getBarycentricV(t.v1(), t.v2(), t.v3(), x, y, triangleArea);

                        if (b.x() >= 0 && b.x() <= 1 && b.y() >= 0 && b.y() <= 1 && b.z() >= 0 && b.z()<= 1) {

                            double depth = b.x() * t.v1().z() + b.y() * t.v2().z() + b.z() * t.v3().z();

                            int zIndex = y * img.getWidth() + x;
                            if (zBuffer[zIndex] < depth) {
                                img.setRGB(x, y, getNewColor(t.getColor(), coefficient).getRGB());
                                zBuffer[zIndex] = depth;
                            }

                        }
                    }
                }
            }

        return img;
    }


    private Vector4 getBarycentricV(Vector4 v1, Vector4 v2, Vector4 v3, int x, int y, double triangleArea){
        double b1 = ((y - v3.y()) * (v2.x() - v3.x()) + (v2.y() - v3.y()) * (v3.x() - x)) / triangleArea;
        double b2 = ((y - v1.y()) * (v3.x() - v1.x()) + (v3.y() - v1.y()) * (v1.x() - x)) / triangleArea;
        double b3 = ((y - v2.y()) * (v1.x() - v2.x()) + (v1.y() - v2.y()) * (v2.x() - x)) / triangleArea;

        return new Vector4(b1, b2, b3);
    }

    private void transformAllShape(){
        triangleList.clear();
        int position = -1;
        for(Shape shape: shapes) {
            position++;
            if (shape == null) continue;
            transformShape(shape, position);
        }
    }

    private void transformShape(Shape shape, int position){

        shape.options().incAngle();

        Matrix4 rotateMatrix = MatrixUtils.getRotateMatrix(shape.options().getVector(), shape.options().getThisAngle());
        Matrix4 scalingMatrix = MatrixUtils.getScalingMatrix4(1 / shape.options().getScale());

        int height = getHeight() / 2;
        int width = (1 + position) * getWidth() / 6;

        for (Triangle t : shape.getTriangleList()) {

            Vector4 v1 = MatrixUtils.vectorOnMatrix(t.v1().copy(), rotateMatrix);
            Vector4 v2 = MatrixUtils.vectorOnMatrix(t.v2().copy(), rotateMatrix);
            Vector4 v3 = MatrixUtils.vectorOnMatrix(t.v3().copy(), rotateMatrix);

            v1 = MatrixUtils.vectorOnMatrix(v1, scalingMatrix);
            v2 = MatrixUtils.vectorOnMatrix(v2, scalingMatrix);
            v3 = MatrixUtils.vectorOnMatrix(v3, scalingMatrix);

            v1.setX(v1.x() + width);
            v1.setY(v1.y() + height);
            v2.setX(v2.x() + width);
            v2.setY(v2.y() + height);
            v3.setX(v3.x() + width);
            v3.setY(v3.y() + height);
            triangleList.add(new Triangle(v1, v2, v3, t.getColor()));
        }
    }

    private Vector4 getNormal(Vector4 v1, Vector4 v2, Vector4 v3){

        //чтобы найти нормаль, нужно нать два ветора в одной плоскости (в данном слчае две стороны треугольника)
        Vector4 ab = new Vector4(v2.x() - v1.x(), v2.y() - v1.y(), v2.z() - v1.z());
        Vector4 ac = new Vector4(v3.x() - v1.x(), v3.y() - v1.y(), v3.z() - v1.z());

        Vector4 norm = new Vector4(ab.y() * ac.z() - ab.z() * ac.y(),
                ab.z() * ac.x() - ab.x() * ac.z(),
                ab.x() * ac.y() - ab.y() * ac.x());
        //нормализуем
        double normalLength =
                Math.sqrt(norm.x() * norm.x() + norm.y() * norm.y() + norm.z() * norm.z());

        norm.setX( norm.x() / normalLength);
        norm.setY( norm.y() / normalLength);
        norm.setZ( norm.z() / normalLength);

        return norm;
    }

    private Color getNewColor(Color color, double shade) {
//        double redLinear = Math.pow(color.getRed(), 2.4) * shade;
//        double greenLinear = Math.pow(color.getGreen(), 2.4) * shade;
//        double blueLinear = Math.pow(color.getBlue(), 2.4) * shade;
//
//        int red = (int) Math.pow(redLinear, 1/2.4);
//        int green = (int) Math.pow(greenLinear, 1/2.4);
//        int blue = (int) Math.pow(blueLinear, 1/2.4);


        int red = (int) (color.getRed() * shade);
        int green = (int)(color.getGreen() * shade);
        int blue = (int) (color.getBlue() * shade);

        return new Color(red, green, blue);
    }

    public void geoButtonClick(int position){
        String type = MainPanel.shapeType.getModel().getSelectedItem().toString();

        if(type.equals("Пустота")){
            shapes[position] = null;
            return;
        }

        double speed = (Double) MainPanel.speedSpinner.getModel().getValue();
        double scale = (Double) MainPanel.scaleSpinner.getModel().getValue();

        Vector4 vector = new Vector4(
                (Double) MainPanel.x.getModel().getValue(),
                (Double) MainPanel.y.getModel().getValue(),
                (Double) MainPanel.z.getModel().getValue()
        );
        vector.normalization();

        Shape shape;
        if(shapes[position] != null && type.equals(shapes[position].options().getType())){
            shape = shapes[position];
            shape.options().setAll(speed, shape.options().getThisAngle(), scale, vector, type);
        }else{
            shape = getShapeByStringType(type);
            shape.setOptions(new Options(speed, scale, vector, type));
        }

        shapes[position] = shape;
    }

    private Shape getShapeByStringType(String type){
        switch (type){
            case "Тетраэдр":
                return new Tetrahedron();
            case "Куб":
                return new Cube();
            case "Октаэдр":
                return new Octahedron();
            case "Икосаэдр":
                return new Icosahedron();
            case "Додекаэдр":
                return new Dodecahedron();
            default:
                return null;
        }
    }

    public void outsideRepaint(){
        repaint();
    }

    public boolean isDemo() {
        return isDemo;
    }

    public void setDemo(boolean demo) {
        isDemo = demo;
    }
}
