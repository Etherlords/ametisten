package org.etherlords.ametisten.stat.reporting;

import org.etherlords.ametisten.stat.reporting.game.PlayerReportByNameQuery;

public interface ReportRepository<T extends Report<K>, K> {
    
    T findById(K id);
    
    void add(T report);
    
    void update(T report);
    
    void updateById(K id, T report);

	void executeQuery(ReportQuery<T> query);
    
}
