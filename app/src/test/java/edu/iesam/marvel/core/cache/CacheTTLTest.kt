package edu.iesam.marvel.core.cache

import android.content.Context
import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CacheTTLTest {
    private val context: Context = mockk()
    private val sharedPref: SharedPreferences = mockk()
    private val editor: SharedPreferences.Editor = mockk(relaxed = true)

    private val cacheTTL: CacheTTL = mockk()



    private lateinit var appcacheTTL: CacheTTL


    @Before
    fun setUp() {
        every { context.getString(any()) } returns "app_cache"
        every { context.getSharedPreferences("app_cache", Context.MODE_PRIVATE) }
        every { sharedPref.edit() } returns editor
        every { editor.putLong(any(), any()) } returns editor

        appcacheTTL = CacheTTL(context, ttlMillis = 0L)
    }

    @Test
    fun get(){

    }

    @Test
    fun save(){
        verify(exactly = 1) { editor.putLong("heroes", 1000L) }
        verify(exactly = 1) { editor.apply() }
    }

    @Test
    fun isExpired(){
        every { sharedPref.getLong("heroes", -1) } returns 1_000L
        every { System.currentTimeMillis() }
    }





}