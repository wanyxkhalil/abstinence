package io.wanyxkhalil.abstinence.wub;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "wub")
public class WubProperties {

    private boolean loggingResponse;
}
