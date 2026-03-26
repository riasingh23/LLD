package com.editor.formatters;

public class UpperCaseStrategy implements TextFormattingStrategy {

    @Override
    public String format(String text) {
        System.out.println("Converting to upper case " + text);
        return text.toUpperCase();
    }
}
