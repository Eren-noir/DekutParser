import java.io.StringReader;

public class LexerTest {
    public static void main(String[] args) throws Exception {
        String input = "Dekut Flavoured Yoghurt Vanilla Chia 500 ml";
        Lexer lexer = new Lexer(new StringReader(input));

        Token token;
        do {
            token = lexer.nextToken();
            System.out.println(token);
        } while (token.getType() != TokenType.EOF);
    }
}
