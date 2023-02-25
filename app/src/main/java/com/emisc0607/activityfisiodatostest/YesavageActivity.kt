package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.R.color
import com.emisc0607.activityfisiodatostest.databinding.ActivityMainBinding
import com.emisc0607.activityfisiodatostest.databinding.ActivityYesavageBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.io.OutputStreamWriter

class YesavageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYesavageBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYesavageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showExp()
        binding.bRes.setOnClickListener { switches() }
        binding.bSave.setOnClickListener {
            if(binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(binding.root, "Presione RESULTADO antes de guardar", Snackbar.LENGTH_SHORT).show()
        }
    }
    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_yesavage), binding.tvRes.text.toString()
            )
        finish()
    }
    fun showExp(){
        val bundle=intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text="$exp"
    }
    fun switches(){
        var yes = Array(15, {i -> 0 })
        if(binding.sSyes1.isChecked){
            yes[0] = 0
        }
        else{
            yes[0] = 1
        }
        if(binding.sSyes2.isChecked){
            yes[1] = 1
        }
        else{
            yes[1] = 0
        }
        if(binding.sSyes3.isChecked){
            yes[2] = 1
        }
        else{
            yes[2] = 0
        }
        if(binding.sSyes4.isChecked){
            yes[3] = 1
        }
        else{
            yes[3] = 0
        }
        if(binding.sSyes5.isChecked){
            yes[4] = 0
        }
        else{
            yes[4] = 1
        }
        if(binding.sSyes6.isChecked){
            yes[5] = 1
        }
        else{
            yes[5] = 0
        }
        if(binding.sSyes7.isChecked){
            yes[6] = 0
        }
        else{
            yes[6] = 1
        }
        if(binding.sSyes8.isChecked){
            yes[7] = 1
        }
        else{
            yes[7] = 0
        }
        if(binding.sSyes9.isChecked){
            yes[8] = 1
        }
        else{
            yes[8] = 0
        }
        if(binding.sSyes10.isChecked){
            yes[9] = 1
        }
        else{
            yes[9] = 0
        }
        if(binding.sSyes11.isChecked){
            yes[10] = 1
        }
        else{
            yes[10] = 0
        }
        if(binding.sSyes12.isChecked){
            yes[11] = 1
        }
        else{
            yes[11] = 0
        }
        if(binding.sSyes13.isChecked){
            yes[12] = 0
        }
        else{
            yes[12] = 1
        }
        if(binding.sSyes14.isChecked){
            yes[13] = 1
        }
        else{
            yes[13] = 0
        }
        if(binding.sSyes15.isChecked){
            yes[14] = 1
        }
        else{
            yes[14] = 0
        }
        val r = ContextCompat.getColor(this, R.color.red)
        val o = ContextCompat.getColor(this, R.color.orange)
        val y = ContextCompat.getColor(this, R.color.yellow)
        val g = ContextCompat.getColor(this, R.color.green)
        if (yes.sum()>=12){
            binding.tvRes.setText("Síntomas depresivos graves:\t${yes.sum()}")
            binding.tvRes.setBackgroundColor(r)
        }
        else if (yes.sum() in 9..11){
            binding.tvRes.setText("Síntomas depresivos moderados:\t${yes.sum()} ")
            binding.tvRes.setBackgroundColor(o)
        }
        else if (yes.sum() in 5..8){
            binding.tvRes.setText("Síntomas depresivos leves:\t${yes.sum()}")
            binding.tvRes.setBackgroundColor(y)
        }
        else if (yes.sum() < 5){
            binding.tvRes.setText("Sin síntomas depresivos:\t${yes.sum()}")
            binding.tvRes.setBackgroundColor(g)
        }

    }
}
