package com.github.alesisjoan.slingr.services;

import com.github.alesisjoan.slingr.domain.MathExpression;
import com.github.alesisjoan.slingr.domain.MathResult;

import java.util.ArrayDeque;

public interface CacheService {
    
    void pushResult(MathResult mathResult);
    
    String pullResult(MathExpression expression);
}
