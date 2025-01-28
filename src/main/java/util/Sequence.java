package util;

public record Sequence(int a, int b, int c, int d) {

    public int key() {
        return 6859 * a + 361 * b + 19 * c + d;
    }
}
