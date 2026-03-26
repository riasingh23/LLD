package com.editor;

import com.editor.actors.TextEditor;
import com.editor.commands.FormatTextCommand;
import com.editor.commands.TextEditorCommand;
import com.editor.formatters.TextFormattingStrategy;
import com.editor.formatters.UpperCaseStrategy;
import com.editor.observers.TextConsoleObserver;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // System.out.println("Text Editor App");
        // Need to create Text editor
        TextEditor textEditor = new TextEditor();

        // text editor observers
        TextConsoleObserver textConsoleObserver = new TextConsoleObserver();

        // register all observers with text editor
        textEditor.registerObserver(textConsoleObserver);
        textEditor.setCurrString("Ria Singh!!");

        // Formatters
        TextFormattingStrategy upperCaseStrategy = new UpperCaseStrategy();

        // Commands to create the text
        TextEditorCommand formatTextCommand = new FormatTextCommand(upperCaseStrategy, textEditor,
                textEditor.getCurrString());
        formatTextCommand.execute();

        textEditor.notifyObserver();
    }
}
