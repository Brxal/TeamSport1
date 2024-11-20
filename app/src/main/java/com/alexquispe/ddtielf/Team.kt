package com.alexquispe.ddtielf

import java.io.Serializable

data class Team(
    val name: String,
    val sport: String,
    val category: String,
    val description: String
) : Serializable
