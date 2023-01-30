package com.melita.weborder.config.feign;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ExternalServiceFeignConfiguration {

    @Bean
    BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	return new BasicAuthRequestInterceptor("melita@melita.com", "123456");
    }


    @Bean
    Request.Options options(WebOrderFeignProperties properties) {
	return new Request.Options(properties.getExternalConnectTimeout(), TimeUnit.MILLISECONDS, properties.getExternalReadTimeout(), TimeUnit.MILLISECONDS, true);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
	return Logger.Level.FULL;
    }

    @Bean
    Retryer retryer(WebOrderFeignProperties properties) {
	return new Retryer.Default(properties.getExternalRetryPeriod(), SECONDS.toMillis(1), properties.getExternalMaxAttempt());
    }


}
