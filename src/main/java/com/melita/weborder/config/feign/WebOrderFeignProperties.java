package com.melita.weborder.config.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "melita.feign")
public class WebOrderFeignProperties {

    private long externalConnectTimeout;
    private long externalReadTimeout;
    private long externalRetryPeriod;
    private int externalMaxAttempt;

}
