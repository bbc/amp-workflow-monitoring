package uk.co.bbc.pcs.amp.monitoring.config

import com.splunk.HttpService
import com.splunk.SSLSecurityProtocol
import com.splunk.ServiceArgs
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.bbc.pcs.amp.monitoring.dao.FailureDao
import uk.co.bbc.pcs.amp.monitoring.dao.SplunkFailureDao

@Configuration
class AppConfig {
    
    @Bean
    ServiceArgs serviceArgs(@Value('${splunk.scheme}') String splunkScheme,
                            @Value('${splunk.username}') String splunkUsername,
                            @Value('${splunk.password}') String splunkPassword,
                            @Value('${splunk.host}') String splunkHost){
        // Create a map of arguments and add login parameters
        HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2)
        ServiceArgs loginArgs = new ServiceArgs()
        loginArgs.setScheme(splunkScheme)
        loginArgs.setUsername(splunkUsername)
        loginArgs.setPassword(splunkPassword)
        loginArgs.setHost(splunkHost)
        
        loginArgs
    }

    @Bean
    FailureDao splunkFailureDao(ServiceArgs splunkLoginArgs){
        return new SplunkFailureDao(splunkLoginArgs)
    }
}
