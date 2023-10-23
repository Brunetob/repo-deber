package com.example.practica_uno_game_of.controller

import com.example.practica_uno_game_of.model.LevelsModel
import com.example.practica_uno_game_of.service.LevelsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/LevelsModel")   //endpoint
class LevelsController {
    @Autowired
    lateinit var modeloService: LevelsService

    @GetMapping
    fun list ():List <LevelsModel>{
        return modeloService.list()
    }
    //Peticiones post - Clase controller
    @PostMapping
    fun save (@RequestBody modelo:LevelsModel):ResponseEntity<LevelsModel>{
        return ResponseEntity(modeloService.save(modelo), HttpStatus.OK)
    }
    //clase controller - Petición Put
    @PutMapping
    fun update (@RequestBody modelo:LevelsModel):ResponseEntity<LevelsModel>{
        return ResponseEntity(modeloService.update(modelo), HttpStatus.OK)
    }
    //clase  controller-Petiicon Patch
    @PatchMapping
    fun updateName (@RequestBody modelo:LevelsModel):ResponseEntity<LevelsModel>{
        return ResponseEntity(modeloService.update(modelo), HttpStatus.OK)
    }
    //clase  controller - Petición Delete
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return modeloService.delete(id)
    }
    //GET BY ID Clase Controller
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(modeloService.listById (id), HttpStatus.OK)

    }
}