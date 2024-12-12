package org.ldv.savonapi.model.controller

import org.ldv.savonapi.model.dao.SavonDAO
import org.ldv.savonapi.model.entity.Savon
import org.ldv.savonapi.model.entity.SimulateurServicve
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping
class SavonController(private val savonDAO: SavonDAO) {

   @GetMapping
   fun getAllSavon():List<Savon> {
      return savonDAO.findAll()
   }

   @GetMapping("/{id}")
   fun getSavonById(@PathVariable id: Long): ResponseEntity<Savon> {
      val savon = savonDAO.findById(id)
      return if (savon.isPresent) {
         ResponseEntity.ok(savon.get())
      } else {
         ResponseEntity.notFound().build()
      }
   }

   @PostMapping
   fun createSavon(@RequestBody savon: Savon): ResponseEntity<Savon> {
      val savedSavon = savonDAO.save(savon)
      return ResponseEntity.ok(savedSavon)
   }

   @PutMapping("/{id}")
   fun updateIngredient(
      @PathVariable id: Long,
      @RequestBody savon: Savon
   ): ResponseEntity<Savon> {
      return if (savonDAO.existsById(id)) {
         val updatedIngredient = savonDAO.save(savon)
         ResponseEntity.ok(updatedIngredient)
      } else {
         ResponseEntity.notFound().build()
      }
   }

   @DeleteMapping("/{id}")
   fun deleteIngredient(@PathVariable id: Long): ResponseEntity<Void> {
      return if (savonDAO.existsById(id)) {
         savonDAO.deleteById(id)
         ResponseEntity.noContent().build()
      } else {
         ResponseEntity.notFound().build()
      }
   }
}
