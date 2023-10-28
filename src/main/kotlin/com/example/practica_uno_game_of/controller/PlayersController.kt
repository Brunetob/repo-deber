package com.example.practica_uno_game_of.controller

import com.example.practica_uno_game_of.model.LevelsModel
import com.example.practica_uno_game_of.model.Players
import com.example.practica_uno_game_of.service.LevelsService
import com.example.practica_uno_game_of.service.PlayersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RestController
@RequestMapping("/Players")   //endpoint
class PlayersController {
    @Autowired
    lateinit var modeloService: PlayersService

    @GetMapping
    fun list ():List <Players>{
        return modeloService.list()
    }
    //Peticiones post - Clase controller
    @PostMapping
    fun save (@RequestBody modelo: Players): ResponseEntity<Players> {
        return ResponseEntity(modeloService.save(modelo), HttpStatus.OK)
    }
    //clase controller - Petición Put
    @PutMapping
    fun update (@RequestBody modelo: Players): ResponseEntity<Players> {
        return ResponseEntity(modeloService.update(modelo), HttpStatus.OK)
    }
    //clase  controller-Petiicon Patch
    @PatchMapping
    fun updateName (@RequestBody modelo: Players): ResponseEntity<Players> {
        return ResponseEntity(modeloService.update(modelo), HttpStatus.OK)
    }
    //clase  controller - Petición Delete
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return modeloService.delete(id)
    }
    //GET BY ID Clase Controller
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(modeloService.listById (id), HttpStatus.OK)

    }
}