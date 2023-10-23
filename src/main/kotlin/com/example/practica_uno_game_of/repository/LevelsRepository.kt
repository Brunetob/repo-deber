package com.example.practica_uno_game_of.repository

import com.example.practica_uno_game_of.model.LevelsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LevelsRepository : JpaRepository<LevelsModel, Long?> {

    fun findById (id: Long?): LevelsModel?
    //Peticion put
    //clase repository
}