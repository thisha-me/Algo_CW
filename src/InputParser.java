import java.io.*;
import java.util.*;

public class InputParser {
    public static Map parse(String filename) throws IOException, RuntimeException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<char[]> lines = new ArrayList<>();
        Node start = null, finish = null;
        int width = -1;

        String line;
        while ((line = reader.readLine()) != null) {
            char[] chars = line.toCharArray();
            if (width == -1) {
                width = chars.length;
            } else if (chars.length != width) {
                throw new RuntimeException("Invalid Puzzle! All lines must have the same width.");
            }
            lines.add(chars);
            int y = lines.size() - 1;
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c != '.' && c != '0' && c != 'S' && c != 'F') {
                    throw new RuntimeException("Invalid character in puzzle! Only '.', '0', 'S', and 'F' are allowed.");
                }
                if (c == 'S' || c == 'F') {
                    if ((c == 'S' && start != null) || (c == 'F' && finish != null)) {
                        throw new RuntimeException("Invalid Puzzle! Multiple '" + c + "' characters found");
                    }
                    Node node = new Node(x, y);
                    if (c == 'S') {
                        start = node;
                    } else {
                        finish = node;
                    }
                }
            }
        }
        reader.close();

        if(start==null || finish==null){
            throw new RuntimeException("Invalid Puzzle! Not Found 'S' or 'F' ");
        }

        int height = lines.size();
        char[][] grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            grid[i] = lines.get(i);
        }

        return new Map(grid, width, height, start, finish);
    }
}
