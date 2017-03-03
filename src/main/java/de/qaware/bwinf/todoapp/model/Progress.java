package de.qaware.bwinf.todoapp.model;

public enum Progress {
    TODO,
    IN_PROGRESS,
    DONE;

    /**
     * Returns the next progress after this progress.
     * <p>
     * Progress cycle is: todo -> in progress -> done -> in progress -> ...
     *
     * @return Next progress.
     */
    public Progress next() {
        switch (this) {
            case TODO:
                return IN_PROGRESS;
            case IN_PROGRESS:
                return DONE;
            case DONE:
                return IN_PROGRESS;
            default:
                throw new IllegalStateException("Invalid progress: " + this);
        }
    }
}
