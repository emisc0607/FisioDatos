package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityBradenBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class bradenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBradenBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBradenBinding.inflate(layoutInflater)
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
        val bra = Array(6, { i -> 0 })
        if (binding.rbBra1.isChecked()) {
            bra[0] = 1
        }
        if (binding.rbBra2.isChecked()) {
            bra[0] = 2
        }
        if (binding.rbBra3.isChecked()) {
            bra[0] = 3
        }
        if (binding.rbBra4.isChecked()) {
            bra[0] = 4
        }
        if (binding.rbBra5.isChecked()) {
            bra[1] = 1
        }
        if (binding.rbBra6.isChecked()) {
            bra[1] = 2
        }
        if (binding.rbBra7.isChecked()) {
            bra[1] = 3
        }
        if (binding.rbBra8.isChecked()) {
            bra[1] = 4
        }
        if (binding.rbBra9.isChecked()) {
            bra[2] = 1
        }
        if (binding.rbBra10.isChecked()) {
            bra[2] = 2
        }
        if (binding.rbBra11.isChecked()) {
            bra[2] = 3
        }
        if (binding.rbBra12.isChecked()) {
            bra[2] = 4
        }
        if (binding.rbBra13.isChecked()) {
            bra[3] = 1
        }
        if (binding.rbBra14.isChecked()) {
            bra[3] = 2
        }
        if (binding.rbBra15.isChecked()) {
            bra[3] = 3
        }
        if (binding.rbBra16.isChecked()) {
            bra[3] = 4
        }
        if (binding.rbBra17.isChecked()) {
            bra[4] = 1
        }
        if (binding.rbBra18.isChecked()) {
            bra[4] = 2
        }
        if (binding.rbBra19.isChecked()) {
            bra[4] = 3
        }
        if (binding.rbBra20.isChecked()) {
            bra[4] = 4
        }
        if (binding.rbBra21.isChecked()) {
            bra[5] = 1
        }
        if (binding.rbBra22.isChecked()) {
            bra[5] = 2
        }
        if (binding.rbBra23.isChecked()) {
            bra[5] = 3
        }
        val r = ContextCompat.getColor(this, R.color.red)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)
        if (bra.sum() < 12) {
            binding.tvRes.text = "Riesgo alto:\t${bra.sum()}"
            binding.tvRes.setBackgroundColor(r)
        } else if (bra.sum() in 13..16) {
            binding.tvRes.text = "Riesgo moderado:\t${bra.sum()}"
            binding.tvRes.setBackgroundColor(y)
        } else if (bra.sum() > 16) {
            binding.tvRes.text = "Riesgo bajo: \t${bra.sum()}"
            binding.tvRes.setBackgroundColor(g)
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_braden) , binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}