package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class G4Reader {

    private static BufferedReader reader;

    public static Stream<String> readFile(String path) throws FileNotFoundException, IllegalArgumentException {
        if (!path.endsWith(".g4")) {
            throw new IllegalArgumentException("path do not end by .g4. path " + path);
        }
       reader = new BufferedReader(new FileReader(path));
       return reader.lines();
    }

    public static void clouseReader() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}