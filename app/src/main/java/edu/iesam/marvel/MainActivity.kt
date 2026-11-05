package edu.iesam.marvel

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.iesam.marvel.feature.superheroes.data.local.xml.SuperHeroXmlLocalDataSource
import edu.iesam.marvel.feature.superheroes.domain.SuperHero

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }
        val xml = SuperHeroXmlLocalDataSource(
            this,
            serialization = TODO()
        )
        xml.save(mutableListOf(
            SuperHero("1", "SuperMan", "super-man","https://"),
            SuperHero("2", "Batman", "bat-man","https://")))


        val superHero = xml.findById("2")
        Log.d(MainActivity::class.java.simpleName,superHero.toString())

    }

}