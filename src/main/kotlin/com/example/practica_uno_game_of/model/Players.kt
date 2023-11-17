package com.example.practica_uno_game_of.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "players")
class Players {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long? = null
    //@NotBlank(message="Campo obligatorio") //validate
    @Column(name="Pl_name")
    var playerName: String? = null   //nombre del player
    @Column(name="Pl_lifes")
    var playerLifes: Long? = null
    @Column(name="levels_id")
    var levelsId: Long? = null
}