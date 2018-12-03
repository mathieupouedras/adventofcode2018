package infrastructure;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void test() {
        String input = "#123 @ 355,223: 5x4";

        String[] tokens = input.split(" ");

        String positon = tokens[2];

    }


}
