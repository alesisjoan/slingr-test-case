package com.github.alesisjoan.slingr.services;

import com.github.alesisjoan.slingr.domain.MathExpression;
import com.github.alesisjoan.slingr.domain.MathResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CacheServiceImpl implements CacheService {

    private static final Logger log = LoggerFactory
            .getLogger(CacheServiceImpl.class);
    
    private static int MAX_SIZE_STACK = 10; //TODO obtain from properties
    
    private static ArrayDeque<MathResult> lastExpressions = new ArrayDeque<>();

    @Override
    public void pushLastExpression(MathResult mathResult) {
        while (lastExpressions.size() >= MAX_SIZE_STACK) {
            lastExpressions.remove();
        }
        lastExpressions.push(mathResult);
    }

    @Override
    public ArrayDeque<MathResult> pullLastExpressions() {
        return lastExpressions;
    }

    @Override
    public MathResult pullResult(MathExpression mathExpression) {
        return null;
    }

    @Override
    public void pushResult(MathResult mathResult) {
        
    }
}
