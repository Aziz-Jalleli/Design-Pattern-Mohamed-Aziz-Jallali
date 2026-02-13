package org.pi;

public class caretaker {

    private memento memento;

    public void save(memento memento) {
        this.memento = memento;
    }

    public memento getSavedState() {
        return memento;
    }
}
