package uk.co.bbc.pcs.amp.monitoring.domain

import groovy.transform.Canonical

@Canonical
class EpgFailure {
    
    String filename
    String pipServiceId
    String scheduleDate
    Integer version
    String platform
    //List<String> failureMessages = []
}
