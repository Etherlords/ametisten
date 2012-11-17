package org.etherlords.ametisten.stat.infrastructure;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.etherlords.ametisten.stat.reporting.Report;
import org.etherlords.ametisten.stat.reporting.ReportQuery;
import org.etherlords.ametisten.stat.reporting.ReportRepository;

public class GenericInMemoryReportRepository<T extends Report<K>, K> implements ReportRepository<T, K> {
    
    private final ConcurrentHashMap<K, T> reports = new ConcurrentHashMap<K, T>();
    
	private ReportFetcher<K, T> reportFetcher = new DefaultReportFetcher<K, T>();
    
	public static class DefaultReportFetcher<K, T extends Report<K>> implements ReportFetcher<K, T> {

		@Override
		public T fetchReportById(K reportId, Map<K, T> reports) {
			return reports.get(reportId);
		}

	}
	
    public void setFetcher(ReportFetcher<K, T> reportFetcher) {
		this.reportFetcher = reportFetcher;
    }
    
    @Override
    public T findById(final K id) {
        return reportFetcher.fetchReportById(id, reports);
    }
    
    @Override
    public void add(final T report) {
        reports.put(report.getReportId(), report);
    }
    
    @Override
    public void update(final T report) {
        reports.put(report.getReportId(), report);
    }
    
    @Override
    public void updateById(final K id, final T report) {
        reports.put(report.getReportId(), report);
    }

	@Override
	public void executeQuery(ReportQuery<T> query) {
		query.executeOn(Collections.unmodifiableCollection(reports.values()));
	}

}
