package org.ldv.savonapi.model.DTO

import org.ldv.savonapi.model.entity.Ingredient

class LigneIngredientDTO(
    var ingredient: Ingredient,
    var savonId: Long,
    var quantite: Float,
    var pourcentage: Float,
)
