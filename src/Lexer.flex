%%
%public
%class Lexer
%unicode
%ignorecase
%line
%column
%type Token
%function nextToken

%%

[ \t\r\n]+ {
    /* Ignore whitespace between tokens. */
}

"dekut" {
    return new Token(TokenType.DEKUT, yytext(), yyline + 1, yycolumn + 1);
}

"flavoured" {
    return new Token(TokenType.FLAVOURED, yytext(), yyline + 1, yycolumn + 1);
}

"unflavoured" {
    return new Token(TokenType.UNFLAVOURED, yytext(), yyline + 1, yycolumn + 1);
}

"yoghurt" {
    return new Token(TokenType.YOGHURT, yytext(), yyline + 1, yycolumn + 1);
}

"vanilla"[ \t\r\n]+"chia" {
    return new Token(TokenType.VANILLA_CHIA, yytext(), yyline + 1, yycolumn + 1);
}

"vanilla" {
    return new Token(TokenType.VANILLA, yytext(), yyline + 1, yycolumn + 1);
}

"coffee" {
    return new Token(TokenType.COFFEE, yytext(), yyline + 1, yycolumn + 1);
}

"cheese" {
    return new Token(TokenType.CHEESE, yytext(), yyline + 1, yycolumn + 1);
}

"strawberry" {
    return new Token(TokenType.STRAWBERRY, yytext(), yyline + 1, yycolumn + 1);
}

"250"|"500"|"100" {
    return new Token(TokenType.NUMBER, yytext(), yyline + 1, yycolumn + 1);
}

"ml" {
    return new Token(TokenType.ML, yytext(), yyline + 1, yycolumn + 1);
}

[^ \t\r\n]+ {
    return new Token(TokenType.INVALID, yytext(), yyline + 1, yycolumn + 1);
}

<<EOF>> {
    return new Token(TokenType.EOF, "", yyline + 1, yycolumn + 1);
}
