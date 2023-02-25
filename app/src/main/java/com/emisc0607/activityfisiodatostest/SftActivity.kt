package com.emisc0607.activityfisiodatostest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivitySftBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class SftActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySftBinding
    private val db =  FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySftBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showExp()
        binding.bRes.setOnClickListener { escala() }
        binding.bSave.setOnClickListener {
            if(binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(binding.root, "Presione RESULTADO antes de guardar", Snackbar.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun escala() {
        if (binding.etSft1.text.isNotEmpty() or binding.etSft2.text.isNotEmpty() or
            binding.etSft3.text.isNotEmpty() or binding.etSft4.text.isNotEmpty() or
            binding.etSft5.text.isNotEmpty() or binding.etSft6.text.isNotEmpty() or
            binding.etSft7.text.isNotEmpty() or binding.etSft8.text.isNotEmpty() or
            binding.etSft9.text.isNotEmpty() or binding.etSft10.text.isNotEmpty() or
            binding.etSft11.text.isNotEmpty() or binding.etSft12.text.isNotEmpty() or
            binding.etSft13.text.isNotEmpty() or binding.etSft14.text.isNotEmpty() or
            binding.etAge.text.isNotEmpty()){
            val s1=(binding.etSft1.text.toString().toDouble() + binding.etSft2.text.toString().toDouble()) / 2.0
            val s2=(binding.etSft3.text.toString().toDouble() + binding.etSft4.text.toString().toDouble()) / 2.0
            val s4=(binding.etSft5.text.toString().toDouble() + binding.etSft6.text.toString().toDouble()) / 2.0
            val s5=(binding.etSft7.text.toString().toDouble() + binding.etSft8.text.toString().toDouble()) / 2.0
            val s6=(binding.etSft9.text.toString().toDouble() + binding.etSft10.text.toString().toDouble()) / 2.0
            val s7=(binding.etSft11.text.toString().toDouble() + binding.etSft12.text.toString().toDouble()) / 2.0
            val s3=(binding.etSft13.text.toString().toDouble() + binding.etSft14.text.toString().toDouble()) / 2.0
            val age = binding.etAge.text.toString().toInt()
            val g = ContextCompat.getColor(this, R.color.green)
            val y = ContextCompat.getColor(this, R.color.yellow)
            if (binding.rbSex1.isChecked){
                if(age in 60..64){
                    if (s1 in 14.0..19.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 16.0..22.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 557.0..627.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 87.0..115.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -6.35..10.16) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -16.51..0.0) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 3.8..5.6) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 65..69){
                    if (s1 in 12.0..18.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 15.0..21.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 512.0..640.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 86.0..116.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -7.62..7.62) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -19.05..2.54) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 3.8..5.9) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 70..74){
                    if (s1 in 12.0..17.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s1 in 14.0..21.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s1 in 498.0..621.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s1 in 80.0..110.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s1 in -7.62..7.62) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s1 in -20.32..2.54) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s1 in 4.3..6.2) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 75..79){
                    if (s1 in 11.0..17.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 13.0..19.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 429.0..585.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 73.0..109.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -10.16..5.08) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -22.86..-5.8) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 4.6..7.2) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 80..84){
                    if (s1 in 10.0..15.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 13.0..19.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 406.0..553.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 71.0..103.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -13.97..3.81) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -24.13..-5.08) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 5.2..7.6) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 85..89){
                    if (s1 in 8.0..14.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 11.0..17.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 357.0..521.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 59.0..91.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -13.97..1.27) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -24.86..-7.62) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 5.5..8.9) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 90..94){
                    if (s1 in 7.0..12.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 10.0..14.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 278.0..457.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 52.0..86.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -16.51..1.27) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -26.67..-10.16) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 6.2..10.0) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
            }
            if (binding.rbSex2.isChecked){
                if(age in 60..64){
                    if (s1 in 12.0..17.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 13.0..19.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 498.0..603.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 75.0..107.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -1.27..12.7) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -7.62..3.81) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 4.4..6.0) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 65..69){
                    if (s1 in 11.0..16.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 12.0..18.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 457.0..580.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 73.0..107.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -1.27..11.43) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -8.89..3.81) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 4.8..6.4) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 70..74){
                    if (s1 in 10.0..15.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s1 in 12.0..17.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s1 in 438.0..563.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s1 in 68.0..101.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s1 in -2.54..10.16) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s1 in -10.16..2.54) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s1 in 4.9..7.1) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 75..79){
                    if (s1 in 10.0..15.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 11.0..17.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 397.0..534.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 68.0..100.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -3.81..8.89) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -12.7..1.27) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 5.2..7.4) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 80..84){
                    if (s1 in 9.0..14.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 10.0..16.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 352.0..493.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 60.0..90.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -5.08..7.62) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -13.97..0.0) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 5.7..8.7) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 85..89){
                    if (s1 in 8.0..13.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 10.0..15.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 310.0..466.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 55.0..85.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -6.35..6.35) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -17.78..-2.54) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in 6.2..9.6) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
                if(age in 90..94){
                    if (s1 in 4.0..11.0) {
                        binding.tvSft1.text = "Dentro del rango"
                        binding.tvSft1.setBackgroundColor(g)
                    } else{
                        binding.tvSft1.text = "Fuera del rango"
                        binding.tvSft1.setBackgroundColor(y)
                    }
                    if (s2 in 8.0..13.0) {
                        binding.tvSft2.text = "Dentro del rango"
                        binding.tvSft2.setBackgroundColor(g)
                    } else{
                        binding.tvSft2.text = "Fuera del rango"
                        binding.tvSft2.setBackgroundColor(y)
                    }
                    if (s3 in 251.0..402.0) {
                        binding.tvSft3.text = "Dentro del rango"
                        binding.tvSft3.setBackgroundColor(g)
                    } else{
                        binding.tvSft3.text = "Fuera del rango"
                        binding.tvSft3.setBackgroundColor(y)
                    }
                    if (s4 in 44.0..72.0) {
                        binding.tvSft4.text = "Dentro del rango"
                        binding.tvSft4.setBackgroundColor(g)
                    } else{
                        binding.tvSft4.text = "Fuera del rango"
                        binding.tvSft4.setBackgroundColor(y)
                    }
                    if (s5 in -11.43..2.54) {
                        binding.tvSft5.text = "Dentro del rango"
                        binding.tvSft5.setBackgroundColor(g)
                    } else{
                        binding.tvSft5.text = "Fuera del rango"
                        binding.tvSft5.setBackgroundColor(y)
                    }
                    if (s6 in -20.32..-2.54) {
                        binding.tvSft6.text = "Dentro del rango"
                        binding.tvSft6.setBackgroundColor(g)
                    } else{
                        binding.tvSft6.text = "Fuera del rango"
                        binding.tvSft6.setBackgroundColor(y)
                    }
                    if (s7 in -7.3..11.5) {
                        binding.tvSft7.text = "Dentro del rango"
                        binding.tvSft7.setBackgroundColor(g)
                    } else{
                        binding.tvSft7.text = "Fuera del rango"
                        binding.tvSft7.setBackgroundColor(y)
                    }
                }
            }
            binding.tvRes.text="Sentarse y levantarse de una silla:\t${binding.tvSft1.text}\n" +
                    "Flexiones del brazo:\t${binding.tvSft2.text}\n" +
                    "Caminar 6 minutos:\t${binding.tvSft3.text}\n" +
                    "2 minutos marcha:\t${binding.tvSft4.text}\n" +
                    "Flexión del tronco en silla:\t${binding.tvSft5.text}\n" +
                    "Juntar las manos tras la espalda:\t${binding.tvSft6.text}\n" +
                    "Levantarse, caminar y volverse a sentar:\t${binding.tvSft7.text}\n"
        }else{
            if (binding.etSft1.text.isEmpty()) binding.etSft1.error = "Requerido"
            if (binding.etSft2.text.isEmpty()) binding.etSft2.error = "Requerido"
            if (binding.etSft3.text.isEmpty()) binding.etSft3.error = "Requerido"
            if (binding.etSft4.text.isEmpty()) binding.etSft4.error = "Requerido"
            if (binding.etSft5.text.isEmpty()) binding.etSft5.error = "Requerido"
            if (binding.etSft6.text.isEmpty()) binding.etSft6.error = "Requerido"
            if (binding.etSft7.text.isEmpty()) binding.etSft7.error = "Requerido"
            if (binding.etSft8.text.isEmpty()) binding.etSft8.error = "Requerido"
            if (binding.etSft9.text.isEmpty()) binding.etSft9.error = "Requerido"
            if (binding.etSft10.text.isEmpty()) binding.etSft10.error = "Requerido"
            if (binding.etSft11.text.isEmpty()) binding.etSft11.error = "Requerido"
            if (binding.etSft12.text.isEmpty()) binding.etSft12.error = "Requerido"
            if (binding.etSft13.text.isEmpty()) binding.etSft13.error = "Requerido"
            if (binding.etSft14.text.isEmpty()) binding.etSft14.error = "Requerido"
            if (binding.etAge.text.isEmpty()) binding.etAge.error = "Requerido"
            Toast.makeText(this,"No deje ningún campo vacío", Toast.LENGTH_LONG).show()
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.senior_fitness_test), binding.tvRes.text.toString()
            )
        finish()
    }
    fun showExp(){
        val bundle=intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text="$exp"
    }
}