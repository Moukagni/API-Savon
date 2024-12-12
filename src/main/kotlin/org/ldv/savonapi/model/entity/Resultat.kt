package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "resultat")
class Resultat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    val resultatId: Long,
    var score: Float,
    var mention: String?,
    var caracteristique: String?,

    @MapsId("savonId")
    @ManyToOne
    @JoinColumn(name ="savonId")
    var savon: Savon? = null,

    @MapsId("caracteristiqueId")
    @ManyToOne
    @JoinColumn(name ="caracteristiqueId")
    var caracteristique: Caracteristique? = null,
)

{

}