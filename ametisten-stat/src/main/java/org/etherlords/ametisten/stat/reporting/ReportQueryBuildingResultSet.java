package org.etherlords.ametisten.stat.reporting;

public interface ReportQueryBuildingResultSet<T extends Report<?>> {
	
	void addResult(T result);
	
}
