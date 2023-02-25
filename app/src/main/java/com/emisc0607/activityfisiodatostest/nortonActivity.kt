package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityNortonBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class nortonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNortonBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNortonBinding.inflate(layoutInflater)
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
        val nor = Array(5, { i -> 0 })
        if (binding.rbNor1.isChecked()) {
            nor[0] = 1
        }
        if (binding.rbNor2.isChecked()) {
            nor[0] = 2
        }
        if (binding.rbNor3.isChecked()) {
            nor[0] = 3
        }
        if (binding.rbNor4.isChecked()) {
            nor[0] = 4
        }
        if (binding.rbNor5.isChecked()) {
            nor[1] = 1
        }
        if (binding.rbNor6.isChecked()) {
            nor[1] = 2
        }
        if (binding.rbNor7.isChecked()) {
            nor[1] = 3
        }
        if (binding.rbNor8.isChecked()) {
            nor[1] = 4
        }
        if (binding.rbNor9.isChecked()) {
            nor[2] = 1
        }
        if (binding.rbNor10.isChecked()) {
            nor[2] = 2
        }
        if (binding.rbNor11.isChecked()) {
            nor[2] = 3
        }
        if (binding.rbNor12.isChecked()) {
            nor[2] = 4
        }
        if (binding.rbNor13.isChecked()) {
            nor[3] = 1
        }
        if (binding.rbNor14.isChecked()) {
            nor[3] = 2
        }
        if (binding.rbNor15.isChecked()) {
            nor[3] = 3
        }
        if (binding.rbNor16.isChecked()) {
            nor[3] = 4
        }
        if (binding.rbNor17.isChecked()) {
            nor[4] = 1
        }
        if (binding.rbNor18.isChecked()) {
            nor[4] = 2
        }
        if (binding.rbNor19.isChecked()) {
            nor[4] = 3
        }
        if (binding.rbNor20.isChecked()) {
            nor[4] = 4
        }
        val r = ContextCompat.getColor(this, R.color.red)
        val o = ContextCompat.getColor(this, R.color.orange)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)
        if (nor.sum() < 10) {
            binding.tvRes.setText("Riesgo muy alto:\t${nor.sum()}")
            binding.tvRes.setBackgroundColor(r)
        } else if (nor.sum() in 10..12) {
            binding.tvRes.setText("Riesgo alto:\t${nor.sum()}")
            binding.tvRes.setBackgroundColor(o)
        } else if (nor.sum() in 13..16) {
            binding.tvRes.setText("Riesgo medio:\t${nor.sum()}")
            binding.tvRes.setBackgroundColor(y)
        } else if (nor.sum() > 16) {
            binding.tvRes.setText("Riesgo bajo: \t${nor.sum()}")
            binding.tvRes.setBackgroundColor(g)
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_norton), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}