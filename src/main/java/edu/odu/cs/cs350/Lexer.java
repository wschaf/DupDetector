/**
 * 
 * Author: rosettacode.org
 * Website: https://rosettacode.org/wiki/Compiler/lexical_analyzer#Java
 * Modified by: Christian Martinez
 * 
 */
package edu.odu.cs.cs350;
import java.util.HashMap;
import java.util.Map;

 
public class Lexer {
    private int line;
    private int pos;
    private int position;
    private char chr;
    private String s;
 
    Map<String, TokenType> keywords = new HashMap<>();
    
    /**
     * 
     */
    static class Token {
        public TokenType tokentype;
        public String value;
        public int line;
        public int pos;
        Token(TokenType token, String value, int line, int pos) {
            this.tokentype = token; this.value = value; this.line = line; this.pos = pos;
        }
        /**
         * format of tokens when printed
         */
        @Override
        public String toString() {
            String result = String.format("%5d  %5d %-15s", this.line, this.pos, this.tokentype);
            switch (this.tokentype) {
                case Integer:
                    result += String.format("  %4s", value);
                    break;
                case Identifier:
                    result += String.format(" %s", value);
                    break;
                case String:
                    result += String.format(" \"%s\"", value);
                    break;
                default:
                    break;
            }
            return result;
        }
    }
 
    /**
     * Prints error messages
     * 
     * @param line: current line number of the character
     * @param pos: position of the current character. (i.e. the column number)
     * @param msg: error message
     */
    static void error(int line, int pos, String msg) {
        if (line > 0 && pos > 0) {
            System.out.printf("%s in line %d, pos %d\n", msg, line, pos);
        } else {
            System.out.println(msg);
        }
        System.exit(1);
    }
    
    /**
     * 
     * @param source: input file data converted to strings
     */
    Lexer(String source) {
        this.line = 1;
        this.pos = 0;
        this.position = 0;
        this.s = source;
        this.chr = this.s.charAt(0);
        this.keywords.put("if", TokenType.Keyword_if);
        this.keywords.put("else", TokenType.Keyword_else);
        this.keywords.put("cout", TokenType.Keyword_print);
        this.keywords.put("putc", TokenType.Keyword_putc);
        this.keywords.put("while", TokenType.Keyword_while);
        this.keywords.put("->", TokenType.Keyword_dereference);
 
    }
    /**
     * 
     * @param expect: expected character of token type
     * @param ifyes: expected token type
     * @param ifno: not expected token type
     * @param line: current line number of the character
     * @param pos: position of the current character. (i.e. the column number)
     * @return
     */
    Token follow(char expect, TokenType ifyes, TokenType ifno, int line, int pos) {
        if (getNextChar() == expect) {
            getNextChar();
            return new Token(ifyes, "", line, pos);
        }
        if (ifno == TokenType.End_of_input) {
            error(line, pos, String.format("follow: unrecognized character: (%d) '%c'", (int)this.chr, this.chr));
        }
        return new Token(ifno, "", line, pos);
    }

    /**
     * 
     * @param line: current line number of the character
     * @param pos: position of the current character. (i.e. the column number)
     * @return
     */
    Token char_lit(int line, int pos) {
        char c = getNextChar(); // skip opening quote
        int n = (int)c;
        if (c == '\'') {
            error(line, pos, "empty character constant");
        } else if (c == '\\') {
            c = getNextChar();
            if (c == 'n') {
                n = 10;
            } else if (c == '\\') {
                n = '\\';
            } else {
                error(line, pos, String.format("unknown escape sequence \\%c", c));
            }
        }
        if (getNextChar() != '\'') {
            error(line, pos, "multi-character constant");
        }
        getNextChar();
        return new Token(TokenType.Integer, "" + n, line, pos);
    }

    /**
     * 
     * 
     * @param start: the starting character of the string
     * @param line: current line number of the character
     * @param pos: position of the current character. (i.e. the column number)
     * @return
     */
    Token string_lit(char start, int line, int pos) {
        String result = "";
        while (getNextChar() != start) {
            if (this.chr == '\u0000') {
                error(line, pos, "EOF while scanning string literal");
            }
            if (this.chr == '\n') {
                error(line, pos, "EOL while scanning string literal");
            }
            result += this.chr;
        }
        getNextChar();
        return new Token(TokenType.String, result, line, pos);
    }

    /**
     * 
     * 
     * 
     * @param line: current line number of the character
     * @param pos: position of the current character. (i.e. the column number)
     * @return Division Token or skip
     */
    Token div_or_comment(int line, int pos) {
        if(getNextChar() == '/') {
            getNextChar();
            while (true) { 
                if (this.chr == '\u0000') {
                    error(line, pos, "EOF in comment");
                }
                else if (this.chr == '\n') {
                    getNextChar();
                    return getToken();
                    
                } 
                else {
                    getNextChar();
                }
            }
        }
        else if (getNextChar() != '*') {
            return new Token(TokenType.Op_divide, "", line, pos);
        }
        getNextChar();
        while (true) { 
            if (this.chr == '\u0000') {
                error(line, pos, "EOF in comment");
            }
            else if (this.chr == '*') {
                if (getNextChar() == '/') {
                    getNextChar();
                    return getToken();
                }
            } 
            else {
                getNextChar();
            }
        }
    }

