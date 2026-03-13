package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import edu.ccrm.config.AppConfig;
import edu.ccrm.util.FileSystemUtils;
import edu.ccrm.util.RecursiveFileUtils;

/**
 * Handles backup operations using NIO.2.
 */
public class BackupService {

    public void createBackup() {
        try {
            Path sourceDir = getSourceDirectory();
            validateSourceDirectory(sourceDir);

            Path backupDestDir = prepareBackupDestination();
            createBackupDirectory(backupDestDir);

            performBackup(sourceDir, backupDestDir);
            reportBackupSuccess(backupDestDir);

        } catch (IOException e) {
            handleBackupFailure(e);
        }
    }

    private Path getSourceDirectory() {
        AppConfig config = AppConfig.getInstance();
        return Paths.get(config.getDataDirectory());
    }

    private void validateSourceDirectory(Path sourceDir) throws IOException {
        if (!FileSystemUtils.isDirectory(sourceDir)) {
            throw new IOException("Source data directory does not exist. Run export first.");
        }
    }

    private Path prepareBackupDestination() {
        AppConfig config = AppConfig.getInstance();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        Path backupParentDir = Paths.get(config.getBackupDirectory());
        return backupParentDir.resolve("backup_" + timestamp);
    }

    private void createBackupDirectory(Path destination) throws IOException {
        FileSystemUtils.ensureDirectoryExists(destination);
    }

    private void performBackup(Path source, Path destination) throws IOException {
        copyDirectory(source, destination);
    }

    private void copyDirectory(Path source, Path destination) throws IOException {
        try (Stream<Path> stream = Files.walk(source)) {
            stream.forEach(sourcePath -> copyFile(sourcePath, source, destination));
        }
    }

    private void copyFile(Path sourcePath, Path sourceRoot, Path destinationRoot) {
        try {
            Path targetPath = destinationRoot.resolve(sourceRoot.relativize(sourcePath));
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Failed to copy file: " + sourcePath + " -> " + e.getMessage());
        }
    }

    private void reportBackupSuccess(Path backupPath) {
        System.out.println("Backup created successfully at: " + backupPath);
        displayBackupSize(backupPath);
    }

    private void displayBackupSize(Path backupPath) {
        long size = RecursiveFileUtils.calculateDirectorySize(backupPath);
        System.out.printf("Total size of backup directory '%s' is %d bytes.\n", backupPath.getFileName(), size);
    }

    private void handleBackupFailure(IOException e) {
        System.err.println("Could not create backup: " + e.getMessage());
    }
}