package org.ldv.savonapi.model.controller

import org.ldv.savonapi.model.dao.IngredientDAO
import org.ldv.savonapi.model.dao.MentionDAO
import org.ldv.savonapi.model.entity.Ingredient
import org.ldv.savonapi.model.entity.Mention
import org.ldv.savonapi.model.entity.Savon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

class MentionController(private val mentionDAO: MentionDAO) {

    @GetMapping
    fun getAllMention():List<Mention> {
        return mentionDAO.findAll()
    }

    @GetMapping("/{id}")
    fun getMentionById(@PathVariable id: Long): ResponseEntity<Mention> {
        val mention = mentionDAO.findById(id)
        return if (mention.isPresent) {
            ResponseEntity.ok(mention.get())
        } else {
           ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createMention(@RequestBody mention: Mention): ResponseEntity<Mention> {
        val savedMention = mentionDAO.save(mention)
        return ResponseEntity.ok(savedMention)
    }

    @_root_ide_package_.org.springframework.web.bind.annotation.PutMapping("/{id}")
    fun updateMention(
        @PathVariable id: Long,
        @RequestBody mention: Mention
    ): ResponseEntity<Mention> {
        return if (mentionDAO.existsById(id)) {
            val updatedMention = mentionDAO.save(mention)
            ResponseEntity.ok(updatedMention)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMention(@PathVariable id: Long):ResponseEntity<Void> {
        return if (mentionDAO.existsById(id)) {
            mentionDAO.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
