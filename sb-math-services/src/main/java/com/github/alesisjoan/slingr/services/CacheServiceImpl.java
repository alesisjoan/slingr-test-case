package com.github.alesisjoan.slingr.services;

import com.github.alesisjoan.slingr.domain.KeyValue;
import com.github.alesisjoan.slingr.domain.MathExpression;
import com.github.alesisjoan.slingr.domain.MathResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class CacheServiceImpl implements CacheService {

    private static final Logger log = LoggerFactory
            .getLogger(CacheServiceImpl.class);
    
    private static RestTemplate restTemplate = new RestTemplate();
    
    @Value("${cache.url.expressions}")
    private String CACHE_EXPRESSIONS;
    
    
    @Override
    public void pushResult(MathResult mathResult) {
        KeyValue keyValue = new KeyValue(mathResult.getMathExpression().getExpression(), mathResult.getResult());
        HttpEntity<KeyValue> requestUpdate = new HttpEntity<>(keyValue);
        try {
            restTemplate.exchange(CACHE_EXPRESSIONS, HttpMethod.PUT, requestUpdate, Void.class);
        }catch (Exception e){
            log.warn("Error while trying to send expression to cache service", e);
        }
    }

    @Override
    public String pullResult(MathExpression expression) {
        String result = null;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(CACHE_EXPRESSIONS
                    +"?expression="+
                    URLEncoder.encode(expression.getExpression()
                    , StandardCharsets.UTF_8.toString()),String.class);
            result = response.getBody();
        }catch (Exception e){
            log.warn("Error while trying to send expression to cache service", e);
        }
        return result;
    }
}
