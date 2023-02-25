package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityKatzBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class katzActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKatzBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKatzBinding.inflate(layoutInflater)
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
        val kat = Array(6) { 0 }
        var str = ""
        //Lavado
        if (binding.rbKat1.isChecked) {
            kat[0] = 1
        }
        if (binding.rbKat2.isChecked) {
            kat[0] = 1
        }
        if (binding.rbKat3.isChecked) {
            kat[0] = 0
            str += "Lavado\n"
        }
        //vestido
        if (binding.rbKat4.isChecked) {
            kat[1] = 1
        }
        if (binding.rbKat5.isChecked) {
            kat[1] = 1
        }
        if (binding.rbKat6.isChecked) {
            kat[1] = 0
            str += "Vestido\n"
        }
        //uso de retrete
        if (binding.rbKat7.isChecked) {
            kat[2] = 1
        }
        if (binding.rbKat8.isChecked) {
            kat[2] = 1
        }
        if (binding.rbKat9.isChecked) {
            kat[2] = 0
            str += "Uso del retrete\n"
        }
        //movilizacion
        if (binding.rbKat10.isChecked) {
            kat[3] = 1
        }
        if (binding.rbKat11.isChecked) {
            kat[3] = 1
        }
        if (binding.rbKat12.isChecked) {
            kat[3] = 0
            str += "Movilidad\n"
        }
        //continecia
        if (binding.rbKat13.isChecked) {
            kat[4] = 1
        }
        if (binding.rbKat14.isChecked) {
            kat[4] = 1
        }
        if (binding.rbKat15.isChecked) {
            kat[4] = 0
            str += "Continencia\n"
        }
        //alimentacion
        if (binding.rbKat16.isChecked) {
            kat[5] = 1
        }
        if (binding.rbKat17.isChecked) {
            kat[5] = 1
        }
        if (binding.rbKat18.isChecked) {
            kat[5] = 0
            str += "Alimentacion\n"
        }

        val r = ContextCompat.getColor(this, R.color.red)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)
        if (kat.sum() == 6) {
            binding.tvRes.text = "Independencia en todas las actividades\nPuntaje\t${kat.sum()}"
            binding.tvRes.setBackgroundColor(g)
        } else if (kat.sum() == 5) {
            binding.tvRes.text =
                "Independencia en todas las actividades menos:\n" + str + "Total: \t${kat.sum()}"
            binding.tvRes.setBackgroundColor(y)
        } else if (kat.sum() in 1..4) {
            binding.tvRes.text = "Dependencia en:\n" + str + "Total: \t${kat.sum()}"
            binding.tvRes.setBackgroundColor(y)
        } else if (kat.sum() == 0) {
            binding.tvRes.text = "Dependencia en todas las actividades\nTotal: \t${kat.sum()}"
            binding.tvRes.setBackgroundColor(r)
        }

    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_katz), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}