/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.example.sixshiro.cache.ehcache;

import net.sf.ehcache.Ehcache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.*;

public class EhcacheCache implements Cache {
    private org.springframework.cache.Cache springCache;

    EhcacheCache(org.springframework.cache.Cache springCache) {
        this.springCache = springCache;
    }

    @Override
    public Object get(Object key) throws CacheException {
        Object value = springCache.get(key);
        if (value instanceof SimpleValueWrapper) {
            return ((SimpleValueWrapper) value).get();
        }
        return value;
    }

    @Override
    public Object put(Object key, Object value) throws CacheException {
        springCache.put(key, value);
        return value;
    }

    @Override
    public Object remove(Object key) throws CacheException {
        springCache.evict(key);
        return null;
    }

    @Override
    public void clear() throws CacheException {
        springCache.clear();
    }

    @Override
    public int size() {
        if (springCache.getNativeCache() instanceof Ehcache) {
            Ehcache ehcache = (Ehcache) springCache.getNativeCache();
            return ehcache.getSize();
        }
        throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
    }


    @Override
    public Set keys() {
        if (springCache.getNativeCache() instanceof Ehcache) {
            Ehcache ehcache = (Ehcache) springCache.getNativeCache();
            return new HashSet(ehcache.getKeys());
        }
        throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
    }

    @Override
    public Collection values() {
        if (springCache.getNativeCache() instanceof Ehcache) {
            Ehcache ehcache = (Ehcache) springCache.getNativeCache();
            List keys = ehcache.getKeys();
            if (!CollectionUtils.isEmpty(keys)) {
                List values = new ArrayList(keys.size());
                for (Object key : keys) {
                    Object value = get(key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        }
        throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
    }
}
