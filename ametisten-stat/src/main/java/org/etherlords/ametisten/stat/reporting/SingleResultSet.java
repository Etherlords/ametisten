package org.etherlords.ametisten.stat.reporting;

public class SingleResultSet<T extends Report<?>> implements
		ReportQueryResultSet<T>, ReportQueryBuildingResultSet<T> {
	
	private T result;

	private boolean isRetrieved = false;
	
	public SingleResultSet() {
	}
	
	@Override
	public int count() {
		return result == null ? 0 : 1;
	}

	public T singleResult() {
		return result;
	}

	@Override
	public boolean hasMoreElements() {
		return !isRetrieved && result != null;
	}

	@Override
	public T nextElement() {
		try {
			return result;
		} finally {
			isRetrieved = true;
		}
	}

	@Override
	public void addResult(T result) {
		
		if (this.result == null) {
			this.result = result;
		} else {
			throw new IllegalStateException("SingleResultSet can contains only one result.");
		}
		
	}
	
}
