package edu.iesam.marvel.core.cache

import android.content.Context
import java.util.concurrent.TimeUnit


class CacheTTL (
    context: Context,
    private val ttlMillis : Long = TimeUnit.MINUTES.toMillis(5)

){
    private val sharedPref = context.getSharedPreferences("cache_ttl", Context.MODE_PRIVATE)

    fun save(key: String) {
        sharedPref.edit().putLong(key, System.currentTimeMillis()).apply()
    }

    fun get(key: String): Long? =
        if (sharedPref.contains(key)) sharedPref.getLong(key, 0L) else null

    fun isExpired(key: String): Boolean {
        val savedTime = get(key) ?: return true
        return System.currentTimeMillis() - savedTime > ttlMillis
    }
}









