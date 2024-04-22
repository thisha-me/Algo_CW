public class Map {
    private final char[][] grid;
    private final int width;
    private final int height;
    private final Node start;
    private final Node finish;

    public Map(char[][] grid, int width, int height, Node start, Node finish) {
        this.grid = grid;
        this.width = width;
        this.height = height;
        this.start = start;
        this.finish = finish;
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Node getStart() {
        return start;
    }

    public Node getFinish() {
        return finish;
    }
}
