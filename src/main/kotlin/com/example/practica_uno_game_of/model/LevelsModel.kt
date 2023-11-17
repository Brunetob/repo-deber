package com.example.practica_uno_game_of.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "levels")
class LevelsModel{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long? = null
    @Column (name="Lvl_name")
    var levelName: String? = null   //description_one en la base de datos
    @Column (name="Lvl_difficulty")
    var levelDifficulty: String? = null   //address
    @Column (name = "Lvl_number")
    var levelNumber: Long? = null
}