import java.io.StringReader;

public class ParserTest {

    public static void main(String[] args) throws Exception {
        String[][] tests = {
            {"VALID", "Dekut Flavoured Yoghurt Vanilla 500 ml"},
            {"VALID", "Dekut Flavoured Yoghurt Vanilla Chia 500 ml"},
            {"VALID", "Dekut Flavoured Yoghurt Coffee 500 ml"},
            {"VALID", "Dekut Unflavoured Yoghurt Cheese 250 ml"},
            {"VALID", "Dekut Flavoured Yoghurt Strawberry 100 ml"},

            {"INVALID", "Dekut Flavoured Yoghurt Mango 500 ml"},
            {"INVALID", "Dekut Flavoured Yoghurt Coffee 700 ml"},
            {"INVALID", "Dekut Yoghurt Coffee 250 ml"},
            {"INVALID", "Dekut Flavoured Yoghurt Coffee 500"}
        };

        int passed = 0;

        for (String[] test : tests) {
            String expected = test[0];
            String input = test[1];

            System.out.println("\nExpected " + expected + ": " + input);

            Lexer lexer = new Lexer(new StringReader(input));
            Parser parser = new Parser(lexer);
            boolean accepted = parser.parse();

            boolean behavedAsExpected =
                (expected.equals("VALID") && accepted) ||
                (expected.equals("INVALID") && !accepted);

            if (behavedAsExpected) {
                passed++;
                System.out.println("TEST RESULT: PASS");
            } else {
                System.out.println("TEST RESULT: FAIL");
            }
        }

        System.out.println("\nPassed " + passed + " of " + tests.length + " tests.");
    }
}
