package com.example.practica_uno_game_of.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "players")
class Players {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long? = null
    @Column(name="Pl_name")
    var playerName: String? = null   //nombre del player
    var Pl_lifes: Long? = null
    var levels_id: Long? = null
}