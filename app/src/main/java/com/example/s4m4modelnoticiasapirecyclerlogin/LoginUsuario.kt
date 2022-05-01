package com.example.s4m4modelnoticiasapirecyclerlogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.s4m4modelnoticiasapirecyclerlogin.databinding.ActivityMainBinding
import com.example.s4m4modelnoticiasapirecyclerlogin.retrofit.RestEngine
import com.example.s4m4modelnoticiasapirecyclerlogin.retrofit.UsuarioAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class LoginUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarse.setOnClickListener {
            val intent: Intent = Intent(applicationContext, RegistroUsuario::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {

                var usu = binding.txtUsuario.text.toString()
                var cont = binding.txtContrasena.text.toString()

                var aux2 = validarUsuarioLogin(usu, cont)

                if (aux2 == 0){
                    runOnUiThread{
                        Toast.makeText(applicationContext, "Ups! El usuario no existe!", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                    }
                }else{
                    val intent: Intent = Intent(applicationContext, OtraAct::class.java)
                    intent.putExtra("usuario", usu)
                    startActivity(intent)
                }
            }
        }
    }

    fun validarUsuarioLogin(usuario: String, pass: String): Int {
        val llamada: UsuarioAPIService =
            RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado: Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u: Usuario? = resultado.execute().body()
        var aux: Int = 0
        for (i in u!!) {
            if (i.usuario == usuario && i.contraseña == pass) {
                aux = 1
            } else {
                aux = 0
            }
        }
        return aux
    }
}