    /**
     * 
     * 
     * @param line: current line number of the character
     * @param pos: position of the current character. (i.e. the column number)
     * @return: Integer or Identifier token
     */
    Token identifier_or_integer(int line, int pos) {
        boolean is_number = true;
        String text = "";
 
        while (Character.isAlphabetic(this.chr) || Character.isDigit(this.chr) || this.chr == '_') {
            text += this.chr;
            if (!Character.isDigit(this.chr)) {
                is_number = false;
            }
            getNextChar();
        }
 
        if (text.equals("")) {
            error(line, pos, String.format("identifer_or_integer unrecopgnized character: (%d) %c", (int)this.chr, this.chr));
        }
 
        if (Character.isDigit(text.charAt(0))) {
            if (!is_number) {
                error(line, pos, String.format("invaslid number: %s", text));
            }
            return new Token(TokenType.Integer, text, line, pos);
        }
 
        if (this.keywords.containsKey(text)) {
            return new Token(this.keywords.get(text), "", line, pos);
        }
        return new Token(TokenType.Identifier, text, line, pos);
    }

    /**
     * The different types of token identified in TokenType. 
     * 
     * @return: the token identified, position, and line number
     */
    Token getToken() {
        int line, pos;
        while (Character.isWhitespace(this.chr)) {
            getNextChar();
        }
        line = this.line;
        pos = this.pos;
 
        switch (this.chr) {
            case '\u0000': return new Token(TokenType.End_of_input, "", this.line, this.pos);
            case '/': return div_or_comment(line, pos);
            case '\'': return char_lit(line, pos);
            case '<': return follow('=', TokenType.Op_lessequal, TokenType.Op_less, line, pos);
            case '>': return follow('=', TokenType.Op_greaterequal, TokenType.Op_greater, line, pos);
            case '=': return follow('=', TokenType.Op_equal, TokenType.Op_assign, line, pos);
            case '!': return follow('=', TokenType.Op_notequal, TokenType.Op_not, line, pos);
            case '|': return follow('|', TokenType.Op_or, TokenType.End_of_input, line, pos);
            case '"': return string_lit(this.chr, line, pos);
            case '{': getNextChar(); return new Token(TokenType.LeftBrace, "", line, pos);
            case '}': getNextChar(); return new Token(TokenType.RightBrace, "", line, pos);
            case '[': getNextChar(); return new Token(TokenType.LeftBracket, "", line, pos);
            case ']': getNextChar(); return new Token(TokenType.RightBracket, "", line, pos);
            case '(': getNextChar(); return new Token(TokenType.LeftParen, "", line, pos);
            case ')': getNextChar(); return new Token(TokenType.RightParen, "", line, pos);
            case '+': getNextChar(); return new Token(TokenType.Op_add, "", line, pos);
            case '-': getNextChar(); return new Token(TokenType.Op_subtract, "", line, pos);
            case '*': getNextChar(); return new Token(TokenType.Op_multiply, "", line, pos);
            case '%': getNextChar(); return new Token(TokenType.Op_mod, "", line, pos);
            case '&': getNextChar(); return new Token(TokenType.Op_and, "", line, pos);
            case ';': getNextChar(); return new Token(TokenType.Semicolon, "", line, pos);
            case ':': getNextChar(); return new Token(TokenType.Colon, "", line, pos);
            case ',': getNextChar(); return new Token(TokenType.Comma, "", line, pos);
            case '.': getNextChar(); return new Token(TokenType.Period, "", line, pos);
            case '?': getNextChar(); return new Token(TokenType.Qstn_mark, "", line, pos);
            case '#': getNextChar(); return new Token(TokenType.Hash_symbol, "", line, pos);
            case '~': getNextChar(); return new Token(TokenType.Tilda, "", line, pos);
            case '@': getNextChar(); return new Token(TokenType.At_symbol, "", line, pos);
            
 
            default: return identifier_or_integer(line, pos);
        }
    }
    
    /**
     * Get the next character, if the next character is "\n"
     * go to the next line, then reset the character position to 0
     * 
     * @return
     */
    char getNextChar() {
        this.pos++;
        this.position++;
        if (this.position >= this.s.length()) {
            this.chr = '\u0000';
            return this.chr;
        }
        this.chr = this.s.charAt(this.position);
        if (this.chr == '\n') {
            this.line++;
            this.pos = 0;
        }
        return this.chr;
    }
    
    /**
     * Print file tokens and the total number of tokens
     * Format: row number, column number, TokenType, Identifier name
     * i.e. : 1 1 Identifier x
     */
    void printTokens() {
        Token t;
        int count = 0;
        while ((t = getToken()).tokentype != TokenType.End_of_input) {
            System.out.println(t);
            count++;
        }
        System.out.println(t);
        System.out.println("Total number of tokens in this file is: " + count);
    }

}
