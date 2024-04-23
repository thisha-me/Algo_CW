import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Map map;
        try {
            String filename="benchmark_series/puzzle_20.txt";
            map=InputParser.parse(filename);
            System.out.println("File Name: "+filename);
        }catch (IOException e){
            System.out.println("File Not Found : "+e.getMessage());
            return;
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
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

//        runAll(10);

    }

    private static void printDirections(List<Node> path){
        if (path.isEmpty()) {
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

    private static void runAll(int runCount){
        String[] puzzles={"puzzle_10", "puzzle_20", "puzzle_40", "puzzle_80", "puzzle_160", "puzzle_320","puzzle_640", "puzzle_1280","puzzle_2560"};

        Map map;
        for(String filename:puzzles){

            try {
                filename="benchmark_series/"+filename+".txt";
                System.out.println("Running trials for: " + filename);
                map=InputParser.parse(filename);

                double totalDurationInMillis = 0;  // Total execution time for all runs

                for(int i=0; i<runCount; i++){
                    long startTime = System.nanoTime();

                    List<Node> path=PathFinder.findPath(map);

                    long endTime = System.nanoTime();
                    double durationInNano = (double)(endTime - startTime);  // Total execution time in nanoseconds
                    double durationInMillis = durationInNano / 1000;  // Total execution time in millisecond

                    totalDurationInMillis += durationInMillis;

//                    System.out.printf("Execution time for run %d: %.15f ms\n", i+1, durationInMillis);
                }

                double averageDurationInMillis = totalDurationInMillis / runCount;  // Average execution time in millisecond

                System.out.printf("Average execution time for %s runs: %.15f micro seconds\n", runCount, averageDurationInMillis);
                System.out.println();

            }catch (IOException e){
                System.out.println("File Not Found : "+e.getMessage());
                return;
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
                return;
            }
        }
    }


}
