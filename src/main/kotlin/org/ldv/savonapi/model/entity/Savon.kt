package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity
@Table(name ="savon")
class Savon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var savonId:Long?,
    var nom:String,
    var type:String,
    var poids:Float,
    var prix: Float,
    var description: String,
    var apportEnEau:Float,
    var surgraisse:Float,
    var qteAlcalin:Float,
    var ConcentrationAlcalin:Float,
    var avecSoude:Boolean,
    var resultats:List<Resultat>?,

    @ManyToOne
    @JoinColumn(name ="Id")
    var id: Id?= null,

    @OneToMany(mappedBy = "savon")
    var resultat: MutableList<Resultat> = mutableListOf(),

    @OneToMany(mappedBy = "savon")
    var ligneIngredient: MutableList<LigneIngredient> = mutableListOf(),

    ) {
    fun calculQteAlcalin() {
        var qteAlcainNormal = 0.0
        if (this.avecSoude) {
            for (uneLigne in this.ligneIngredient) {
                qteAlcainNormal += uneLigne.quantite * uneLigne.ingredient.sopa * (40.0 / 56 / 1000)
            }
        } else {
            qteAlcalin =
                this.ligneIngredient.sumOf { ligneIngredient: LigneIngredient -> (ligneIngredient.quantite.toDouble() * ligneIngredient.ingredient!!.sopa) * 100 }
                    .toFloat()

        }
        qteAlcalin = qteAlcalin / (ConcentrationAlcalin / 100)

        qteAlcalin -= (qteAlcalin * surgraisse / 100)

    }

    fun calculApportEau() {
        var concentrationEau = (100 - ConcentrationAlcalin / 100)
        var apportEau = qteAlcalin * concentrationEau
    }

    fun calculScoreNonPondere() {
        var isn = this.ligneIngredient.sumOf { it.ingredient!!.isn.toDouble() * it.pourcentage / 100 }
        var iode = this.ligneIngredient.sumOf { it.ingredient!!.iode.toDouble() * it.pourcentage / 100 }

        this.resultat.find { it.caracteristique!!.nom == "isn" }!!.score = isn.toFloat()
        this.resultat.find { it.caracteristique!!.nom == "iode" }!!.score = iode.toFloat()
    }
}
