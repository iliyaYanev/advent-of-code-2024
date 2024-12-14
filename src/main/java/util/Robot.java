package util;

public class Robot {

    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;

    private final int vx;
    private final int vy;

    private int x;
    private int y;

    public Robot(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void move(int time) {
        x = (x + time * vx) % WIDTH;
        y = (y + time * vy) % HEIGHT;

        if (x < 0) {
            x += WIDTH;
        }

        if (y < 0) {
            y += HEIGHT;
        }
    }

    public boolean isInQuadrant() {
        return x != WIDTH / 2 && y != HEIGHT / 2;
    }

    public int getQuadrant() {
        int quadrantX = x / (WIDTH / 2 + 1);
        int quadrantY = y / (HEIGHT / 2 + 1);

        return 2 * quadrantY + quadrantX;
    }
}
