package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity
class LigneIngredient(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val ligneIngredientId: Long,
    @ManyToOne
    var ingredient: Ingredient,
    var quantite:Float,
    var pourcentage:Float,

    @MapsId("recetteId")
    @ManyToOne
    @JoinColumn(name ="recetteId")
    var savon: Savon? = null,
{

}




