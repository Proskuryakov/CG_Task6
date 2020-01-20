package task6.gui;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    public static void start() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Task6");
        frame.setSize(800, 425);

        Container mainPanel = frame.getContentPane();
        mainPanel.setLayout(new BorderLayout());

        renderPanel = new RenderPanel(600, 400);
        JButton startButton = new JButton("Старт");
        startButton.addActionListener(e -> {
            renderPanel.setDemo(! renderPanel.isDemo());
            startButton.setText(renderPanel.isDemo()?"Стоп":"Старт");
            renderPanel.outsideRepaint();
        });

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(startButton, BorderLayout.NORTH);
        rightPanel.add(getSettingsPanel(200, 400), BorderLayout.CENTER);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(renderPanel, BorderLayout.CENTER);
        leftPanel.add(getGeoButtonPanel(600, 25), BorderLayout.SOUTH);

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private static JPanel getSettingsPanel(int width, int height){
        JPanel panel = new JPanel(new GridLayout(12, 1, 5, 1));
        panel.setSize(width, height);

        JLabel speedLabel = new JLabel("Скорость:");
        speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        speedSpinner = new JSpinner(new SpinnerNumberModel(0.10, 0, 360, 0.01));
        addMouseListener(speedSpinner, 100);

        JLabel vectorLabel = new JLabel("Вектор:");
        vectorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        x = new JSpinner(new SpinnerNumberModel(1d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1));
        addMouseListener(x, 1);
        y = new JSpinner(new SpinnerNumberModel(0d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1));
        addMouseListener(y, 1);
        z = new JSpinner(new SpinnerNumberModel(0d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1));
        addMouseListener(z, 1);

        JPanel vectorPanel = new JPanel(new GridLayout(1, 3, 1, 1));
        vectorPanel.add(x);
        vectorPanel.add(y);
        vectorPanel.add(z);

        JLabel scaleLabel = new JLabel("Расстояние:");
        scaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scaleSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0, 10, 0.01));
        addMouseListener(scaleSpinner, 10);

        shapeType = new JComboBox<>(new String[]{
                "Тетраэдр",
                "Куб",
                "Октаэдр",
                "Икосаэдр",
                "Додекаэдр",
                "Пустота"
        });

        panel.add(speedLabel);
        panel.add(speedSpinner);
        panel.add(vectorLabel);
        panel.add(vectorPanel);
        panel.add(scaleLabel);
        panel.add(scaleSpinner);
        panel.add(shapeType);

        return panel;
    }

    private static void addMouseListener(JSpinner spinner, int d){
        spinner.addMouseWheelListener(e -> {
            spinner.getModel().setValue((double)spinner.getModel().getValue() + -1 *e.getPreciseWheelRotation()/d);
        });
    }

    private static JPanel getGeoButtonPanel(int width, int height){
        JPanel panel = new JPanel(new GridLayout(1, 5, 0, 1));
        panel.setSize(width, height);

        JButton button1 = new JButton("↑");
        button1.addActionListener(e -> renderPanel.geoButtonClick(0));
        JButton button2 = new JButton("↑");
        button2.addActionListener(e -> renderPanel.geoButtonClick(1));
        JButton button3 = new JButton("↑");
        button3.addActionListener(e -> renderPanel.geoButtonClick(2));
        JButton button4 = new JButton("↑");
        button4.addActionListener(e -> renderPanel.geoButtonClick(3));
        JButton button5 = new JButton("↑");
        button5.addActionListener(e -> renderPanel.geoButtonClick(4));

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        return panel;
    }

    public static RenderPanel renderPanel = null;


    public static JSpinner speedSpinner = null;

    public static JSpinner x = null;
    public static JSpinner y = null;
    public static JSpinner z = null;

    public static JSpinner scaleSpinner = null;
    public static JComboBox<String> shapeType = null;

}
