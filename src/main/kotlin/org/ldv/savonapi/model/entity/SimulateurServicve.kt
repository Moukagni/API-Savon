package org.ldv.savonapi.model.entity

import jakarta.persistence.*
import org.ldv.savonapi.model.dao.*
import org.springframework.stereotype.Service

@Service
class SimulateurServicve(
    val caracteristiqueDAO: CaracteristiqueDAO,
    val savonDAO: SavonDAO,
    val ingredientDAO: IngredientDAO,
    val ligneIngredientDAO: LigneIngredientDAO,
    val mentionDAO: MentionDAO,
    val resultatDAO: ResultatDAO,
)