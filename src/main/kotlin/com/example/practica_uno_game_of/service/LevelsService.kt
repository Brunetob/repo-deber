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
    fun save(levelsModel: LevelsModel): LevelsModel{
        try{
            //validaciones
            levelsModel.id?.takeIf { it > 0}
                ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El id no debe ser nulo")
            levelsModel.levelName?.takeIf { it.trim().isNotEmpty()}
                ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El título de la conferencia no debe ser nulo")
            levelsModel.Lvl_number?.takeIf {it > 0}
                ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de nivel no debe ser nulo")
            levelsModel.levelDifficulty?.takeIf { it.trim().isNotEmpty()}
                ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El título de la conferencia no debe ser nulo")
            return modeloRepository.save(levelsModel)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }
    //clase service -Petición put
    fun update(levelsModel: LevelsModel): LevelsModel{
        try {
            modeloRepository.findById(levelsModel.id)
                ?: throw Exception("ID no existe")

            return modeloRepository.save(levelsModel)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    /*clase service - Peticiones patch*/
    fun updateName(levelsModel: LevelsModel): LevelsModel{
        try{
            val response = modeloRepository.findById(levelsModel.id)
                ?: throw Exception("ID no existe")
            response.apply {
                levelName=levelsModel.levelName //un atributo del modelo
            }
            return modeloRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    //clase service - Delete by id
    fun delete (id: Long?):Boolean?{
        try{
            val response = modeloRepository.findById(id)
                ?: throw Exception("ID no existe")
            modeloRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    //GET BY ID clase service
    fun listById (id:Long?):LevelsModel?{
        return modeloRepository.findById(id)
    }
}