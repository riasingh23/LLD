package com.editor.actors;

import com.editor.observers.TextEditorObserver;

public interface TextEditorSubject {
    // public static final List<TextEditorObserver> observers = null;

    // Register an observer
    public void registerObserver(TextEditorObserver observer);

    // Remove an observer
    public void removeObserver(TextEditorObserver observer);

    // Notify an observer
    public void notifyObserver();

}
