package com.example.s4m4modelnoticiasapirecyclerlogin.retrofit

data class Usuario(
    val bd: List<Bd>,
    val clave: String,
    val usuario: String
)

data class Bd(
    val clave: String,
    val usuario: String
)