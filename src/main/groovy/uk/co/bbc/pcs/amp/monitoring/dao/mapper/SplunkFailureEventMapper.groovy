package uk.co.bbc.pcs.amp.monitoring.dao.mapper


import uk.co.bbc.pcs.amp.monitoring.domain.EpgFailure


class SplunkFailureEventMapper {
    
    
    EpgFailure map(String rawEvent){

        Map<String,String> rawEventMap = rawEvent.split(' ')
                                                 .toList()
                                                 .collect{ elem -> elem.split('=') }
                                                 .findAll{ it.size() == 2 }
                                                 .collectEntries{ [(it[0]): it[1]] }
                                              
       new EpgFailure(filename: rawEventMap['filename'], 
                      scheduleDate: rawEventMap['scheduleDate'],
                      pipServiceId: rawEventMap['pipsServiceId'],
                      platform: 'Freeview')                                               
        
    }
}
