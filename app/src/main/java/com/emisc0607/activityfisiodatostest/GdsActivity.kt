package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityGdsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class GdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGdsBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGdsBinding.inflate(layoutInflater)
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
        val gds = Array(15) { 0 }
        if (binding.sGDS1.isChecked) gds[0] = 0
        else gds[0] = 1
        if (binding.sGDS2.isChecked) gds[1] = 1
        else gds[1] = 0
        if (binding.sGDS3.isChecked) gds[2] = 1
        else gds[2] = 0
        if (binding.sGDS4.isChecked) gds[3] = 1
        else gds[3] = 0
        if (binding.sGDS5.isChecked) gds[4] = 0
        else gds[4] = 1
        if (binding.sGDS6.isChecked) gds[5] = 1
        else gds[5] = 0
        if (binding.sGDS7.isChecked) gds[6] = 0
        else gds[6] = 1
        if (binding.sGDS8.isChecked) gds[7] = 1
        else gds[7] = 0
        if (binding.sGDS9.isChecked) gds[8] = 1
        else gds[8] = 0
        if (binding.sGDS10.isChecked) gds[9] = 1
        else gds[9] = 0
        if (binding.sGDS11.isChecked) gds[10] = 0
        else gds[10] = 1
        if (binding.sGDS12.isChecked) gds[11] = 1
        else gds[11] = 0
        if (binding.sGDS13.isChecked) gds[12] = 0
        else gds[12] = 1
        if (binding.sGDS14.isChecked) gds[13] = 1
        else gds[13] = 0
        if (binding.sGDS15.isChecked) gds[14] = 1
        else gds[14] = 0
        val r = ContextCompat.getColor(this, R.color.red)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)
        if (gds.sum() < 5) {
            binding.tvRes.setBackgroundColor(g)
            binding.tvRes.text = "Normal:\t${gds.sum()}"
        } else {
            binding.tvRes.setBackgroundColor(y)
            binding.tvRes.text = "Presencia de síntomas depresivos:\t${gds.sum()}"
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_gds), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}