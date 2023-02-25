package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityLawtonBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class lawtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLawtonBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLawtonBinding.inflate(layoutInflater)
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
        val law = Array(8, { i -> 0 })
        //Usar el telefono
        if (binding.rbLaw1.isChecked()) {
            law[0] = 1
        }
        if (binding.rbLaw2.isChecked()) {
            law[0] = 1
        }
        if (binding.rbLaw3.isChecked()) {
            law[0] = 1
        }
        if (binding.rbLaw4.isChecked()) {
            law[0] = 0
        }
        //hacer compras
        if (binding.rbLaw5.isChecked()) {
            law[1] = 1
        }
        if (binding.rbLaw6.isChecked()) {
            law[1] = 0
        }
        if (binding.rbLaw7.isChecked()) {
            law[1] = 0
        }
        if (binding.rbLaw8.isChecked()) {
            law[1] = 0
        }
        //Preparacion de comida
        if (binding.rbLaw9.isChecked()) {
            law[2] = 1
        }
        if (binding.rbLaw10.isChecked()) {
            law[2] = 0
        }
        if (binding.rbLaw11.isChecked()) {
            law[2] = 0
        }
        if (binding.rbLaw12.isChecked()) {
            law[2] = 0
        }
        //cuidado de la casa
        if (binding.rbLaw13.isChecked()) {
            law[3] = 1
        }
        if (binding.rbLaw14.isChecked()) {
            law[3] = 1
        }
        if (binding.rbLaw15.isChecked()) {
            law[3] = 1
        }
        if (binding.rbLaw16.isChecked()) {
            law[3] = 1
        }
        if (binding.rbLaw17.isChecked()) {
            law[3] = 0
        }
        //lavado de ropa
        if (binding.rbLaw18.isChecked()) {
            law[4] = 1
        }
        if (binding.rbLaw19.isChecked()) {
            law[4] = 1
        }
        if (binding.rbLaw20.isChecked()) {
            law[4] = 0
        }
        //uso de medios de transporte
        if (binding.rbLaw21.isChecked()) {
            law[5] = 1
        }
        if (binding.rbLaw22.isChecked()) {
            law[5] = 1
        }
        if (binding.rbLaw23.isChecked()) {
            law[5] = 1
        }
        if (binding.rbLaw24.isChecked()) {
            law[5] = 0
        }
        if (binding.rbLaw25.isChecked()) {
            law[5] = 0
        }
        //responsabilidad medicacion
        if (binding.rbLaw26.isChecked()) {
            law[6] = 1
        }
        if (binding.rbLaw27.isChecked()) {
            law[6] = 0
        }
        if (binding.rbLaw28.isChecked()) {
            law[6] = 0
        }
        //manejo asuntos
        if (binding.rbLaw29.isChecked()) {
            law[7] = 1
        }
        if (binding.rbLaw30.isChecked()) {
            law[7] = 1
        }
        if (binding.rbLaw31.isChecked()) {
            law[7] = 0
        }
        val r = ContextCompat.getColor(this, R.color.red)
        val o = ContextCompat.getColor(this, R.color.orange)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val b = ContextCompat.getColor(this, R.color.blue)
        val g = ContextCompat.getColor(this, R.color.green)
        if (law.sum() < 2) {
            binding.tvRes.setText("Dependencia total:\t${law.sum()}")
            binding.tvRes.setBackgroundColor(r)
        } else if (law.sum() in 2..3) {
            binding.tvRes.setText("Dependencia severa:\t${law.sum()}")
            binding.tvRes.setBackgroundColor(o)
        } else if (law.sum() in 4..5) {
            binding.tvRes.setText("Dependencia moderada:\t${law.sum()}")
            binding.tvRes.setBackgroundColor(y)
        } else if (law.sum() in 6..7) {
            binding.tvRes.setText("Dependencia escasa:\t${law.sum()}")
            binding.tvRes.setBackgroundColor(b)
        } else if (law.sum() > 7) {
            binding.tvRes.setText("Independencia: \t${law.sum()}")
            binding.tvRes.setBackgroundColor(g)
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_lawton), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}