package com.company.service.impl;

import com.company.model.AccessPeriod;
import com.company.repository.AccessPeriodRepository;
import com.company.service.AccessPeriodService;

public class AccessPeriodServiceImpl implements AccessPeriodService {
	/* Service layer - Encapsulates application logic/API. Services should be the only ones with access to the repositories.
	* For this case, there is no application logic to be done, since as long as input is validated at controller layer, it will be correct.	
	* So this just connects the controller layer to the repo layer.
	*/
    private AccessPeriodRepository accessPeriodRepository;

    public AccessPeriodServiceImpl(AccessPeriodRepository accessPeriodRepository) {
        this.accessPeriodRepository = accessPeriodRepository;
    }

    @Override
    public AccessPeriod getAccessPeriod() {
        return accessPeriodRepository.getAccessPeriod();
    }

    @Override
    public void updateAccessPeriod() {
        accessPeriodRepository.updateAccessPeriod();
    }
}
