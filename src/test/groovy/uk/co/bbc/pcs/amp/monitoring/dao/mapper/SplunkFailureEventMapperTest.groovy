package uk.co.bbc.pcs.amp.monitoring.dao.mapper

import org.junit.Test
import uk.co.bbc.pcs.amp.monitoring.domain.EpgFailure

class SplunkFailureEventMapperTest {
    
    SplunkFailureEventMapper sut = new SplunkFailureEventMapper()
    
    @Test
    void 'should map raw splunk message into EpgFailure'(){
        String raw = "event_name=pcs-epg-response-fetcher.FREEVIEW_FAILURE_RESPONSE_RECEIVED timestamp=\"2018-10-26T15:01:23.5 GMT\" activity_id=7e3986de-c401-497a-b7d9-cb1aebe37855 filename=20181028WALER006.xml correlationId=20181026T150113Z_d7074eebeff8488890f1e61935283f7d platform=FREEVIEW activityId=7e3986de-c401-497a-b7d9-cb1aebe37855 pipsServiceId=bbc_radio_wales_fm scheduleDate=2018-10-28"        
        
        EpgFailure epgFailure = sut.map(raw)
        
        assert epgFailure.filename == '20181028WALER006.xml'
        assert epgFailure.scheduleDate == '2018-10-28'
        assert epgFailure.pipServiceId == 'bbc_radio_wales_fm'
    }
}
