package uk.co.bbc.pcs.amp.monitoring.dao

import com.splunk.Event
import com.splunk.HttpService
import com.splunk.JobExportArgs
import com.splunk.MultiResultsReaderXml
import com.splunk.SSLSecurityProtocol
import com.splunk.SearchResults
import com.splunk.Service
import com.splunk.ServiceArgs
import uk.co.bbc.pcs.amp.monitoring.dao.mapper.SplunkFailureEventMapper
import uk.co.bbc.pcs.amp.monitoring.domain.EpgFailure

interface FailureDao {
    
    List<EpgFailure> getLatestFailures()
    
}

class SplunkFailureDao implements FailureDao {

    private final Service service
    private final SplunkFailureEventMapper mapper = new SplunkFailureEventMapper()
    
    SplunkFailureDao(ServiceArgs splunkLoginArgs) {
        
        // Create a Service instance and log in with the argument map
         this.service = Service.connect(splunkLoginArgs)
        
    }

    List<EpgFailure> getLatestFailures(){

        // Create an argument map for the export arguments
        JobExportArgs exportArgs = new JobExportArgs()
        exportArgs.setEarliestTime("-48h")
        exportArgs.setLatestTime("now")
        exportArgs.setSearchMode(JobExportArgs.SearchMode.NORMAL)

        String searchQuery = 'search index=main event_name=pcs-epg-response-fetcher.FREEVIEW_FAILURE_RESPONSE_RECEIVED' // Return the first 100 events
       

        // A blocking search returns the job when the search is done
        InputStream exportSearch = service.export(searchQuery, exportArgs)
        // Display results using the SDK's multi-results reader for XML 
        MultiResultsReaderXml multiResultsReader = new MultiResultsReaderXml(exportSearch);


        List<EpgFailure> results = []
        
        for (SearchResults searchResults : multiResultsReader) {
            for (Event event : searchResults) {
                EpgFailure epgFailure = mapper.map(event['_raw'])
                results.add(epgFailure)
            }
        }
        multiResultsReader.close()
        
        return results


    }
    
    
}