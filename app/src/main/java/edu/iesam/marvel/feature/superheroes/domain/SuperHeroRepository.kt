package edu.iesam.marvel.feature.superheroes.domain

interface SuperHeroRepository {
    fun getAll(): List<SuperHero>
}