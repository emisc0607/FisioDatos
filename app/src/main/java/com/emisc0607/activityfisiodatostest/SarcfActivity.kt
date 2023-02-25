package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivitySarcfBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class SarcfActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySarcfBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySarcfBinding.inflate(layoutInflater)
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
        val sarcf = Array(5){0}
        if (binding.rbSarcf1.isChecked) sarcf[0]=0
        if (binding.rbSarcf2.isChecked) sarcf[0]=1
        if (binding.rbSarcf3.isChecked) sarcf[0]=2

        if (binding.rbSarcf4.isChecked) sarcf[1]=0
        if (binding.rbSarcf5.isChecked) sarcf[1]=1
        if (binding.rbSarcf6.isChecked) sarcf[1]=2

        if (binding.rbSarcf7.isChecked) sarcf[2]=0
        if (binding.rbSarcf8.isChecked) sarcf[2]=1
        if (binding.rbSarcf9.isChecked) sarcf[2]=2

        if (binding.rbSarcf10.isChecked) sarcf[3]=0
        if (binding.rbSarcf11.isChecked) sarcf[3]=1
        if (binding.rbSarcf12.isChecked) sarcf[3]=2

        if (binding.rbSarcf13.isChecked) sarcf[4]=0
        if (binding.rbSarcf14.isChecked) sarcf[4]=1
        if (binding.rbSarcf15.isChecked) sarcf[4]=2

        val r = ContextCompat.getColor(this, R.color.red)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)

        if (sarcf.sum() >3){
            binding.tvRes.setBackgroundColor(y)
            binding.tvRes.text = "Alta probabilidad de sarcopenia:\t ${sarcf.sum()}"
        }
        else{
            binding.tvRes.setBackgroundColor(g)
            binding.tvRes.text = "Baja probabilidad de sarcopenia:\t ${sarcf.sum()}"
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes)).document(getString(R.string.db_escalas))
            .update(getString(R.string.escala_sarcf), binding.tvRes.text.toString())
        finish()
    }
    fun showExp(){
        val bundle=intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text="$exp"
    }
}