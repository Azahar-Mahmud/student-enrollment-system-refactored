package edu.ccrm.cli.command;

import java.util.Scanner;
import edu.ccrm.service.ReportService;
import edu.ccrm.util.InputValidator;

public class ShowReportsCommand implements MenuCommand {
    private final ReportService reportService;

    public ShowReportsCommand(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("\n--- Reports ---");
        System.out.println("1. GPA Distribution Report");

        int choice = InputValidator.getInt(scanner, "Enter choice: ");
        if (choice == 1) {
            reportService.generateGpaDistributionReport();
        } else {
            System.out.println("Invalid choice.");
        }
    }
}