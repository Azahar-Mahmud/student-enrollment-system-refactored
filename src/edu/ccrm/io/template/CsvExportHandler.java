package edu.ccrm.io.template;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public abstract class CsvExportHandler<T> {
    protected final Path dataDir;

    protected CsvExportHandler(Path dataDir) {
        this.dataDir = dataDir;
    }

    public final void export() throws IOException {
        ensureDirectoryExists();
        Path filePath = getFilePath();

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(getHeader() + "\n");

            for (T item : getData()) {
                writer.write(formatRow(item) + "\n");
            }
        }
    }

    private void ensureDirectoryExists() throws IOException {
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
    }

    protected abstract Path getFilePath();
    protected abstract String getHeader();
    protected abstract Collection<T> getData();
    protected abstract String formatRow(T item);
}