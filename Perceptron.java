public class Perceptron {
    private final double[] weights;

    public Perceptron(int n) {
        this.weights = new double[n];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 0.0;
        }
    }

    public double weightedSum(int[] grayscale) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += this.weights[i] * grayscale[i];
        }
        return sum;
    }

    public int predict(int[] grayscale) {
        if (weightedSum(grayscale) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public void train(int[] grayscale, int actualLabel) {
        int prediction = predict(grayscale);
        if (prediction != actualLabel) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] += actualLabel * grayscale[i];
            }
        }
    }
}
