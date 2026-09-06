public class Digit {
    private final int label;
    private final int[] grayscale;

    public Digit(int label, int[] grayscale) {
        this.label = label;
        this.grayscale = grayscale.clone();
    }

    public int getLabel() {
        return label;
    }

    public int[] getGrayscale() {
        return grayscale.clone();
    }
}
