package com.meihaofenqi.common.webutilsboot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "wub")
public class WubProperties {

    private boolean loggingResponse;
}
