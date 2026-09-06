# handwritten-digit-classifier
A Java application that trains a perceptron to classify a hand-drawn 28×28 image as either zero or not zero.

Features
Interactive 28×28 pixel drawing interface
Perceptron machine-learning algorithm implemented from scratch
Training from labeled handwritten-digit data
Five randomized training epochs
Training-accuracy calculation
Mouse-drag drawing
Predict and Clear controls

How It Works
Each handwritten image is represented by 784 grayscale values—one value for every pixel in the 28×28 image.

The perceptron calculates a weighted sum:
weighted sum = weight₁ × pixel₁ + weight₂ × pixel₂ + ... + weight₇₈₄ × pixel₇₈₄

The program performs binary classification:
1 represents a zero
-1 represents a digit that is not zero

If the perceptron makes an incorrect prediction during training, its weights are updated using:
weight = weight + (actual label × pixel value)

After training, users can draw a digit on the canvas and ask the program to predict whether it is a zero.

Technologies
Java
Stanford ACM Graphics Library
Java Swing
Java AWT
CSV data processing
Perceptron machine learning
Project Structure
handwritten-digit-classifier/
├── README.md
├── .gitignore
└── src/
    ├── Digit.java
    ├── HandwrittenDigitClassifier.java
    └── Perceptron.java
    
Dataset Format
The program expects an MNIST-formatted CSV file at:

data/mnist_train.csv

Each row must contain a digit label followed by 784 grayscale pixel values:

label,pixel1,pixel2,...,pixel784

The label is a number from 0 through 9. Each pixel value ranges from 0 to 255.

The dataset is not included in this repository. The program reads the first 1,000 valid images from the provided CSV file.

Running the Project
Requirements
Java 8 or newer
Stanford ACM Java library (acm.jar)
An MNIST-formatted training CSV

Place acm.jar in the project’s main folder. Create a folder named data and place the dataset inside it as mnist_train.csv.

The completed structure should look like:

handwritten-digit-classifier/
├── acm.jar
├── data/
│   └── mnist_train.csv
└── src/
    ├── Digit.java
    ├── HandwrittenDigitClassifier.java
    └── Perceptron.java
    
After the application opens:

Wait for the model to finish training.
Draw a digit by dragging the mouse across the canvas.
Click Predict to classify the drawing.
Click Clear to reset the canvas.

Limitations
The model only distinguishes zero from nonzero digits.
The displayed percentage is training accuracy, not accuracy measured using a separate testing dataset.
Hand-drawn input may differ from the size, position, and stroke style of the training images.
A basic perceptron cannot learn every type of nonlinear pattern.

Project History
This project was originally developed during high school using Java and the Stanford ACM Graphics Library. After access to the original CodeHS account was lost, the project was reconstructed from surviving source-code excerpts and notes. The original perceptron algorithm, training approach, drawing-grid design, and classification logic were preserved, while missing setup and error-handling code was restored.
