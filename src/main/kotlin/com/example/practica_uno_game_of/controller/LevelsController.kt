package com.example.practica_uno_game_of.controller

import com.example.practica_uno_game_of.model.LevelsModel
import com.example.practica_uno_game_of.service.LevelsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


data class JSendResponse<T>(
    val status: String,
    val data: T? = null,
    val message: String? = null
)

@RestController
@RequestMapping("/api/levels")   //endpoint

@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])

class LevelsController {
    @Autowired
    lateinit var modeloService: LevelsService

    @GetMapping
    fun list(): ResponseEntity<JSendResponse<List<LevelsModel>>> {
        val levelsList = modeloService.list()
        return ResponseEntity.ok(JSendResponse("success", levelsList))
    }
    //Peticiones post - Clase controller
    @PostMapping
    fun save(@RequestBody modelo: LevelsModel): ResponseEntity<JSendResponse<LevelsModel>> {
        val savedModel = modeloService.save(modelo)
        return ResponseEntity.status(HttpStatus.CREATED).body(JSendResponse("success", savedModel))
    }
    //clase controller - Petición Put
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody modelo: LevelsModel): ResponseEntity<JSendResponse<LevelsModel>> {
        val updatedModel = modeloService.update(id, modelo)
        return ResponseEntity.ok(JSendResponse("success", updatedModel))
    }
    //clase  controller-Petiicon Patch
    @PatchMapping("/{id}")
    fun updateName(@PathVariable id: Long, @RequestBody levelName: String): ResponseEntity<JSendResponse<LevelsModel>> {
        val updatedModel = modeloService.updateName(id, levelName)
        return ResponseEntity.ok(JSendResponse("success", updatedModel))
    }
    //clase  controller - Petición Delete
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<JSendResponse<String>> {
        val deleted = modeloService.delete(id)
        return if (deleted) {
            ResponseEntity.ok(JSendResponse("success", "Level deleted successfully"))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(JSendResponse("fail", null, "Level not found"))
        }
    }
    //GET BY ID Clase Controller
    @GetMapping("/{id}")
    fun listById(@PathVariable id: Long): ResponseEntity<JSendResponse<LevelsModel>> {
        val level = modeloService.listById(id)
        return if (level != null) {
            ResponseEntity.ok(JSendResponse("success", level))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(JSendResponse("fail", null, "Level not found"))
        }
    }
}