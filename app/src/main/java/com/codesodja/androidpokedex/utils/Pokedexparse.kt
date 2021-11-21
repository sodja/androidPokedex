package com.codesodja.androidpokedex.utils

import androidx.compose.ui.graphics.Color
import com.codesodja.androidpokedex.R
import com.codesodja.androidpokedex.data.remote.responses.Stat
import com.codesodja.androidpokedex.data.remote.responses.Type
import java.util.*

fun parseTypeToColor(type: Type): Color {
    return when (type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> Color(R.color.TypeNormal)
        "fire" -> Color(R.color.TypeFire)
        "water" -> Color(R.color.TypeWater)
        "electric" -> Color(R.color.TypeElectric)
        "grass" -> Color(R.color.TypeGrass)
        "ice" -> Color(R.color.TypeIce)
        "fighting" -> Color(R.color.TypeFighting)
        "poison" -> Color(R.color.TypePoison)
        "ground" -> Color(R.color.TypeGround)
        "flying" -> Color(R.color.TypeFlying)
        "psychic" -> Color(R.color.TypePsychic)
        "bug" -> Color(R.color.TypeBug)
        "rock" -> Color(R.color.TypeRock)
        "ghost" -> Color(R.color.TypeGhost)
        "dragon" -> Color(R.color.TypeDragon)
        "dark" -> Color(R.color.TypeDark)
        "steel" -> Color(R.color.TypeSteel)
        "fairy" -> Color(R.color.TypeFairy)
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when (stat.stat.name.lowercase()) {
        "hp" -> Color(R.color.HPColor)
        "attack" -> Color(R.color.AtkColor)
        "defense" -> Color(R.color.DefColor)
        "special-attack" -> Color(R.color.SpAtkColor)
        "special-defense" -> Color(R.color.SpDefColor)
        "speed" -> Color(R.color.SpdColor)
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when (stat.stat.name.lowercase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}
