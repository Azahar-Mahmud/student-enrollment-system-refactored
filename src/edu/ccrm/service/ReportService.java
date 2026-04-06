package edu.ccrm.service;

import edu.ccrm.service.bridge.GpaDistributionReport;
import edu.ccrm.service.bridge.ReportRenderer;
import edu.ccrm.service.proxy.DataStoreInterface;

/**
 * Bridge Pattern: Report logic is now separated from output rendering.
 * ReportRenderer (implementor) is injected — can be console, file, HTML, etc.
 * Proxy Pattern: Uses DataStoreInterface instead of concrete DataStore.
 */
public class ReportService {
    private final DataStoreInterface dataStore;
    private final ReportRenderer renderer;
    
    public ReportService(DataStoreInterface dataStore, ReportRenderer renderer) {
        this.dataStore = dataStore;
        this.renderer = renderer;
    }
    
    public void generateGpaDistributionReport() {
        GpaDistributionReport report = new GpaDistributionReport(renderer, dataStore);
        report.generate();
    }
}