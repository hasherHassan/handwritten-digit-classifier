# handwritten-digit-classifier

A Java application that uses a perceptron to classify a hand-drawn 28×28 image as either zero or not zero. Users draw on an interactive pixel grid, and the trained model processes the drawing to produce a binary prediction.

## Features

* Interactive 28×28 pixel drawing interface
* Perceptron machine-learning algorithm implemented from scratch
* Training using labeled handwritten-digit data
* Five randomized training epochs
* Training-accuracy calculation
* Mouse-drag drawing
* Predict and Clear controls

## Machine-Learning Design

* Each handwritten image is represented by 784 grayscale pixel values.
* The perceptron stores one weight for each pixel.
* A weighted sum is calculated using the image’s pixel values and the model’s weights.
* The value `1` represents a zero.
* The value `-1` represents a digit that is not zero.
* Weights are updated whenever the model makes an incorrect prediction.

The weight-update formula is:

```text
weight = weight + (actual label × pixel value)
```

## Project Structure

```text
handwritten-digit-classifier/
├── README.md
├── .gitignore
└── src/
    ├── Digit.java
    ├── HandwrittenDigitClassifier.java
    └── Perceptron.java
```

## Technologies

* Java
* Stanford ACM Graphics Library
* Java Swing
* Java AWT
* CSV data processing
* Perceptron machine learning

## Dataset Format

* The program expects an MNIST-formatted CSV file at `data/mnist_train.csv`.
* Each row begins with a digit label followed by 784 grayscale pixel values.
* Labels range from 0 through 9.
* Pixel values range from 0 through 255.
* The program trains using the first 1,000 valid images.
* The training dataset is not included in this repository.

Each CSV row should follow this format:

```text
label,pixel1,pixel2,...,pixel784
```

## Controls

* Drag the mouse across the grid to draw a digit.
* Click **Predict** to classify the drawing as zero or not zero.
* Click **Clear** to reset the drawing grid.
* Wait for the model to finish training before making a prediction.

## Current Limitations

* The model only distinguishes zero from nonzero digits.
* The displayed percentage represents training accuracy rather than accuracy on a separate testing dataset.
* Hand-drawn input may differ from the position, size, and stroke style of the training images.
* A basic perceptron cannot learn every type of nonlinear pattern.
* The ACM Java library and an MNIST-formatted CSV file are required to run the project.

## Project History

This project was originally developed during high school as a Java machine-learning exercise. It demonstrates perceptron training, binary classification, array processing, CSV data loading, graphical input, and event handling. After access to the original CodeHS account was lost, the project was reconstructed from surviving source-code excerpts and notes. The original perceptron algorithm, training approach, drawing-grid design, and classification logic were preserved, while missing setup and error-handling code was restored.

