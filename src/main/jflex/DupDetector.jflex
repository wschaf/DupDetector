package edu.odu.cs.cs350;
//@SuppressWarnings("unused")

%%

%public
%class LexerAnalyzer

%unicode
%line
%column

%type Token

%{
  StringBuffer string = new StringBuffer();
  
  private Token symbol(TokenType type) {
    return new Token(type, yyline+1, yycolumn+1);
  }

  private Token symbol(TokenType type, String value) {
    return new Token(type, yyline+1, yycolumn+1, value);
  }
  
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comments = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
ConstantNumbers = [0-9][0-9]* | [0-9][_0-9]*[0-9]

/* string literals */
StringCharacter = [^\r\n\"\\]

/* Strings */
%state STRING

/* Rules */
%%

<YYINITIAL> {

  /* keywords */
  "while"                       { return symbol(TokenType.WHILE); }
  "if"                          { return symbol(TokenType.IF); }
  "else"                        { return symbol(TokenType.ELSE); }
  "else if"                     { return symbol(TokenType.ELSE_IF); }
  "cout"                        { return symbol(TokenType.COUT); }
  "cin"                         { return symbol(TokenType.CIN); }
  "cerr"                        { return symbol(TokenType.CERR); }
  "endl"                        { return symbol(TokenType.ENDL); }
  "break"                       { return symbol(TokenType.BREAK); }
  "switch"                      { return symbol(TokenType.SWITCH); }
  "return"                      { return symbol(TokenType.RETURN); }
  "continue"                    { return symbol(TokenType.CONTINUE); }
  "do"                          { return symbol(TokenType.DO); }
  "const"                       { return symbol(TokenType.CONST); }
  "int"                         { return symbol(TokenType.INT); }
  "double"                      { return symbol(TokenType.DOUBLE); }
  "float"                       { return symbol(TokenType.FLOAT); }
  "for"                         { return symbol(TokenType.FOR); }
  "default"                     { return symbol(TokenType.DEFAULT); }
  "include"                     { return symbol(TokenType.INCLUDE); }
  "final"                       { return symbol(TokenType.FINAL); }
  "override"                    { return symbol(TokenType.OVERRIDE); }
  "class"                       { return symbol(TokenType.CLASS); }
  "delete"                      { return symbol(TokenType.DELETE); }
  "new"                         { return symbol(TokenType.NEW); }
  "enum"                        { return symbol(TokenType.ENUM); }
  "sizeof"                      { return symbol(TokenType.SIZEOF); }
  "namespace"                   { return symbol(TokenType.NAMESPACE); }
  "using"                       { return symbol(TokenType.USING); }
  "virtual"                     { return symbol(TokenType.VIRTUAL); }
  "inherets"                    { return symbol(TokenType.INHERETS); }
  "void"                        { return symbol(TokenType.VOID); }
  "public"                      { return symbol(TokenType.PUBLIC); }
  "string"                      { return symbol(TokenType.STRING); }
  "inline"                      { return symbol(TokenType.INLINE); }
  "long"                        { return symbol(TokenType.LONG); }

  /* operators */
  "="                           { return symbol(TokenType.ASSIGN_OP); }
  "<<"                          { return symbol(TokenType.OSTREAM); }
  ">>"                          { return symbol(TokenType.ISTREAM); }
  "++"                          { return symbol(TokenType.INCREMENT); }
  "--"                          { return symbol(TokenType.DECREMENT); }
  "-"                           { return symbol(TokenType.SUBTRACT); }
  "+"                           { return symbol(TokenType.ADD); }
  "/"                           { return symbol(TokenType.DIVIDE); }
  "*"                           { return symbol(TokenType.MULTIPLY); }
  "%"                           { return symbol(TokenType.MODULO); }
  "&"                           { return symbol(TokenType.REFERENCE_SYM); }
  
  /* Boolean Keywords */
  "true"                        { return symbol(TokenType.TRUE); }
  "false"                       { return symbol(TokenType.FALSE); }
  "bool"                        { return symbol(TokenType.BOOL); }

  /* Boolean Operators */
  "<"                           { return symbol(TokenType.LT); }
  ">"                           { return symbol(TokenType.GT); }
  "<="                          { return symbol(TokenType.LEQ); }
  ">="                          { return symbol(TokenType.GEQ); }
  "=="                          { return symbol(TokenType.EQ_EQ); }
  "&&"                          { return symbol(TokenType.AND_SYMBOL); }
  "||"                          { return symbol(TokenType.OR_SYMBOL); }
  "!="                          { return symbol(TokenType.NOT_EQUAL); }
  "?"                           { return symbol(TokenType.QUESTION_MARK); }

  /* Other Tokens */
  ";"                           { return symbol(TokenType.SEMI_COLON); }
  ","                           { return symbol(TokenType.COMMA); }
  "."                           { return symbol(TokenType.PERIOD); }
  "#"                           { return symbol(TokenType.HASH_SYMBOL); }
  "~"                           { return symbol(TokenType.TILDA); }
  "@"                           { return symbol(TokenType.AT_SYMBOL); }
  "{"                           { return symbol(TokenType.LEFT_BRACE); }
  "}"                           { return symbol(TokenType.RIGHT_BRACE); }
  "["                           { return symbol(TokenType.LEFT_BRACKET); }
  "]"                           { return symbol(TokenType.RIGHT_BRACKET); }
  "("                           { return symbol(TokenType.LEFT_PAREN); }
  ")"                           { return symbol(TokenType.RIGHT_PAREN); }

  /* string literal */
  \"                            { yybegin(STRING); string.setLength(0); }

  /* numeric literals */
  {ConstantNumbers}             { return symbol(TokenType.CONSTANT_NUMBERS, yytext()); }

  /* Identifiers */
  {Identifier}                  { return symbol(TokenType.IDENTIFIER, yytext()); } 
  
  /* whitespaces */
  {WhiteSpace}                  {/* Ignore */}  
  
  /* comments */
  {Comments}                    {/* Ignore */}
}

<STRING> {
 \"                             { yybegin(YYINITIAL); return symbol(TokenType.STRING_LITERAL, string.toString()); }

 {StringCharacter}+             { string.append( yytext() ); }

 "\\b"                          { string.append( '\b' ); }
 "\\t"                          { string.append( '\t' ); }
 "\\n"                          { string.append( '\n' ); }
 "\\f"                          { string.append( '\f' ); }
 "\\r"                          { string.append( '\r' ); }
 "\\\""                         { string.append( '\"' ); }
 "\\'"                          { string.append( '\'' ); }
 "\\\\"                         { string.append( '\\' ); }


 \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
 {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}


/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(TokenType.EOF); }
