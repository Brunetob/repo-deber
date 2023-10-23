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
    fun save(modelo: LevelsModel): LevelsModel{
        try{
            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    //clase service -Petición put
    fun update(modelo: LevelsModel): LevelsModel{
        try {
            modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    /*clase service - Peticiones patch
    fun updateName(modelo:LevelsModel): LevelsModel{
        try{
            val response = modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                fullname=modelo.fullname //un atributo del modelo
            }
            return modeloRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }*/
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