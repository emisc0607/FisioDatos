package com.emisc0607.activityfisiodatostest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityTinettiBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class tinettiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTinettiBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTinettiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showExp()
        binding.bRes.setOnClickListener { escala() }
        binding.bSave.setOnClickListener {
            if(binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(binding.root, "Presione RESULTADO antes de guardar", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun escala() {
        val tin = Array(20) { 0 }
        //equilibrio sentado
        if (binding.rbTin1.isChecked) {
            tin[0] = 0
        }
        if (binding.rbTin2.isChecked) {
            tin[0] = 1
        }
        //levantarse
        if (binding.rbTin3.isChecked) {
            tin[1] = 0
        }
        if (binding.rbTin4.isChecked) {
            tin[1] = 1
        }
        if (binding.rbTin5.isChecked) {
            tin[1] = 2
        }
        //intento levantarse
        if (binding.rbTin6.isChecked) {
            tin[2] = 0
        }
        if (binding.rbTin7.isChecked) {
            tin[2] = 1
        }
        if (binding.rbTin8.isChecked) {
            tin[2] = 2
        }
        //equilibrio inmediato levantarse
        if (binding.rbTin9.isChecked) {
            tin[3] = 0
        }
        if (binding.rbTin10.isChecked) {
            tin[3] = 1
        }
        if (binding.rbTin11.isChecked) {
            tin[3] = 2
        }
        //equlibrio en bipedestacion
        if (binding.rbTin12.isChecked) {
            tin[4] = 0
        }
        if (binding.rbTin13.isChecked) {
            tin[4] = 1
        }
        if (binding.rbTin14.isChecked) {
            tin[4] = 2
        }
        //empujon
        if (binding.rbTin15.isChecked) {
            tin[5] = 0
        }
        if (binding.rbTin16.isChecked) {
            tin[5] = 1
        }
        if (binding.rbTin17.isChecked) {
            tin[5] = 2
        }
        //ojos cerrados
        if (binding.rbTin18.isChecked) {
            tin[6] = 0
        }
        if (binding.rbTin19.isChecked) {
            tin[6] = 1
        }
        //giro 360 1
        if (binding.rbTin20.isChecked) {
            tin[7] = 0
        }
        if (binding.rbTin21.isChecked) {
            tin[7] = 1
        }
        //giro 360 2
        if (binding.rbTin22.isChecked) {
            tin[8] = 0
        }
        if (binding.rbTin23.isChecked) {
            tin[8] = 1
        }
        //sentarse
        if (binding.rbTin24.isChecked) {
            tin[9] = 0
        }
        if (binding.rbTin25.isChecked) {
            tin[9] = 1
        }
        if (binding.rbTin26.isChecked) {
            tin[9] = 2
        }
        //comienza la marcha
        if (binding.rbTin27.isChecked) {
            tin[10] = 0
        }
        if (binding.rbTin28.isChecked) {
            tin[10] = 1
        }
        //longitud de paso 1
        if (binding.rbTin29.isChecked) {
            tin[11] = 0
        }
        if (binding.rbTin30.isChecked) {
            tin[11] = 1
        }
        //longitud de paso 2
        if (binding.rbTin31.isChecked) {
            tin[12] = 0
        }
        if (binding.rbTin32.isChecked) {
            tin[12] = 1
        }
        //longitud de paso 3
        if (binding.rbTin33.isChecked) {
            tin[13] = 0
        }
        if (binding.rbTin34.isChecked) {
            tin[13] = 1
        }
        //longitud de paso 4
        if (binding.rbTin35.isChecked) {
            tin[14] = 0
        }
        if (binding.rbTin36.isChecked) {
            tin[14] = 1
        }
        //simetria del paso
        if (binding.rbTin37.isChecked) {
            tin[15] = 0
        }
        if (binding.rbTin38.isChecked()) {
            tin[15] = 1
        }
        //continuidad del paso
        if (binding.rbTin39.isChecked()) {
            tin[16] = 0
        }
        if (binding.rbTin40.isChecked()) {
            tin[16] = 1
        }
        //trayectoria
        if (binding.rbTin41.isChecked()) {
            tin[17] = 0
        }
        if (binding.rbTin42.isChecked()) {
            tin[17] = 1
        }
        if (binding.rbTin43.isChecked()) {
            tin[17] = 2
        }
        //tronco
        if (binding.rbTin44.isChecked()) {
            tin[18] = 0
        }
        if (binding.rbTin45.isChecked()) {
            tin[18] = 1
        }
        if (binding.rbTin46.isChecked()) {
            tin[18] = 2
        }
        //postura en la marcha
        if (binding.rbTin47.isChecked()) {
            tin[19] = 0
        }
        if (binding.rbTin48.isChecked()) {
            tin[19] = 1
        }
        val r = ContextCompat.getColor(this, R.color.red)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)
        if (tin.sum() < 19) {
            binding.tvRes.text = "Alto riesgo de caídas:\t${tin.sum()}"
            //binding.tvRes.text = "Alto riesgo de caídas:\t${tin.sum()}"
            binding.tvRes.setBackgroundColor(r)
        } else if (tin.sum() in 19..23) {
            binding.tvRes.text = "Riesgo de caídas:\t${tin.sum()}"
            binding.tvRes.setBackgroundColor(y)
        } else if (tin.sum() > 23) {
            binding.tvRes.text = "Bajo riesgo de caídas:\t${tin.sum()}"
            binding.tvRes.setBackgroundColor(g)
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes)).document(getString(R.string.db_escalas))
            .update(getString(R.string.escala_tinetti), binding.tvRes.text.toString())
//        val intent = Intent()
//        val tinettiResult = binding.tvRes.text
//        intent.putExtra("TIN_DATA", tinettiResult)
//        setResult(RESULT_OK, intent)
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}