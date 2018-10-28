package uk.co.bbc.pcs.amp.monitoring.dao

import org.junit.Test
import uk.co.bbc.pcs.amp.monitoring.domain.EpgFailure

class SplunkFailureDaoTest {


    SplunkFailureDao sut = new SplunkFailureDao()
    
    @Test
    void 'search splunk for failures'(){
        
        List<EpgFailure> result = sut.latestFailures
        
        assert result
    }
    
    
}
