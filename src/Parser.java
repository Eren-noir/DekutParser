import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Parser {

    private final Lexer lexer;
    private Token lookahead;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        this.lookahead = lexer.nextToken();
    }

    public boolean parse() throws IOException {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("$");
        stack.push("S");

        while (!stack.isEmpty()) {
            String top = stack.pop();
            String current = getTerminal(lookahead);

            if (top.equals("$")) {
                if (current.equals("$")) {
                    System.out.println("ACCEPTED: Valid DEKUT product description.");
                    return true;
                }
                return syntaxError("Unexpected extra input");
            }

            if (isTerminal(top)) {
                if (top.equals(current)) {
                    lookahead = lexer.nextToken();
                } else {
                    return syntaxError("Expected " + top);
                }
            } else {
                String[] production = getProduction(top, current);
                if (production == null) {
                    return syntaxError("No production for " + top);
                }

                for (int i = production.length - 1; i >= 0; i--) {
                    stack.push(production[i]);
                }
            }
        }
        return false;
    }

    // Non-empty cells of the LL(1) predictive parsing table.
    private String[] getProduction(String nonTerminal, String input) {
        switch (nonTerminal) {
            case "S":
                if (input.equals("DEKUT"))
                    return new String[]{"BRAND", "P", "F", "Q"};
                break;

            case "BRAND":
                if (input.equals("DEKUT"))
                    return new String[]{"DEKUT"};
                break;

            case "P":
                if (input.equals("FLAVOURED"))
                    return new String[]{"FLAVOURED", "YOGHURT"};
                if (input.equals("UNFLAVOURED"))
                    return new String[]{"UNFLAVOURED", "YOGHURT"};
                break;

            case "F":
                if (input.equals("COFFEE"))
                    return new String[]{"COFFEE"};
                if (input.equals("CHEESE"))
                    return new String[]{"CHEESE"};
                if (input.equals("STRAWBERRY"))
                    return new String[]{"STRAWBERRY"};
                if (input.equals("VANILLA"))
                    return new String[]{"VANILLA"};
                if (input.equals("VANILLA_CHIA"))
                    return new String[]{"VANILLA_CHIA"};
                break;

            case "Q":
                if (input.equals("250") || input.equals("500") || input.equals("100"))
                    return new String[]{"N", "ML"};
                break;

            case "N":
                if (input.equals("250"))
                    return new String[]{"250"};
                if (input.equals("500"))
                    return new String[]{"500"};
                if (input.equals("100"))
                    return new String[]{"100"};
                break;
        }
        return null;
    }

    private String getTerminal(Token token) {
        if (token.getType() == TokenType.NUMBER) {
            return token.getLexeme();
        }
        if (token.getType() == TokenType.EOF) {
            return "$";
        }
        return token.getType().name();
    }

    private boolean isTerminal(String symbol) {
        return symbol.equals("DEKUT") ||
               symbol.equals("FLAVOURED") ||
               symbol.equals("UNFLAVOURED") ||
               symbol.equals("YOGHURT") ||
               symbol.equals("COFFEE") ||
               symbol.equals("CHEESE") ||
               symbol.equals("STRAWBERRY") ||
               symbol.equals("VANILLA") ||
               symbol.equals("VANILLA_CHIA") ||
               symbol.equals("250") ||
               symbol.equals("500") ||
               symbol.equals("100") ||
               symbol.equals("ML");
    }

    private boolean syntaxError(String message) {
        System.out.println(
            "REJECTED: " + message +
            " near '" + lookahead.getLexeme() +
            "' at line " + lookahead.getLine() +
            ", column " + lookahead.getColumn()
        );
        return false;
    }
}
