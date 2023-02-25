package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityFrailBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class FrailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFrailBinding
    private val db =FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFrailBinding.inflate(layoutInflater)
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
        val frail = Array(21) { 0 }
        val illness = Array(11) { 0 }
        if (binding.etFrail1.text.isEmpty() or binding.etFrail2.text.isEmpty()) {
            if (binding.etFrail1.text.isEmpty()) binding.etFrail1.error = "Requerido"
            if (binding.etFrail2.text.isEmpty()) binding.etFrail2.error = "Requerido"
            Toast.makeText(this, "No deje ningún campo vacío", Toast.LENGTH_LONG).show()
        }
        else {
            val p1 = binding.etFrail1.text.toString().toDouble()
            val p2 = binding.etFrail2.text.toString().toDouble()
            //fatiga
            if (binding.rbF1.isChecked) frail[0] = 1
            if (binding.rbF2.isChecked) frail[0] = 1
            if (binding.rbF3.isChecked) frail[0] = 0
            if (binding.rbF4.isChecked) frail[0] = 0
            if (binding.rbF5.isChecked) frail[0] = 0
            //resistencia
            if (binding.rbF6.isChecked) frail[1] = 1
            if (binding.rbF7.isChecked) frail[1] = 0
            //aerobic
            if (binding.rbF8.isChecked) frail[2] = 1
            if (binding.rbF9.isChecked) frail[2] = 0
            //illness
            if (binding.sF10.isChecked) illness[0] = 1
            if (binding.sF11.isChecked) illness[1] = 1
            if (binding.sF12.isChecked) illness[2] = 1
            if (binding.sF13.isChecked) illness[3] = 1
            if (binding.sF14.isChecked) illness[4] = 1
            if (binding.sF15.isChecked) illness[5] = 1
            if (binding.sF16.isChecked) illness[6] = 1
            if (binding.sF17.isChecked) illness[7] = 1
            if (binding.sF18.isChecked) illness[8] = 1
            if (binding.sF19.isChecked) illness[9] = 1
            if (binding.sF20.isChecked) illness[10] = 1
            if (illness.sum() > 4) frail[3] = 1
            else frail[3] = 0
            //lost
            val p = ((p2 - p1) / p2) * 100
            if (p >= 5) frail[4] = 1
            else frail[4] = 0
            val r = ContextCompat.getColor(this, R.color.red)
            val y = ContextCompat.getColor(this, R.color.yellow)
            val g = ContextCompat.getColor(this, R.color.green)
            if (frail.sum() == 0) {
                binding.tvRes.setBackgroundColor(g)
                binding.tvRes.text = "Sin fragilidad:\t${frail.sum()}"
            }
            if (frail.sum() in 1..2) {
                binding.tvRes.setBackgroundColor(y)
                binding.tvRes.text = "Probable pre-fragilidad:\t${frail.sum()}"
            }
            if (frail.sum() > 2) {
                binding.tvRes.setBackgroundColor(r)
                binding.tvRes.text = "Probable fragilidad:\t${frail.sum()}"
            }
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_frail), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}