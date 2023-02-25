package com.emisc0607.activityfisiodatostest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityBateryBrBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class BateryBrActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBateryBrBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBateryBrBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showExp()
        binding.bRes.setOnClickListener { switches() }
        binding.bSave.setOnClickListener {
            if(binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(binding.root, "Presione RESULTADO antes de guardar", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun switches() {
        val bbr=Array(3) { 0 }
        if (binding.etBbr1.text.isEmpty() or binding.etBbr4.text.isEmpty() or binding.etBbr7.text.isEmpty() or
            binding.etBbr2.text.isEmpty() or binding.etBbr5.text.isEmpty() or binding.etBbr8.text.isEmpty() or
            binding.etBbr3.text.isEmpty() or binding.etBbr6.text.isEmpty() or binding.etBbr10.text.isEmpty()) {
            if (binding.etBbr1.text.isEmpty()) binding.etBbr1.error = "Requerido"
            if (binding.etBbr2.text.isEmpty()) binding.etBbr2.error = "Requerido"
            if (binding.etBbr3.text.isEmpty()) binding.etBbr3.error = "Requerido"
            if (binding.etBbr4.text.isEmpty()) binding.etBbr4.error = "Requerido"
            if (binding.etBbr5.text.isEmpty()) binding.etBbr5.error = "Requerido"
            if (binding.etBbr6.text.isEmpty()) binding.etBbr6.error = "Requerido"
            if (binding.etBbr7.text.isEmpty()) binding.etBbr7.error = "Requerido"
            if (binding.etBbr8.text.isEmpty()) binding.etBbr8.error = "Requerido"
            if (binding.etBbr10.text.isEmpty()) binding.etBbr10.error = "Requerido"
            Toast.makeText(this, "No deje ningún campo vacío", Toast.LENGTH_LONG).show()
        } else {
            val eq1=(binding.etBbr1.text.toString().toDouble() + binding.etBbr2.text.toString().toDouble() +
                    binding.etBbr3.text.toString().toDouble() + binding.etBbr4.text.toString().toDouble() +
                    binding.etBbr5.text.toString().toDouble() + binding.etBbr6.text.toString().toDouble()) / 6.0
            val cam=(binding.etBbr7.text.toString().toDouble() + binding.etBbr8.text.toString().toDouble()) / 2.0
            val lys= binding.etBbr10.text.toString().toDouble()
            var apoyo= ""
            if (binding.rbBbr1.isChecked){
                apoyo= binding.rbBbr1.text.toString()
            }
            if (binding.rbBbr2.isChecked){
                apoyo= binding.rbBbr2.text.toString()
            }
            if (binding.rbBbr3.isChecked){
                apoyo= binding.rbBbr3.text.toString()
            }
            //Equlilibrio
            if (eq1>10.0){
                bbr[0]= 2
            }
            else if (eq1 in 3.0..10.0){
                bbr[0]= 1
            }
            else if (eq1 < 3.0){
                bbr[0]= 0
            }
            //Caminar
            if (cam>8.70){
                bbr[1]= 1
            }
            else if (cam in 6.21..8.70){
                bbr[1]= 2
            }
            else if (cam in 4.82..6.20){
                bbr[1]= 3
            }
            else if (cam < 4.81){
                bbr[1]= 4
            }
            //Levantarse y sentarse
            if (lys>60.0){
                bbr[1]= 0
            }
            else if (cam in 16.70..60.0){
                bbr[2]= 1
            }
            else if (cam in 13.70..16.69){
                bbr[2]= 2
            }
            else if (cam in 11.20..13.69){
                bbr[2]= 3
            }
            else if (cam < 11.19){
                bbr[2]= 4
            }
            val y = ContextCompat.getColor(this, R.color.yellow)
            val g = ContextCompat.getColor(this, R.color.green)
            if(bbr.sum()<8){
                binding.tvRes.setBackgroundColor(y)
                binding.tvRes.text = "Desempeño físico bajo:\t${bbr.sum()}\nUtilizó como ayuda:\t$apoyo"
            }
            else{
                binding.tvRes.setBackgroundColor(g)
                binding.tvRes.text = "Desempeño físico normal:\t${bbr.sum()}\nUtilizó como ayuda:\t$apoyo\n"
            }
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_bateria), binding.tvRes.text.toString()
            )
        finish()
    }
    fun showExp(){
        val bundle=intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text="$exp"
    }
}