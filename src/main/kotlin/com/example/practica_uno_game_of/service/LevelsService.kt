package com.example.practica_uno_game_of.service

import com.example.practica_uno_game_of.model.LevelsModel
import com.example.practica_uno_game_of.repository.LevelsRepository
import org.slf4j.event.Level
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class LevelsService {
    @Autowired
    lateinit var modeloRepository: LevelsRepository

    fun list ():List<LevelsModel>{
        return modeloRepository.findAll()
    }
    //PETICIONES POST
    //clase service
    fun save(levelsModel: LevelsModel): LevelsModel {
        try {
            validateLevelsModel(levelsModel)
            return modeloRepository.save(levelsModel)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }
    //clase service -Petición put
    fun update(id: Long, levelsModel: LevelsModel): LevelsModel {
        try {
            validateLevelsModel(levelsModel)
            if (!modeloRepository.existsById(id)) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "ID no existe")
            }
            levelsModel.id = id
            return modeloRepository.save(levelsModel)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }
    /*clase service - Peticiones patch*/
    fun updateName(id: Long, levelName: String): LevelsModel {
        try {
            val existingModel = modeloRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "ID no existe") }

            existingModel.levelName = levelName

            return modeloRepository.save(existingModel)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }
    //clase service - Delete by id
    fun delete(id: Long): Boolean {
        try {
            if (!modeloRepository.existsById(id)) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "ID no existe")
            }
            modeloRepository.deleteById(id)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }
    //GET BY ID clase service
    fun listById(id: Long): LevelsModel? {
        return modeloRepository.findById(id).orElse(null)
    }

    // Validaciones comunes
    private fun validateLevelsModel(levelsModel: LevelsModel) {
        levelsModel.apply {
            levelName?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El nombre del nivel no debe ser nulo o vacío")
            levelNumber?.takeIf { it > 0 }
                ?: throw Exception("El numero de nivel no debe ser nulo o menor o igual a 0")
            levelDifficulty?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("La dificultad del nivel no debe ser nula o vacía")
        }
    }
}