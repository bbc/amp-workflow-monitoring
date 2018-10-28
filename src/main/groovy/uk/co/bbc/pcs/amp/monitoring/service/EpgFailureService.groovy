package uk.co.bbc.pcs.amp.monitoring.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.co.bbc.pcs.amp.monitoring.dao.FailureDao

@Service
class EpgFailureService {

    private final FailureDao failureDao
    
    @Autowired
    EpgFailureService(FailureDao failureDao) {
        this.failureDao = failureDao
    }

    List<String> getLatestFailures(){
        failureDao.latestFailures
    }
    
    
}
