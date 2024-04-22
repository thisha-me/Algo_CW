import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static Map parse(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        List<char[]> lines = new ArrayList<>();
        Node start = null, finish = null;

        while ((line = reader.readLine()) != null) {
            lines.add(line.toCharArray());
            int y = lines.size() - 1;
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'S') {
                    start = new Node(x, y);
                } else if (line.charAt(x) == 'F') {
                    finish = new Node(x, y);
                }
            }
        }

        reader.close();

        int height = lines.size();
        int width = lines.get(0).length;
        char[][] grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            grid[i] = lines.get(i);
        }

        return new Map(grid, width, height, start, finish);
    }
}
