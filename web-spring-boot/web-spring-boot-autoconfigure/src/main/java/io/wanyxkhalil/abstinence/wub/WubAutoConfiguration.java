package io.wanyxkhalil.abstinence.wub;

import io.wanyxkhalil.abstinence.wub.log.AppLoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WubProperties.class)
public class WubAutoConfiguration {

    @Autowired
    private WubProperties wubProperties;

    @Bean
    public AppLoggingFilter appLoggingFilter() {
        return new AppLoggingFilter(wubProperties.isLoggingResponse());
    }
}
