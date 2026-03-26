package com.editor.actors;

import java.util.ArrayList;
import java.util.List;

import com.editor.observers.TextEditorObserver;

public class TextEditor implements TextEditorSubject {

    private List<TextEditorObserver> observerList = new ArrayList<>();
    private String currString = "";

    @Override
    public void registerObserver(TextEditorObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(TextEditorObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (TextEditorObserver observer : observerList) {
            observer.update(currString);
        }
    }

    public String getCurrString() {
        return currString;
    }

    public void setCurrString(String currString) {
        this.currString = currString;
    }

}
