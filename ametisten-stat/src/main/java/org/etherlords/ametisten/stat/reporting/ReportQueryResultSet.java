package org.etherlords.ametisten.stat.reporting;

import java.util.Enumeration;

public interface ReportQueryResultSet<T extends Report<?>> extends Enumeration<T> {
	
	int count();
	
}
