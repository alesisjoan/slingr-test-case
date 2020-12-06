package com.github.alesisjoan.slingr.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CacheServiceImpl implements CacheService {

    private static final Logger log = LoggerFactory
            .getLogger(CacheServiceImpl.class);

    /*
    * A char value[] and an int hash. A char is 2 bytes, and an int is 4 bytes.
        So wouldn't the answer be yourstring.length * 2 + 4?
    * we take an average math expression as 10 characters by the expression and 4 for the result
    * so 10 * 2 + 4 = 24 bytes
    * and 4 * 2 + 4 = 12 bytes
    * 36 bytes every key and value
    * if we want to take 200mb, we should reserve at max, 209715200 bytes / 36 = 5825422 elements
    * 
    * */
    private static int MAX_SIZE_MAP = 5825422;
    private static int MAX_SIZE_STACK = 10;

    private static WeakHashMap<String, String> cache = new WeakHashMap<>();
    private static ArrayDeque<String> lastExpressions = new ArrayDeque<>();

    @Override
    public void put(String key, String value) {
        if(cache.size() > MAX_SIZE_MAP + 1 ){
            cache.clear();
        }
        cache.put(key, value);
        while (lastExpressions.size() >= MAX_SIZE_STACK) {
            lastExpressions.remove();
        }
        lastExpressions.push(key);
    }

    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public ArrayDeque<String> getLast() {
        return lastExpressions;
    }
}
