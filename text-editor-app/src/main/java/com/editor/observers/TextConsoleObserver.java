package com.editor.observers;

public class TextConsoleObserver implements TextEditorObserver {

    @Override
    public void update(String text) {
        System.out.println("Text upadated in console " + text);
    }

}
