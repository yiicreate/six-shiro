/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.example.sixshiro.cache.ehcache;

import org.apache.commons.lang3.Validate;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class EhCacheCacheManager implements CacheManager {

	private org.springframework.cache.CacheManager cacheManager;

	/**
	 * 设置spring cache manager
	 *
	 * @param cacheManager
	 */
	public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		org.springframework.cache.Cache springCache = cacheManager.getCache(name);
		Validate.validState(springCache != null, "cache: "+name+" 不存在, 请在ehcache.xml中定义name为"+name+"的cache");
		return new EhcacheCache(springCache);
	}

}
