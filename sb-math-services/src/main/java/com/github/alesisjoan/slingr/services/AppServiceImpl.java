package com.github.alesisjoan.slingr.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppServiceImpl implements AppService {

    private static final Logger log = LoggerFactory
            .getLogger(AppServiceImpl.class);

    @Autowired
    CacheService cacheService;

    @Override
    public String getLastExpressions() {
        final StringBuilder builder = new StringBuilder();
        Optional.ofNullable(cacheService.pullLastExpressions()).ifPresent(l ->
                l.forEach(
                        e -> builder.append("\n")
                                .append(e.getMathExpression().getExpression())
                                .append(" = ")
                                .append(e.getResult())
                ));
        return builder.toString();
    }
}
