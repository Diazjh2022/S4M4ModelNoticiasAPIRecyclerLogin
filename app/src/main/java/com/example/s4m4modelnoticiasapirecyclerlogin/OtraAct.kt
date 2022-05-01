package com.example.s4m4modelnoticiasapirecyclerlogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.s4m4modelnoticiasapirecyclerlogin.databinding.ActivityOtraBinding

class OtraAct : AppCompatActivity() {

    lateinit var binding: ActivityOtraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var usuarioLogeado:String = intent.getStringExtra("usuario").toString()

        binding.textView2.text = "Bienvenido/a $usuarioLogeado"
    }
}