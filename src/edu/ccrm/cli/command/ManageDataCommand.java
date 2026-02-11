package edu.ccrm.cli.command;

import java.util.Scanner;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.util.InputValidator;

public class ManageDataCommand implements MenuCommand {
    private final ImportExportService importExportService;
    private final BackupService backupService;

    public ManageDataCommand(ImportExportService importExportService, BackupService backupService) {
        this.importExportService = importExportService;
        this.backupService = backupService;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("\n--- Data Management ---");
        System.out.println("1. Export All Data");
        System.out.println("2. Create Backup of Exported Data");

        int choice = InputValidator.getInt(scanner, "Enter choice: ");
        switch (choice) {
            case 1 -> importExportService.exportData();
            case 2 -> backupService.createBackup();
            default -> System.out.println("Invalid choice.");
        }
    }
}