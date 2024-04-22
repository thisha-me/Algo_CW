import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Map map;
        try {
            String filename="benchmark_series/puzzle_640.txt";
            map=InputParser.parse(filename);
        }catch (IOException e){
            System.out.println("File Not Found : "+e.getMessage());
            return;
        }

        long startTime = System.nanoTime();

        List<Node> path=PathFinder.findPath(map);

        long endTime = System.nanoTime();
        double durationInNano = (double)(endTime - startTime);  // Total execution time in nanoseconds
        double durationInMillis = durationInNano / 1000000;  // Total execution time in millisecond

        printDirections(path);
        System.out.printf("Execution time in nanoseconds: %.0f ns\n", durationInNano);
        System.out.printf("Execution time in milliseconds: %.3f ms\n", durationInMillis);

    }

    private static void printDirections(List<Node> path){
        if (path == null) {
            System.out.println("No path found.");
        } else {
            Node prev = null;
            int step = 1;
            for (Node node : path) {
                if (prev != null) {
                    String direction = getDirection(prev, node);
                    System.out.println(step + ". Move " + direction + " to "+node);
                    step++;
                } else {
                    System.out.println(step + ". Start at "+node);
                    step++;
                }
                prev = node;
            }
            System.out.println(step + ". Done!");
        }
    }

    private static String getDirection(Node from, Node to) {
        if (from.x < to.x) return "right";
        if (from.x > to.x) return "left";
        if (from.y < to.y) return "down";
        return "up";
    }
}
