package de.qaware.bwinf.todoapp.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Model of the application.
 */
public class MainModel {
    private List<Entry> entries = new ArrayList<>();

    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * Loads the entries from the given file.
     *
     * @param file File.
     * @throws IOException If something went wrong while loading.
     */
    public void load(Path file) throws IOException {
        if (!Files.exists(file)) {
            return;
        }

        List<String> lines = Files.readAllLines(file);

        for (String line : lines) {
            // Format is: <progress>;<label>
            String[] parts = line.split(";", 2);
            Progress progress = Progress.valueOf(parts[0]);
            Entry entry = new Entry(progress, parts[1]);
            entries.add(entry);
        }
    }

    /**
     * Saves the entries to the given file.
     *
     * @param file File.
     * @throws IOException If something went wrong while saving.
     */
    public void save(Path file) throws IOException {
        List<String> lines = new LinkedList<>();

        for (Entry entry : entries) {
            lines.add(entry.getProgress().name() + ";" + entry.getLabel());
        }

        Files.write(file, lines);
    }
}
