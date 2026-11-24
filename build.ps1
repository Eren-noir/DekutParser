# Run this script from any location; it builds the DEKUT parser project.
$ErrorActionPreference = "Stop"
$ProjectDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $ProjectDir

$JFlexJar = "C:\Users\USER\Downloads\jflex-1.9.1\jflex-1.9.1\lib\jflex-full-1.9.1.jar"

if (-not (Test-Path $JFlexJar)) {
    $JFlexJar = Read-Host "Enter the full path to jflex-full-1.9.1.jar"
}

if (-not (Test-Path $JFlexJar)) {
    throw "JFlex JAR not found. Check the path and run this script again."
}

New-Item -ItemType Directory -Force -Path ".\generated", ".\out" | Out-Null

Write-Host "`n[1/3] Generating Lexer.java with JFlex..."
java -jar $JFlexJar -d ".\generated" ".\src\Lexer.flex"
if ($LASTEXITCODE -ne 0) { throw "JFlex generation failed." }

Write-Host "`n[2/3] Compiling Java files..."
javac -d out .\src\TokenType.java .\src\Token.java .\generated\Lexer.java .\src\Parser.java .\test\ParserTest.java
if ($LASTEXITCODE -ne 0) { throw "Java compilation failed." }

Write-Host "`n[3/3] Running parser tests..."
java -cp out ParserTest
if ($LASTEXITCODE -ne 0) { throw "Parser tests failed to execute." }
