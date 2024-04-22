import java.util.*;

public class PathFinder {
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static final char ROCK = '0';

    public static List<Node> findPath(Map map) {
        char[][] grid = map.getGrid();
        int width = map.getWidth();
        int height = map.getHeight();
        Node start = map.getStart();
        Node finish = map.getFinish();

        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[height][width];
        Node[][] parent = new Node[height][width];

        queue.offer(start);
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.equals(finish)) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int newX = current.x;
                int newY = current.y;
                // Continue sliding until hitting a wall or a rock
                while (isValid(newX + dx[i], newY + dy[i], width, height) && grid[newY + dy[i]][newX + dx[i]] != ROCK) {
                    newX += dx[i];
                    newY += dy[i];
                    if (newX == finish.x && newY == finish.y) {
                        break;
                    }
                }
                if (!visited[newY][newX]) {
                    queue.offer(new Node(newX, newY));
                    visited[newY][newX] = true;
                    parent[newY][newX] = current;
                }
            }
        }

        if (!visited[finish.y][finish.x]) {
            // Finish is unreachable
            return Collections.emptyList();
        }

        return createPath(start, finish, parent);
    }

    private static List<Node> createPath(Node start, Node finish, Node[][] parent) {
        List<Node> path = new ArrayList<>();
        Node current = finish;

        while (!current.equals(start)) {
            path.add(current);
            current = parent[current.y][current.x];
        }
        path.add(start);
        Collections.reverse(path); // Reverse the path to get the correct order
        return path;
    }

    private static boolean isValid(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
