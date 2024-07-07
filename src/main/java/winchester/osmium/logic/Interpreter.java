package winchester.osmium.logic;

import winchester.osmium.logic.classification.Statement;
import winchester.osmium.logic.classification.Token;
import winchester.osmium.logic.preprocess.Lexer;
import winchester.osmium.logic.preprocess.Parser;

public class Interpreter {
    private final String orginalInput;
    private String[] input;
    private Token[] tokens;
    private Statement[] statements;
    
    public Interpreter(String inputCode) {
        this.orginalInput = inputCode;
    }

    public void runLexer() {
        Lexer lexer = new Lexer(this.orginalInput);

        try { lexer.convertToTokens(); }
        catch (Exception err) {
            System.err.println(err.getMessage());
            return;
        }

        this.tokens = lexer.getTokens().clone();
    }

    public void runParser() {
        Parser parser = new Parser(this.tokens);

        parser.tokensToStatements();
        parser.classifyStatements();

        this.statements = parser.getParsedStatements().clone();

        for (Statement statement : statements) {
            System.out.println(statement.toString());
        }
    }

}
