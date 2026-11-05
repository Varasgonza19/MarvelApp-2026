package edu.iesam.marvel.feature.superheroes.data.local.xml

import android.content.Context
import edu.iesam.marvel.R
import edu.iesam.marvel.feature.superheroes.domain.SuperHero
import edu.iesam.marvelapp.core.serialization.JsonSerializer

/**
 * Conector para gestionar datos en un fichero Xml a través de SharedPreferences.
 *
 */
class SuperHeroXmlLocalDataSource (private val context: Context, private val serialization: JsonSerializer<List<SuperHero>>) {

    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE
    )


    fun save(superHeroes : List<SuperHero>){
        sharedPref.edit().apply(){
            putString(keySuperHero, serialization.toJson(superHeroes))
            apply()
        }
    }

    public fun findAll(): List<SuperHero> {
        val json = sharedPref.getString(keySuperHero, null)
        return json.let {
            serialization.fromJson(json)
        }
        return emptyList()

    }


    fun findById(id : String): SuperHero? {
        return findAll().find { it.id == id }
    }

    companion object{
        private val keySuperHero : String = "superHero"
    }

    fun deleteAll(){
        sharedPref.edit().apply(){
           remove(keySuperHero)
           apply()
        }


    }

    fun deleteById(id : String){
        save(findAll().filterNot { it.id == id })
    }


}


