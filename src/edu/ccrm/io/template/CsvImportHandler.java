package edu.ccrm.io.template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public abstract class CsvImportHandler {
    protected final Path dataDir;

    protected CsvImportHandler(Path dataDir) {
        this.dataDir = dataDir;
    }

    public final void importData() throws IOException {
        Path filePath = getFilePath();
        if (!Files.exists(filePath)) return;

        try (Stream<String> lines = Files.lines(filePath)) {
            lines.skip(1) // Skip header
                 .map(line -> line.split(","))
                 .forEach(this::processRow);
        }
    }

    protected abstract Path getFilePath();
    protected abstract void processRow(String[] parts);
}