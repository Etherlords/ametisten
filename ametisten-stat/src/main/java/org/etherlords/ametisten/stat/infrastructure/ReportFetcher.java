package org.etherlords.ametisten.stat.infrastructure;

import java.util.Map;

import org.etherlords.ametisten.stat.reporting.Report;

public interface ReportFetcher<K, T extends Report<K>> {
	
	T fetchReportById(K reportId, Map<K, T> reports);
	
}
