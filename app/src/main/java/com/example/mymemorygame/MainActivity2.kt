package com.example.mymemorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import java.util.Collections.shuffle

class MainActivity2 : AppCompatActivity() {
    var myTablero: ArrayList<ImageButton> = arrayListOf<ImageButton>()
    var arrayDesordenado: ArrayList<Int> = arrayListOf<Int>()
    var imagenes: ArrayList<Int> = arrayListOf<Int>()
    var txtPuntuacion: TextView? = null
    var fondo: Int = 0
    var bloqueo: Boolean = false
    var primero: ImageButton? = null
    var numeroPrimero: Int = 0
    var numeroSegundo: Int = 0
    var aciertos: Int = 0
    var puntuacion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()
    }

    private fun init(){
        cargarBotones()
        cargarTablero()
        cargarTexto()
        cargarImagenes()
        arrayDesordenado = barajar(imagenes.size)

        object: CountDownTimer(2000, 1){
            override fun onTick(p0: Long) {
                for(i: Int in 0 until myTablero.size){
                    myTablero[i].scaleType = ImageView.ScaleType.CENTER_CROP
                    myTablero[i].setImageResource(imagenes[arrayDesordenado.get(i)])
                }
            }
            override fun onFinish() {
                for(i: Int in 0 until myTablero.size){
                    myTablero[i].scaleType = ImageView.ScaleType.CENTER_CROP
                    myTablero[i].setImageResource(fondo)
                }
            }
        }.start()

        for(i: Int in 0 until myTablero.size){
            val j: Int = i
            myTablero[i].isEnabled
            myTablero[i].setOnClickListener{
                if(!bloqueo){
                    comprobar(j, myTablero[j])
                }
            }
        }
    }
    private fun cargarTexto(){
        txtPuntuacion = findViewById(R.id.txtScore)
    }
    private fun cargarBotones(){
        val btnReiniciar: Button = findViewById(R.id.btnReiniciar)
        val btnSalir: Button = findViewById(R.id.btnSalir)

        btnReiniciar.setOnClickListener {
            imagenes.clear()
            myTablero.clear()
            arrayDesordenado.clear()
            aciertos = 0
            puntuacion = 0
            txtPuntuacion!!.setText("Puntuacion: $puntuacion")
            init()
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
    private fun cargarTablero(){
        val img00: ImageButton = findViewById(R.id.btn00)
        val img01: ImageButton = findViewById(R.id.btn01)
        val img02: ImageButton = findViewById(R.id.btn02)
        val img03: ImageButton = findViewById(R.id.btn03)
        val img04: ImageButton = findViewById(R.id.btn04)
        val img05: ImageButton = findViewById(R.id.btn05)
        val img06: ImageButton = findViewById(R.id.btn06)
        val img07: ImageButton = findViewById(R.id.btn07)
        val img08: ImageButton = findViewById(R.id.btn08)
        val img09: ImageButton = findViewById(R.id.btn09)
        val img10: ImageButton = findViewById(R.id.btn10)
        val img11: ImageButton = findViewById(R.id.btn11)
        val img12: ImageButton = findViewById(R.id.btn12)
        val img13: ImageButton = findViewById(R.id.btn13)
        val img14: ImageButton = findViewById(R.id.btn14)
        val img15: ImageButton = findViewById(R.id.btn15)

        myTablero.add(img00)
        myTablero.add(img01)
        myTablero.add(img02)
        myTablero.add(img03)
        myTablero.add(img04)
        myTablero.add(img05)
        myTablero.add(img06)
        myTablero.add(img07)
        myTablero.add(img08)
        myTablero.add(img09)
        myTablero.add(img10)
        myTablero.add(img11)
        myTablero.add(img12)
        myTablero.add(img13)
        myTablero.add(img14)
        myTablero.add(img15)

    }
    private fun cargarImagenes(){
        imagenes.add(R.drawable.goku_0)
        imagenes.add(R.drawable.goku_1)
        imagenes.add(R.drawable.goku_2)
        imagenes.add(R.drawable.goku_3)
        imagenes.add(R.drawable.goku_4)
        imagenes.add(R.drawable.goku_5)
        imagenes.add(R.drawable.goku_6)
        imagenes.add(R.drawable.goku_7)

        fondo = R.drawable.goku_bkgd
    }
    private fun barajar(longitud: Int): ArrayList<Int>{
        var result:ArrayList<Int> = arrayListOf<Int>()
        for (i: Int in 0 until (longitud*2) ){
            result.add(i % longitud)
        }
        shuffle(result)
        return result
    }
    private fun comprobar(i: Int, j: ImageButton){
        if(primero == null){
            primero = j
            primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
            primero!!.setImageResource(imagenes[arrayDesordenado.get(i)])
            primero!!.isEnabled = false
            numeroPrimero = arrayDesordenado.get(i)
        } else {
            bloqueo = true
            j.scaleType = ImageView.ScaleType.CENTER_CROP
            j.setImageResource(imagenes[arrayDesordenado.get(i)])
            j.isEnabled = false
            numeroSegundo = arrayDesordenado.get(i)

            if(numeroPrimero == numeroSegundo){
                primero = null
                bloqueo = false
                aciertos++
                puntuacion++
                txtPuntuacion!!.setText("Puntuacion: $puntuacion")

                if (aciertos == imagenes.size){
                    Toast.makeText(this, "Felicitaciones!! Ganaste!", Toast.LENGTH_LONG).show()
                }
            } else {

                object: CountDownTimer(1000, 1){
                    override fun onTick(p0: Long) {
                    }
                    override fun onFinish() {
                        primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
                        primero!!.setImageResource(fondo)
                        primero!!.isEnabled = true
                        j.scaleType = ImageView.ScaleType.CENTER_CROP
                        j.setImageResource(fondo)
                        j.isEnabled = true
                        bloqueo = false
                        primero = null
                        puntuacion--
                        txtPuntuacion!!.setText("Puntuacion: $puntuacion")
                    }
                }.start()
            }
        }
    }
}
