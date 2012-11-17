package org.etherlords.ametisten.stat.reporting;

import java.util.Collection;

public interface ReportQuery<T extends Report<?>> {

	void executeOn(Collection<T> collection);
	
	ReportQueryResultSet<T> getResultSet();
	
}
