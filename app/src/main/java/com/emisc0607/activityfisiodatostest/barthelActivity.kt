package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityBarthelBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class barthelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBarthelBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBarthelBinding.inflate(layoutInflater)
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
        val bar = Array(10, { i -> 0 })
        //Comer
        if (binding.rbBar1.isChecked()) {
            bar[0] = 10
        }
        if (binding.rbBar2.isChecked()) {
            bar[0] = 5
        }
        if (binding.rbBar3.isChecked()) {
            bar[0] = 0
        }
        //Lavarse
        if (binding.rbBar4.isChecked()) {
            bar[1] = 5
        }
        if (binding.rbBar5.isChecked()) {
            bar[1] = 0
        }
        //Vestirse
        if (binding.rbBar6.isChecked()) {
            bar[2] = 10
        }
        if (binding.rbBar7.isChecked()) {
            bar[2] = 5
        }
        if (binding.rbBar8.isChecked()) {
            bar[2] = 0
        }
        //Arreglarse
        if (binding.rbBar9.isChecked()) {
            bar[3] = 5
        }
        if (binding.rbBar10.isChecked()) {
            bar[3] = 0
        }
        //deposicion
        if (binding.rbBar11.isChecked()) {
            bar[4] = 10
        }
        if (binding.rbBar12.isChecked()) {
            bar[4] = 5
        }
        if (binding.rbBar13.isChecked()) {
            bar[4] = 0
        }
        //miccion
        if (binding.rbBar14.isChecked()) {
            bar[5] = 10
        }
        if (binding.rbBar15.isChecked()) {
            bar[5] = 5
        }
        if (binding.rbBar16.isChecked()) {
            bar[5] = 0
        }
        //retrete
        if (binding.rbBar17.isChecked()) {
            bar[6] = 10
        }
        if (binding.rbBar18.isChecked()) {
            bar[6] = 5
        }
        if (binding.rbBar19.isChecked()) {
            bar[6] = 0
        }
        //trasladarse
        if (binding.rbBar20.isChecked()) {
            bar[7] = 15
        }
        if (binding.rbBar21.isChecked()) {
            bar[7] = 10
        }
        if (binding.rbBar22.isChecked()) {
            bar[7] = 5
        }
        if (binding.rbBar23.isChecked()) {
            bar[7] = 0
        }
        //deambulacion
        if (binding.rbBar24.isChecked()) {
            bar[8] = 15
        }
        if (binding.rbBar25.isChecked()) {
            bar[8] = 10
        }
        if (binding.rbBar26.isChecked()) {
            bar[8] = 5
        }
        if (binding.rbBar27.isChecked()) {
            bar[8] = 0
        }
        //subir escaleras
        if (binding.rbBar28.isChecked()) {
            bar[9] = 10
        }
        if (binding.rbBar29.isChecked()) {
            bar[9] = 5
        }
        if (binding.rbBar30.isChecked()) {
            bar[9] = 0
        }
        val r = ContextCompat.getColor(this, R.color.red)
        val o = ContextCompat.getColor(this, R.color.orange)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val b = ContextCompat.getColor(this, R.color.blue)
        val g = ContextCompat.getColor(this, R.color.green)
        if (bar.sum() < 20) {
            binding.tvRes.setText("Dependencia total:\t${bar.sum()}")
            binding.tvRes.setBackgroundColor(r)
        } else if (bar.sum() in 21..60) {
            binding.tvRes.setText("Dependencia severa:\t${bar.sum()}")
            binding.tvRes.setBackgroundColor(o)
        } else if (bar.sum() in 61..90) {
            binding.tvRes.setText("Dependencia moderada:\t${bar.sum()}")
            binding.tvRes.setBackgroundColor(y)
        } else if (bar.sum() in 91..99) {
            binding.tvRes.setText("Dependencia escasa:\t${bar.sum()}")
            binding.tvRes.setBackgroundColor(b)
        } else if (bar.sum() > 99) {
            binding.tvRes.setText("Independencia: \t${bar.sum()}")
            binding.tvRes.setBackgroundColor(g)
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_bathel), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}