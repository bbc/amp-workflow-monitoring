package uk.co.bbc.pcs.amp.monitoring.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uk.co.bbc.pcs.amp.monitoring.domain.EpgFailure
import uk.co.bbc.pcs.amp.monitoring.service.EpgFailureService

@RestController
class EpgFailureController {

    @Autowired
    EpgFailureService epgFailureService
    
    @RequestMapping("/epg/response/failures/freeview")
    List<EpgFailure> epgResponseFailures() {
        epgFailureService.latestFailures
    }
}
