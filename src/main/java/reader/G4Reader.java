package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class G4Reader {

    private static BufferedReader reader;

    public static String readParser(String path) throws IllegalArgumentException {
        if (!path.endsWith(".g4")) {
            throw new IllegalArgumentException("path do not end by .g4. path " + path);
        }
        var sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(path));
            List<String> tempLines = new ArrayList<>();
            reader.lines().forEach(tempLines::add);
            tempLines.forEach(e -> sb.append(e).append("\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       return sb.toString();
    }
}