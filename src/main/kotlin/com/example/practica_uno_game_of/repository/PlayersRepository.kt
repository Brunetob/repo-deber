package com.example.practica_uno_game_of.repository

import com.example.practica_uno_game_of.model.Players
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
@Service
interface PlayersRepository : JpaRepository<Players, Long?> {
    fun findById (id: Long?): Players?
    //Peticion put
    //clase repository
}