package edu.ccrm.service.bridge;

/**
 * Bridge Pattern — Implementor interface.
 * Defines HOW reports are rendered (console, file, HTML, etc.).
 * The Report abstraction delegates rendering to this interface.
 */
public interface ReportRenderer {
    void renderHeader(String title);
    void renderDataRow(String label, long value);
    void renderFooter();
}
