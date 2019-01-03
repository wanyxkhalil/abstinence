package com.meihaofenqi.common.webutilsboot;

import com.meihaofenqi.common.webutilsboot.log.AppLoggingFilter;
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
