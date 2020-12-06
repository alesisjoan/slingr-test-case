package com.github.alesisjoan.slingr.services;

import java.util.ArrayDeque;

public interface CacheService {
    
    void put(String key, String value);
    
    String get(String key);

    ArrayDeque<String> getLast();
}
