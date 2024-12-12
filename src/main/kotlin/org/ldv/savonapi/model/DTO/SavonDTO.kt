package org.ldv.savonapi.model.DTO

class SavonDTO(
    var Id: Long,
    var tite: String,
    var description: String,
    var surgraissage: Float,
    var avecSoude: Boolean,
    var concentrationAlcalin: Float,
    var lignIngredient: MutableList<LigneIngredientDTO> = mutableListOf(),
)