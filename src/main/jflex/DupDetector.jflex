package edu.odu.cs.cs350;
//@SuppressWarnings("unused")

%%

%public
%class LexerAnalyzer


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
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
ConstantNumbers = [0-9][0-9]* | [0-9][_0-9]*[0-9]

/* Strings */
%state STRING

/* Rules */
%%


  /* keywords */
  "while"                     { return symbol(TokenType.WHILE); }
  "if"                     { return symbol(TokenType.IF); }
  "else"                     { return symbol(TokenType.ELSE); }
  "else if"                     { return symbol(TokenType.ELSE_IF); }
  "cout"                     { return symbol(TokenType.COUT); }
  "cin"                     { return symbol(TokenType.CIN); }
  "cerr"                     { return symbol(TokenType.CERR); }
  "break"                     { return symbol(TokenType.BREAK); }
  "switch"                     { return symbol(TokenType.SWITCH); }
  "return"                     { return symbol(TokenType.RETURN); }

  /* operators */
  "="                           { return symbol(TokenType.ASSIGN); }
  "<<"                           { return symbol(TokenType.OSTREAM); }
  ">>"                           { return symbol(TokenType.ISTREAM); }
  "++"                           { return symbol(TokenType.INCREMENT); }
  "--"                           { return symbol(TokenType.DECREMENT); }
  "-"                           { return symbol(TokenType.SUBTRACT); }
  "+"                           { return symbol(TokenType.ADD); }
  "/"                           { return symbol(TokenType.DIVIDE); }
  "*"                           { return symbol(TokenType.MULTIPLY); }
  "%"                           { return symbol(TokenType.MODULO); }
  "&"                           { return symbol(TokenType.REFERENCE_SYM); }
  
  /* Boolean Operators */
  "<"                           { return symbol(TokenType.LT); }
  ">"                           { return symbol(TokenType.GT); }
  "<="                           { return symbol(TokenType.LEQ); }
  ">="                           { return symbol(TokenType.GEQ); }
  "=="                           { return symbol(TokenType.EQ_EQ); }
  "&&"                           { return symbol(TokenType.AND_SYMBOL); }
  "||"                           { return symbol(TokenType.OR_SYMBOL); }
  "!="                           { return symbol(TokenType.NOT_EQUAL); }
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

  
  {ConstantNumbers}            { return symbol(TokenType.CONSTANT_NUMBERS, yytext()); }

  {Identifier}                   { return symbol(TokenType.IDENTIFIER, yytext()); } 
  
  {WhiteSpace}                  {/* Ignore */}  
  
  /* comments */
  {Comments}                    {/* Ignore */}


  <STRING> {
      \"                             { yybegin(YYINITIAL);
                                       return symbol(TokenType.STRING_LITERAL,
                                       string.toString()); }
      [^\n\r\"\\]+                   { string.append( yytext() ); }
      \\t                            { string.append('\t'); }
      \\n                            { string.append('\n'); }

      \\r                            { string.append('\r'); }
      \\\"                           { string.append('\"'); }
      \\                             { string.append('\\'); }
    }


/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(TokenType.EOF); }
