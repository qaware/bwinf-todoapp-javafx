package de.qaware.bwinf.todoapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class MainModel {
    private ObservableList<Entry> entries = FXCollections.observableArrayList();

    public ObservableList<Entry> getEntries() {
        return entries;
    }

    public void load(Path file) throws IOException {
        if (!Files.exists(file)) {
            return;
        }

        List<String> lines = Files.readAllLines(file);

        for (String line : lines) {
            String[] parts = line.split(";", 2);
            Progress progress = Progress.valueOf(parts[0]);
            Entry entry = new Entry(progress, parts[1]);
            entries.add(entry);
        }
    }

    public void save(Path file) throws IOException {
        List<String> lines = new LinkedList<>();

        for (Entry entry : entries) {
            lines.add(entry.getProgress().name() + ";" + entry.getLabel());
        }

        Files.write(file, lines);
    }
}
