import java.awt.*;
import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class HandwrittenDigitClassifier extends GraphicsProgram {
    private static final int IMAGE_SIZE = 28;
    private static final int PIXEL_SIZE = 10;
    private static final int TOTAL_LINES = 1000;
    private static final int EPOCHS = 5;
    private static final String DATA_FILE = "data/mnist_train.csv";

    private GRect[] pixels = new GRect[IMAGE_SIZE * IMAGE_SIZE];
    private Perceptron perceptron;

    public void init() {
        setSize(IMAGE_SIZE * PIXEL_SIZE, IMAGE_SIZE * PIXEL_SIZE + 70);
        add(new JButton("Predict"), SOUTH);
        add(new JButton("Clear"), SOUTH);

        for (int i = 0; i < IMAGE_SIZE * IMAGE_SIZE; i++) {
            int x = i % IMAGE_SIZE * PIXEL_SIZE;
            int y = i / IMAGE_SIZE * PIXEL_SIZE;
            GRect pixel = new GRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
            pixel.setFilled(true);
            pixel.setColor(Color.BLACK);
            pixels[i] = pixel;
            add(pixel);
        }

        addActionListeners();
        addMouseListeners();
    }

    public void run() {
        ArrayList<Digit> training = new ArrayList<Digit>();
        perceptron = new Perceptron(IMAGE_SIZE * IMAGE_SIZE);

        try {
            Scanner scanner = new Scanner(new File(DATA_FILE));
            int linesRead = 0;

            while (scanner.hasNextLine() && linesRead < TOTAL_LINES) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length != IMAGE_SIZE * IMAGE_SIZE + 1) {
                    continue;
                }

                try {
                    int label = Integer.parseInt(parts[0]);
                    int[] grayscale = new int[parts.length - 1];

                    for (int k = 0; k < parts.length - 1; k++) {
                        grayscale[k] = Integer.parseInt(parts[k + 1]);
                    }

                    Digit digit = new Digit(label, grayscale);
                    training.add(digit);
                    linesRead++;
                } catch (NumberFormatException e) {
                    // Skip a header or an incorrectly formatted row.
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            println("Could not find " + DATA_FILE);
            println("Add an MNIST CSV file before running the program.");
            return;
        }

        if (training.isEmpty()) {
            println("The data file did not contain any valid digit records.");
            return;
        }

        for (int epoch = 0; epoch < EPOCHS; epoch++) {
            Collections.shuffle(training);
            for (Digit d : training) {
                int binaryLabel = (d.getLabel() == 0) ? 1 : -1;
                perceptron.train(d.getGrayscale(), binaryLabel);
            }
        }

        int trainCount = 0;
        for (Digit d : training) {
            int binaryLabel = (d.getLabel() == 0) ? 1 : -1;
            int prediction = perceptron.predict(d.getGrayscale());
            if (prediction == binaryLabel) {
                trainCount++;
            }
        }

        double trainAcc = (double) trainCount / training.size() * 100;
        println("Correct percentage: " + trainAcc + "%");
    }

    public void mouseDragged(MouseEvent e) {
        GPoint point = new GPoint(e.getPoint());
        GObject object = getElementAt(point);

        if (object instanceof GRect) {
            GRect rectangle = (GRect) object;
            rectangle.setColor(Color.WHITE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Predict")) {
            int[] grayscale = new int[IMAGE_SIZE * IMAGE_SIZE];

            for (int i = 0; i < IMAGE_SIZE * IMAGE_SIZE; i++) {
                grayscale[i] = (pixels[i].getColor() == Color.WHITE) ? 255 : 0;
            }

            int predicted = perceptron.predict(grayscale);
            if (predicted == 1) {
                println("It is a 0");
            } else {
                println("It is not a 0");
            }
        } else if (command.equals("Clear")) {
            for (int i = 0; i < IMAGE_SIZE * IMAGE_SIZE; i++) {
                pixels[i].setColor(Color.BLACK);
            }
        }
    }
}
