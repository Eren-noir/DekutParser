DEKUT PRODUCT DESCRIPTION PARSER
================================

This project contains a JFlex lexer and a table-driven LL(1) parser.

CURRENT GRAMMAR
---------------
S      -> BRAND P F Q
BRAND  -> DEKUT
P      -> FLAVOURED YOGHURT
       | UNFLAVOURED YOGHURT
F      -> COFFEE
       | CHEESE
       | STRAWBERRY
       | VANILLA
       | VANILLA_CHIA
Q      -> N ML
N      -> 250
       | 500
       | 100

VANILLA_CHIA is treated as one terminal. The lexer recognises the two-word
phrase "Vanilla Chia" as one token, preserving the space in the original
lexeme. The scanner is case-insensitive.

IMPORTANT: REGENERATE THE JFLEX SCANNER
---------------------------------------
The generated/Lexer.java file in the archive is the previously generated
scanner. Since Lexer.flex has changed, regenerate Lexer.java before compiling.

From PowerShell in the DekutParser project directory:

  java -jar "C:\Users\USER\Downloads\jflex-1.9.1\jflex-1.9.1\lib\jflex-full-1.9.1.jar" -d ".\generated" ".\src\Lexer.flex"

Then compile:

  javac -d out .\src\TokenType.java .\src\Token.java .\generated\Lexer.java .\src\Parser.java .\test\ParserTest.java

Then run:

  java -cp out ParserTest

A build.ps1 script is also included. If the JFlex JAR is not at the
previously used path, edit the JFlexJar variable at the top of that script.

TESTS
-----
ParserTest.java contains five valid and four invalid test cases.
The expected behaviour is:
- Vanilla and Vanilla Chia are accepted as flavour values.
- Existing supported flavours and package sizes remain accepted.
- Unsupported flavour Mango, unsupported size 700, missing yoghurt type,
  and missing ML unit are rejected.

EXTENSION NOTE
--------------
Only Vanilla and Vanilla Chia have been added based on the product details
provided by the user. Other products or flavours should only be added after
their packaging/details have been confirmed.
