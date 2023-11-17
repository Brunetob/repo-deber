package com.example.practica_uno_game_of.service

import com.example.practica_uno_game_of.model.Players
import com.example.practica_uno_game_of.repository.LevelsRepository
import com.example.practica_uno_game_of.repository.PlayersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.server.ResponseStatusException
@Repository
class PlayersService {
    @Autowired
    lateinit var playersRepository: PlayersRepository
    @Autowired//primera tabla inyección
    lateinit var levelsRepository: LevelsRepository

    fun list ():List<Players>{
        return playersRepository.findAll()
    }
    //PETICIONES POST
    //clase service
    fun save(players: Players): Players {
        //Comprobación de la clave foranea
        try {
            /*levelsRepository.findById(players.levels_id)
                ?: throw Exception("Id del cliente no encontradaa")*/
            return playersRepository.save(players)
        }catch (ex : Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
    //clase service -Petición put
    fun update(modelo: Players): Players {
        try {
            playersRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return playersRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    //clase service - Delete by id
    fun delete (id: Long?):Boolean?{
        try{
            val response = playersRepository.findById(id)
                ?: throw Exception("ID no existe")
            playersRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    //GET BY ID clase service
    fun listById (id:Long?): Players?{
        return playersRepository.findById(id)
    }

    //Petición patch
    fun updateName(modelo: Players): Players{
        try {
            var response = playersRepository.findById(modelo.id)?: throw Exception("element doesn't exist")
            response.apply { playerName = modelo.playerName }
            return playersRepository.save(response)
        }catch (ex: Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }
}