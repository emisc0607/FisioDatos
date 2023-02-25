package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emisc0607.activityfisiodatostest.databinding.ActivityEvalFisioBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class EvalFisioActivity : AppCompatActivity() {
    private var db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityEvalFisioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvalFisioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showExp()
        db.collection(getString(R.string.db_expedientes)).document(binding.tvExp.text.toString())
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.exp_evaluacion_fisio)).get().addOnSuccessListener {
                binding.etMotivC.setText(it.get(getString(R.string.evf_motivo_de_consulta)) as String?)
                binding.etExpfisica1.setText(it.get(getString(R.string.evf_inspeccion_y_palpacion)) as String?)
                binding.etExpfisica2.setText(it.get(getString(R.string.evf_movilidad_articular)) as String?)
                binding.etExpfisica3.setText(it.get(getString(R.string.evf_fuerza_muscular)) as String?)
                binding.etExpfisica4.setText(it.get(getString(R.string.evf_sensibilidad_y_reflejos)) as String?)
                binding.etExpfisica5.setText(it.get(getString(R.string.evf_pruebas_especificas)) as String?)
                binding.etExpfisica6.setText(it.get(getString(R.string.evf_analisis_de_postura)) as String?)
                binding.etExpfisica7.setText(it.get(getString(R.string.evf_analisis_de_marcha)) as String?)
                binding.etEvalFdia.setText(it.get(getString(R.string.evf_diagnostico_funcional)) as String?)
                binding.etIntObj.setText(it.get(getString(R.string.evf_intervencion_objetivos)) as String?)
                binding.etIntStrat.setText(it.get(getString(R.string.evf_intervencion_estrategias)) as String?)
                binding.etObservations.setText(it.get(getString(R.string.exp_observaciones)) as String?)
                binding.etEvalFTrat.setText(it.get(getString(R.string.evf_tratamiento)) as String?)
            }
        binding.bSave.setOnClickListener {
            save(binding.tvExp.text.toString())
        }
    }


    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }

    fun save(exp: String) {
        if (binding.etMotivC.text.isEmpty()) binding.etMotivC.setText(getString(R.string.exp_na_texto))
        if (binding.etExpfisica1.text.isEmpty()) binding.etExpfisica1.setText(getString(R.string.exp_na_texto))
        if (binding.etExpfisica2.text.isEmpty()) binding.etExpfisica2.setText(getString(R.string.exp_na_texto))
        if (binding.etExpfisica3.text.isEmpty()) binding.etExpfisica3.setText(getString(R.string.exp_na_texto))
        if (binding.etExpfisica4.text.isEmpty()) binding.etExpfisica4.setText(getString(R.string.exp_na_texto))
        if (binding.etExpfisica5.text.isEmpty()) binding.etExpfisica5.setText(getString(R.string.exp_na_texto))
        if (binding.etExpfisica6.text.isEmpty()) binding.etExpfisica6.setText(getString(R.string.exp_na_texto))
        if (binding.etExpfisica7.text.isEmpty()) binding.etExpfisica7.setText(getString(R.string.exp_na_texto))
        if (binding.etEvalFdia.text.isEmpty()) binding.etEvalFdia.setText(getString(R.string.exp_na_texto))
        if (binding.etIntObj.text.isEmpty()) binding.etIntObj.setText(getString(R.string.exp_na_texto))
        if (binding.etIntStrat.text.isEmpty()) binding.etIntStrat.setText(getString(R.string.exp_na_texto))
        if (binding.etObservations.text.isEmpty()) binding.etObservations.setText(getString(R.string.exp_na_texto))
        if (binding.etEvalFTrat.text.isEmpty()) binding.etEvalFTrat.setText(getString(R.string.exp_na_texto))
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.exp_evaluacion_fisio)).set(hashMapOf(
                getString(R.string.evf_motivo_de_consulta) to binding.etMotivC.text.toString(),
                getString(R.string.evf_inspeccion_y_palpacion) to binding.etExpfisica1.text.toString(),
                getString(R.string.evf_movilidad_articular) to binding.etExpfisica2.text.toString(),
                getString(R.string.evf_fuerza_muscular) to binding.etExpfisica3.text.toString(),
                getString(R.string.evf_sensibilidad_y_reflejos) to binding.etExpfisica4.text.toString(),
                getString(R.string.evf_pruebas_especificas) to binding.etExpfisica5.text.toString(),
                getString(R.string.evf_analisis_de_postura) to binding.etExpfisica6.text.toString(),
                getString(R.string.evf_analisis_de_marcha) to binding.etExpfisica7.text.toString(),
                getString(R.string.evf_diagnostico_funcional) to binding.etEvalFdia.text.toString(),
                getString(R.string.evf_intervencion_objetivos) to binding.etIntObj.text.toString(),
                getString(R.string.evf_intervencion_estrategias) to binding.etIntStrat.text.toString(),
                getString(R.string.exp_observaciones) to binding.etObservations.text.toString(),
                getString(R.string.evf_tratamiento) to binding.etEvalFTrat.text.toString(),
                "Ultíma modificación (día)" to day,
                "Ultíma modificación (mes)" to month,
                "Ultíma modificación (año)" to year
            ).toMap())
        val intent = Intent()
        intent.putExtra("EVA_DATA", binding.tvExp.text.toString())
        setResult(RESULT_OK, intent)

        finish()
    }
}