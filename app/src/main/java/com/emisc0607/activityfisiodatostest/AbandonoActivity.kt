package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import com.emisc0607.activityfisiodatostest.databinding.ActivityAbandonoBinding
import com.google.firebase.firestore.FirebaseFirestore

class AbandonoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAbandonoBinding
    private var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbandonoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showExp()
        getDB(binding.tvExp.text.toString())
        //binding.tvDate.text = binding.rg1.checkedRadioButtonId.toString()
        binding.bSave.setOnClickListener {
            if (binding.etConclusion.text.isNotEmpty())
                save(binding.tvExp.text.toString())
            else
                Toast.makeText(this, "No deje ningún campo vacío", Toast.LENGTH_SHORT).show()
            binding.etConclusion.error = "Requerido"
        }
    }

    private fun getDB(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.strAbandono)).get().addOnSuccessListener {
                binding.etConclusion.setText(it.get(getString(R.string.indab_conclusion)) as String?)
            }
    }


    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.getString("EXP")
        binding.tvExp.text = exp
    }
    fun save(exp: String) {
        //getString(R.string.str_presente)
        val datos: MutableList<String> = mutableListOf()
        datos.add(0, binding.etConclusion.text.toString())
        if (binding.cbAbA1.isChecked) datos.add(1, getString(R.string.str_presente))
        if (binding.cbAbP1.isChecked) datos.add(1, getString(R.string.str_ausente))
        if (binding.cbAbnV1.isChecked) datos.add(1, getString(R.string.str_no_valorable))
        if (binding.cbAbA2.isChecked) datos.add(2, getString(R.string.str_presente))
        if (binding.cbAbP2.isChecked) datos.add(2, getString(R.string.str_ausente))
        if (binding.cbAbnV2.isChecked) datos.add(2, getString(R.string.str_no_valorable))
        if (binding.cbAbA3.isChecked) datos.add(3, getString(R.string.str_presente))
        if (binding.cbAbP3.isChecked) datos.add(3, getString(R.string.str_ausente))
        if (binding.cbAbnV3.isChecked) datos.add(3, getString(R.string.str_no_valorable))
        if (binding.cbAbA4.isChecked) datos.add(4, getString(R.string.str_presente))
        if (binding.cbAbP4.isChecked) datos.add(4, getString(R.string.str_ausente))
        if (binding.cbAbnV4.isChecked) datos.add(4, getString(R.string.str_no_valorable))
        if (binding.cbAbA5.isChecked) datos.add(5, getString(R.string.str_presente))
        if (binding.cbAbP5.isChecked) datos.add(5, binding.cbAbP5.text.toString())
        if (binding.cbAbnV5.isChecked) datos.add(5, binding.cbAbnV5.text.toString())
        if (binding.cbAbA6.isChecked) datos.add(6, getString(R.string.str_presente))
        if (binding.cbAbP6.isChecked) datos.add(6, binding.cbAbP6.text.toString())
        if (binding.cbAbnV6.isChecked) datos.add(6, binding.cbAbnV6.text.toString())
        if (binding.cbAbA7.isChecked) datos.add(7, getString(R.string.str_presente))
        if (binding.cbAbP7.isChecked) datos.add(7, binding.cbAbP7.text.toString())
        if (binding.cbAbnV7.isChecked) datos.add(7, binding.cbAbnV7.text.toString())
        if (binding.cbAbA8.isChecked) datos.add(8, getString(R.string.str_presente))
        if (binding.cbAbP8.isChecked) datos.add(8, binding.cbAbP8.text.toString())
        if (binding.cbAbnV8.isChecked) datos.add(8, binding.cbAbnV8.text.toString())
        if (binding.cbAbA9.isChecked) datos.add(9, getString(R.string.str_presente))
        if (binding.cbAbP9.isChecked) datos.add(9, binding.cbAbP9.text.toString())
        if (binding.cbAbnV9.isChecked) datos.add(9, binding.cbAbnV9.text.toString())
        if (binding.cbAbA10.isChecked) datos.add(10, getString(R.string.str_presente))
        if (binding.cbAbP10.isChecked) datos.add(10, binding.cbAbP10.text.toString())
        if (binding.cbAbnV10.isChecked) datos.add(10, binding.cbAbnV10.text.toString())
        if (binding.cbAbA11.isChecked) datos.add(11, getString(R.string.str_presente))
        if (binding.cbAbP11.isChecked) datos.add(11, binding.cbAbP11.text.toString())
        if (binding.cbAbnV11.isChecked) datos.add(11, binding.cbAbnV11.text.toString())
        if (binding.cbAbA12.isChecked) datos.add(12, getString(R.string.str_presente))
        if (binding.cbAbP12.isChecked) datos.add(12, binding.cbAbP12.text.toString())
        if (binding.cbAbnV12.isChecked) datos.add(12, binding.cbAbnV12.text.toString())
        if (binding.cbAbA13.isChecked) datos.add(13, binding.cbAbA13.text.toString())
        if (binding.cbAbP13.isChecked) datos.add(13, binding.cbAbP13.text.toString())
        if (binding.cbAbnV13.isChecked) datos.add(13, binding.cbAbnV13.text.toString())
        if (binding.cbAbA14.isChecked) datos.add(14, binding.cbAbA14.text.toString())
        if (binding.cbAbP14.isChecked) datos.add(14, binding.cbAbP14.text.toString())
        if (binding.cbAbnV14.isChecked) datos.add(14, binding.cbAbnV14.text.toString())
        if (binding.cbAbA15.isChecked) datos.add(15, binding.cbAbA15.text.toString())
        if (binding.cbAbP15.isChecked) datos.add(15, binding.cbAbP15.text.toString())
        if (binding.cbAbnV15.isChecked) datos.add(15, binding.cbAbnV15.text.toString())
        if (binding.cbAbA16.isChecked) datos.add(16, binding.cbAbA16.text.toString())
        if (binding.cbAbP16.isChecked) datos.add(16, binding.cbAbP16.text.toString())
        if (binding.cbAbnV16.isChecked) datos.add(16, binding.cbAbnV16.text.toString())
        if (binding.cbAbA17.isChecked) datos.add(17, binding.cbAbA17.text.toString())
        if (binding.cbAbP17.isChecked) datos.add(17, binding.cbAbP17.text.toString())
        if (binding.cbAbnV17.isChecked) datos.add(17, binding.cbAbnV17.text.toString())
        if (binding.cbAbA18.isChecked) datos.add(18, binding.cbAbA18.text.toString())
        if (binding.cbAbP18.isChecked) datos.add(18, binding.cbAbP18.text.toString())
        if (binding.cbAbnV18.isChecked) datos.add(18, binding.cbAbnV18.text.toString())
        if (binding.cbAbA19.isChecked) datos.add(19, binding.cbAbA19.text.toString())
        if (binding.cbAbP19.isChecked) datos.add(19, binding.cbAbP19.text.toString())
        if (binding.cbAbnV19.isChecked) datos.add(19, binding.cbAbnV19.text.toString())
        if (binding.cbAbA20.isChecked) datos.add(20, binding.cbAbA20.text.toString())
        if (binding.cbAbP20.isChecked) datos.add(20, binding.cbAbP20.text.toString())
        if (binding.cbAbnV20.isChecked) datos.add(20, binding.cbAbnV20.text.toString())
        if (binding.cbAbA21.isChecked) datos.add(21, binding.cbAbA21.text.toString())
        if (binding.cbAbP21.isChecked) datos.add(21, binding.cbAbP21.text.toString())
        if (binding.cbAbnV21.isChecked) datos.add(21, binding.cbAbnV21.text.toString())
        if (binding.cbAbA22.isChecked) datos.add(22, binding.cbAbA22.text.toString())
        if (binding.cbAbP22.isChecked) datos.add(22, binding.cbAbP22.text.toString())
        if (binding.cbAbnV22.isChecked) datos.add(22, binding.cbAbnV22.text.toString())
        if (binding.cbAbA23.isChecked) datos.add(23, binding.cbAbA23.text.toString())
        if (binding.cbAbP23.isChecked) datos.add(23, binding.cbAbP23.text.toString())
        if (binding.cbAbnV23.isChecked) datos.add(23, binding.cbAbnV23.text.toString())
        if (binding.cbAbA24.isChecked) datos.add(24, binding.cbAbA24.text.toString())
        if (binding.cbAbP24.isChecked) datos.add(24, binding.cbAbP24.text.toString())
        if (binding.cbAbnV24.isChecked) datos.add(24, binding.cbAbnV24.text.toString())
        if (binding.cbAbA25.isChecked) datos.add(25, binding.cbAbA25.text.toString())
        if (binding.cbAbP25.isChecked) datos.add(25, binding.cbAbP25.text.toString())
        if (binding.cbAbnV25.isChecked) datos.add(25, binding.cbAbnV25.text.toString())
        if (binding.cbAbA26.isChecked) datos.add(26, binding.cbAbA26.text.toString())
        if (binding.cbAbP26.isChecked) datos.add(26, binding.cbAbP26.text.toString())
        if (binding.cbAbnV26.isChecked) datos.add(26, binding.cbAbnV26.text.toString())
        if (binding.cbAbA27.isChecked) datos.add(27, binding.cbAbA27.text.toString())
        if (binding.cbAbP27.isChecked) datos.add(27, binding.cbAbP27.text.toString())
        if (binding.cbAbnV27.isChecked) datos.add(27, binding.cbAbnV27.text.toString())
        if (binding.cbAbA28.isChecked) datos.add(28, binding.cbAbA28.text.toString())
        if (binding.cbAbP28.isChecked) datos.add(28, binding.cbAbP28.text.toString())
        if (binding.cbAbnV28.isChecked) datos.add(28, binding.cbAbnV28.text.toString())
        if (binding.cbAbA29.isChecked) datos.add(29, binding.cbAbA29.text.toString())
        if (binding.cbAbP29.isChecked) datos.add(29, binding.cbAbP29.text.toString())
        if (binding.cbAbnV29.isChecked) datos.add(29, binding.cbAbnV29.text.toString())
        if (binding.cbAbA30.isChecked) datos.add(30, binding.cbAbA30.text.toString())
        if (binding.cbAbP30.isChecked) datos.add(30, binding.cbAbP30.text.toString())
        if (binding.cbAbnV30.isChecked) datos.add(30, binding.cbAbnV30.text.toString())
        if (binding.cbAbA31.isChecked) datos.add(31, binding.cbAbA31.text.toString())
        if (binding.cbAbP31.isChecked) datos.add(31, binding.cbAbP31.text.toString())
        if (binding.cbAbnV31.isChecked) datos.add(31, binding.cbAbnV31.text.toString())
        if (binding.cbAbA32.isChecked) datos.add(32, binding.cbAbA32.text.toString())
        if (binding.cbAbP32.isChecked) datos.add(32, binding.cbAbP32.text.toString())
        if (binding.cbAbnV32.isChecked) datos.add(32, binding.cbAbnV32.text.toString())
        if (binding.cbAbA33.isChecked) datos.add(33, binding.cbAbA33.text.toString())
        if (binding.cbAbP33.isChecked) datos.add(33, binding.cbAbP33.text.toString())
        if (binding.cbAbnV33.isChecked) datos.add(33, binding.cbAbnV33.text.toString())
        if (binding.cbAbA34.isChecked) datos.add(34, binding.cbAbA34.text.toString())
        if (binding.cbAbP34.isChecked) datos.add(34, binding.cbAbP34.text.toString())
        if (binding.cbAbnV34.isChecked) datos.add(34, binding.cbAbnV34.text.toString())
        if (binding.cbAbA35.isChecked) datos.add(35, binding.cbAbA35.text.toString())
        if (binding.cbAbP35.isChecked) datos.add(35, binding.cbAbP35.text.toString())
        if (binding.cbAbnV35.isChecked) datos.add(35, binding.cbAbnV35.text.toString())
        if (binding.cbAbA36.isChecked) datos.add(36, binding.cbAbA36.text.toString())
        if (binding.cbAbP36.isChecked) datos.add(36, binding.cbAbP36.text.toString())
        if (binding.cbAbnV36.isChecked) datos.add(36, binding.cbAbnV36.text.toString())
        if (datos.size < 37){
            Toast.makeText(this, "Seleccione al menos una opción para cada apartado", Toast.LENGTH_SHORT).show()
        } else{
            db.collection(getString(R.string.db_expedientes)).document(exp)
                .collection(getString(R.string.db_datos_interes))
                .document(getString(R.string.strAbandono)).set(
                    hashMapOf(
                        getString(R.string.indab_conclusion) to datos[0],
                        getString(R.string.strAb1) to datos[1],
                        getString(R.string.strAb2) to datos[2],
                        getString(R.string.strAb3) to datos[3],
                        getString(R.string.strAb4) to datos[4],
                        getString(R.string.strAb5) to datos[5],
                        getString(R.string.strAb6) to datos[6],
                        getString(R.string.strAb7) to datos[7],
                        getString(R.string.strAb8) to datos[8],
                        getString(R.string.strAb9) to datos[9],
                        getString(R.string.strAb10) to datos[10],
                        getString(R.string.strAb11) to datos[11],
                        getString(R.string.strAb12) to datos[12],
                        getString(R.string.strAb13) to datos[13],
                        getString(R.string.strAb14) to datos[14],
                        getString(R.string.strAb15) to datos[15],
                        getString(R.string.strAb16) to datos[16],
                        getString(R.string.strAb17) to datos[17],
                        getString(R.string.strAb18) to datos[18],
                        getString(R.string.strAb19) to datos[19],
                        getString(R.string.strAb20) to datos[20],
                        getString(R.string.strAb21) to datos[21],
                        getString(R.string.strAb22) to datos[22],
                        getString(R.string.strAb23) to datos[23],
                        getString(R.string.strAb24) to datos[24],
                        getString(R.string.strAb25) to datos[25],
                        getString(R.string.strAb26) to datos[26],
                        getString(R.string.strAb27) to datos[27],
                        getString(R.string.strAb28) to datos[28],
                        getString(R.string.strAb29) to datos[29],
                        getString(R.string.strAb30) to datos[30],
                        getString(R.string.strAb31) to datos[31],
                        getString(R.string.strAb32) to datos[32],
                        getString(R.string.strAb33) to datos[33],
                        getString(R.string.strAb34) to datos[34],
                        getString(R.string.strAb35) to datos[35],
                        getString(R.string.strAb36) to datos[36],
                    ).toMap()
                )
            db.collection(getString(R.string.db_expedientes)).document(exp)
                .collection(getString(R.string.db_datos_interes))
                .document(getString(R.string.strAbandono)).update("Tipo de centro", "Centro de larga estadía")
            val intent = Intent()
            intent.putExtra("IND_DATA", binding.tvExp.text.toString())
            setResult(RESULT_OK, intent)

            finish()
        }
    }
}
