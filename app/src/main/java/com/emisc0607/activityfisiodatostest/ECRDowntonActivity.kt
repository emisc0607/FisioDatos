package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityEcrdowntonBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class ECRDowntonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEcrdowntonBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEcrdowntonBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showExp()

        binding.bRes.setOnClickListener { escala() }
        binding.bSave.setOnClickListener {
            if(binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(binding.root, "Presione RESULTADO antes de guardar", Snackbar.LENGTH_SHORT).show()
        }
        binding.sERC0.setOnCheckedChangeListener { _, isChecked ->
            binding.sERC1.isChecked = false
            binding.sERC2.isChecked = false
            binding.sERC3.isChecked = false
            binding.sERC4.isChecked = false
            binding.sERC5.isChecked = false
            binding.sERC6.isChecked = false
        }
/*        binding.sERC1.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC0.isChecked = false
        }
        binding.sERC2.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC0.isChecked = false
        }
        binding.sERC3.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC0.isChecked = false
        }
        binding.sERC4.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC0.isChecked = false
        }
        binding.sERC5.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC0.isChecked = false
        }
        binding.sERC6.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC0.isChecked = false
        }*/
        binding.sERC7.setOnCheckedChangeListener { _, isChecked ->
            binding.sERC8.isChecked = false
            binding.sERC9.isChecked = false
            binding.sERC10.isChecked = false
        }
/*        binding.sERC8.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC7.isChecked = false
        }
        binding.sERC9.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC7.isChecked = false
        }
        binding.sERC10.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC7.isChecked = false
        }*/
        binding.sERC11.setOnCheckedChangeListener { _, isChecked ->
            binding.sERC12.isChecked = false
            binding.sERC13.isChecked = false
            binding.sERC14.isChecked = false
        }
/*        binding.sERC12.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC11.isChecked = false
        }
        binding.sERC13.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC11.isChecked = false
        }
        binding.sERC14.setOnCheckedChangeListener {_, isChecked ->
            binding.sERC11.isChecked = false
        }*/
    }

    private fun escala() {
        var don = Array(5, { i -> 0 })
        //caidas previas
        if (binding.rbERC1.isChecked()) {
            don[0] = 0
        }
        if (binding.rbERC2.isChecked()) {
            don[0] = 1
        }
        //estado mental
        if (binding.rbERC3.isChecked()) {
            don[1] = 0
        }
        if (binding.rbERC4.isChecked()) {
            don[1] = 1
        }
        //medicamentos
        if (binding.sERC0.isChecked) {
            don[2] = 0
        }
        if (binding.sERC1.isChecked) {
            don[2] = 1
        }
        if (binding.sERC2.isChecked) {
            don[2] = 1
        }
        if (binding.sERC3.isChecked) {
            don[2] = 1
        }
        if (binding.sERC4.isChecked) {
            don[2] = 1
        }
        if (binding.sERC5.isChecked) {
            don[2] = 1
        }
        if (binding.sERC6.isChecked) {
            don[2] = 0
        }
        //deficiencias
        if (binding.sERC7.isChecked) {
            don[3] = 0
        }
        if (binding.sERC8.isChecked) {
            don[3] = 1
        }
        if (binding.sERC9.isChecked) {
            don[3] = 1
        }
        if (binding.sERC10.isChecked) {
            don[3] = 1
        }
        //marcha
        if (binding.sERC11.isChecked) {
            don[4] = 0
        }
        if (binding.sERC12.isChecked) {
            don[4] = 0
        }
        if (binding.sERC13.isChecked) {
            don[4] = 1
        }
        if (binding.sERC14.isChecked) {
            don[4] = 1
        }
        val r = ContextCompat.getColor(this, R.color.red)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)
        if (don.sum() < 2) {
            binding.tvRes.setText("Riesgo bajo:\t${don.sum()}")
            binding.tvRes.setBackgroundColor(g)
        } else if (don.sum() in 2..4) {
            binding.tvRes.setText("Riesgo medio:\t${don.sum()}")
            binding.tvRes.setBackgroundColor(y)
        } else if (don.sum() > 4) {
            binding.tvRes.setText("Riesgo alto:\t${don.sum()}")
            binding.tvRes.setBackgroundColor(r)
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_ecr_downton), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}