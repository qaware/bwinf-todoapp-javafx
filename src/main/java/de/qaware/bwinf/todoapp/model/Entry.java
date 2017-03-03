package de.qaware.bwinf.todoapp.model;

/**
 * A entry.
 */
public class Entry {
    private Progress progress;
    private final String label;

    public Entry(Progress progress, String label) {
        this.progress = progress;
        this.label = label;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Progress getProgress() {
        return progress;
    }

    public String getLabel() {
        return label;
    }
}
