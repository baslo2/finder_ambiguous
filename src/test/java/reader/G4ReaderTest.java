package reader;


import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

public class G4ReaderTest {

    private final static String PARSER = "/SQLParser.g4";
    private final static String RESOUCE = new File("src/test/resources").getAbsolutePath();

    @Test
    void readParserTest() {
        assertThrows(IllegalArgumentException.class,
                () -> G4Reader.readParser(RESOUCE + PARSER.replace(".g4", ".mp4")));
    }
}