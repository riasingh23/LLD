package com.editor.commands;

import com.editor.actors.TextEditor;
import com.editor.formatters.TextFormattingStrategy;

public class FormatTextCommand implements TextEditorCommand {
    private TextFormattingStrategy textFormattingStrategy;
    private TextEditor textEditor;
    private String textToFormat;

    public FormatTextCommand(TextFormattingStrategy textFormattingStrategy, TextEditor textEditor,
            String textToFormat) {
        this.textEditor = textEditor;
        this.textFormattingStrategy = textFormattingStrategy;
        this.textToFormat = textToFormat;
    }

    @Override
    public void execute() {
        System.out.println("Formatting the text " + textToFormat);
        String formattedText = textFormattingStrategy.format(textToFormat);
        textEditor.setCurrString(formattedText);
    }

}
