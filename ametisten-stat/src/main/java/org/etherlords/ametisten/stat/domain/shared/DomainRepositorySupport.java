package org.etherlords.ametisten.stat.domain.shared;

public abstract class DomainRepositorySupport {
    
    protected DomainRepository domainRepository;
    
    public void setDomainRepository(final DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

}
