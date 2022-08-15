package com.example.dts_minggu_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.graphics.toColorInt
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val FILENAME : String = "namafile.txt"
    lateinit var txtBaca : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_create = findViewById<Button>(R.id.btn_create)
        val btn_update = findViewById<Button>(R.id.btn_update)
        val btn_read = findViewById<Button>(R.id.btn_read)
        val btn_delete = findViewById<Button>(R.id.btn_delete)

        txtBaca = findViewById(R.id.txt_baca)

        btn_create.setOnClickListener(this)
        btn_update.setOnClickListener(this)
        btn_read.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_create -> buatFile()
            R.id.btn_read -> bacaFile()
            R.id.btn_update -> ubahFile()
            R.id.btn_delete -> hapusFile()
        }
    }

    fun buatFile() {
        var isiFile : String = "Coba Isi Data File Text"
        var file : File = File(filesDir, FILENAME)

        var outputStream : FileOutputStream? = null
        try {
            file.createNewFile()
            outputStream = FileOutputStream(file, true)
            outputStream.write(isiFile.toByteArray())

            outputStream.flush()
            outputStream.close()
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    fun ubahFile() {
        var ubah : String = "Update Isi Data File Text"
        var file : File = File(filesDir, FILENAME)

        var outputStream : FileOutputStream? = null
        try {
            file.createNewFile()
            outputStream = FileOutputStream(file, false)
            outputStream.write(ubah.toByteArray())

            outputStream.flush()
            outputStream.close()
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    fun bacaFile() {
        var sdCard : File = filesDir
        var file : File = File(sdCard, FILENAME)

        if(file.exists()){
            var text : StringBuilder = StringBuilder()
            try{
                var br : BufferedReader = BufferedReader(FileReader(file))
                var line : String? = br?.readLine()

                while(line != null){
                    text.append(line)
                    line = br.readLine()
                }
                br.close()
            }catch (e : IOException){
                println("Error : " + e.message)
            }
            txtBaca.text = text.toString()
        }
    }

    fun hapusFile() {
        var file : File = File(filesDir, FILENAME)
        if(file.exists()){
            file.delete()
            txtBaca.text = " "
        }
    }
}