package ru.helpdesk.helpers;

import us.abstracta.jmeter.javadsl.http.DslCacheManager;

import static us.abstracta.jmeter.javadsl.JmeterDsl.httpCache;

public class CacheHelper {
    public static DslCacheManager getCacheManager() {
        return httpCache();
    }

    public static DslCacheManager getCacheDisable() {
        return httpCache()
                .disable();
    }
}
