package com.editor.formatters;

public class LowerCaseStrategy implements TextFormattingStrategy {

    @Override
    public String format(String text) {
        System.out.println("Converting to lower case " + text);
        return text.toLowerCase();
    }

}
