package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "caracteristique")
class Caracteristique(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var caracteristiqueId: Long,
    var nom:String,

    @OneToMany(mappedBy = "caracteristique")
    var mention: MutableList<Mention>? = null,

    @OneToMany(mappedBy = "caracteristique")
    var resultat: MutableList<Resultat>? = null

) {
}