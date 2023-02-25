package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityOarsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class oarsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOarsBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOarsBinding.inflate(layoutInflater)
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
        var oars = Array(11, { i -> 0 })
        //estado civil
        if (binding.rbOars1.isChecked()) {
            oars[0] = 1
        }
        if (binding.rbOars2.isChecked()) {
            oars[0] = 2
        }
        if (binding.rbOars3.isChecked()) {
            oars[0] = 3
        }
        if (binding.rbOars4.isChecked()) {
            oars[0] = 4
        }
        //conyuge en residencia
        if (binding.rbOars5.isChecked()) {
            oars[1] = 1
        }
        if (binding.rbOars6.isChecked()) {
            oars[1] = 2
        }
        //frecuencia salio
        if (binding.rbOars7.isChecked()) {
            oars[2] = 1
        }
        if (binding.rbOars8.isChecked()) {
            oars[2] = 2
        }
        if (binding.rbOars9.isChecked()) {
            oars[2] = 3
        }
        if (binding.rbOars10.isChecked()) {
            oars[2] = 0
        }
        //confianza casa
        if (binding.rbOars11.isChecked()) {
            oars[3] = 1
        }
        if (binding.rbOars12.isChecked()) {
            oars[3] = 2
        }
        if (binding.rbOars13.isChecked()) {
            oars[3] = 3
        }
        if (binding.rbOars14.isChecked()) {
            oars[3] = 0
        }
        //hablo amigos
        if (binding.rbOars15.isChecked()) {
            oars[4] = 3
        }
        if (binding.rbOars16.isChecked()) {
            oars[4] = 2
        }
        if (binding.rbOars17.isChecked()) {
            oars[4] = 1
        }
        if (binding.rbOars18.isChecked()) {
            oars[4] = 0
        }
        //visita
        if (binding.rbOars19.isChecked()) {
            oars[5] = 3
        }
        if (binding.rbOars20.isChecked()) {
            oars[5] = 2
        }
        if (binding.rbOars21.isChecked()) {
            oars[5] = 1
        }
        if (binding.rbOars22.isChecked()) {
            oars[5] = 0
        }
        //persona en confianza
        if (binding.rbOars23.isChecked()) {
            oars[6] = 2
        }
        if (binding.rbOars24.isChecked()) {
            oars[6] = 0
        }
        //sentirse solo
        if (binding.rbOars25.isChecked()) {
            oars[7] = 0
        }
        if (binding.rbOars26.isChecked()) {
            oars[7] = 1
        }
        if (binding.rbOars27.isChecked()) {
            oars[7] = 2
        }
        if (binding.rbOars28.isChecked()) {
            oars[7] = 3
        }
        //ve a familiares
        if (binding.rbOars29.isChecked()) {
            oars[8] = 1
        }
        if (binding.rbOars30.isChecked()) {
            oars[8] = 2
        }
        //ayuda de alguien
        if (binding.rbOars31.isChecked()) {
            oars[9] = 1
        }
        if (binding.rbOars32.isChecked()) {
            oars[9] = 0
        }
        //tiempo de cuidado
        if (binding.rbOars33.isChecked()) {
            oars[10] = 1
        }
        if (binding.rbOars34.isChecked()) {
            oars[10] = 2
        }
        if (binding.rbOars35.isChecked()) {
            oars[10] = 3
        }

        val r = ContextCompat.getColor(this, R.color.red)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)
        if (oars.sum() < 10) {
            binding.tvRes.setText("Incapacitado socialmente:\t${oars.sum()}")
            binding.tvRes.setBackgroundColor(r)
        } else if (oars.sum() in 10..20) {
            binding.tvRes.setText("Moderadamente incapacitado socialmente:\t${oars.sum()}")
            binding.tvRes.setBackgroundColor(y)
        } else if (oars.sum() > 20) {
            binding.tvRes.setText("Buenos recursos sociales:\t${oars.sum()}")
            binding.tvRes.setBackgroundColor(g)
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_escalas)).document(exp)
            .collection(getString(R.string.db_datos_interes)).document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_oars), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}