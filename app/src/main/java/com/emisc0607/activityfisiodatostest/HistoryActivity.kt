package com.emisc0607.activityfisiodatostest

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.emisc0607.activityfisiodatostest.R.*
import com.emisc0607.activityfisiodatostest.R.string.*
import com.emisc0607.activityfisiodatostest.databinding.ActivityHistoryBinding

import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class HistoryActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    //DataBase
    private val db = FirebaseFirestore.getInstance()

    //Spinners
    private lateinit var spinner1: ArrayAdapter<String>
    private lateinit var spinner2: ArrayAdapter<String>

    //View Binding
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showExp()
        getDB()
        //import android.widget.EditText
        //checkBoxes()

        binding.bSave.setOnClickListener {
            noApply()
        }//save() }
        binding.etDoBirth.setOnClickListener { showDate() }
        binding.etFirstDate.setOnClickListener { showDateB() }
        //Spinner EdoCivil
        spinner1 = ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item)
        spinner1.addAll(
            listOf(
                "Soltero/a",
                "Casado/a",
                "Divorciado/a",
                "Viudo/a",
                "Concubinato",
                "Otro"
            )
        )
        binding.sEdoCivil.onItemSelectedListener = this
        binding.sEdoCivil.adapter = spinner1
        //Spinner Escolaridad
        spinner2 = ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item)
        spinner2.addAll(
            listOf(
                "Ninguna",
                "Primaria (1° - 2°)",
                "Primaria (3° - 4°)",
                "Primaria (4° - 6°)",
                "Secundaria",
                "Preparatoria",
                "Licenciatura",
                "Posgrado"
            )
        )
        binding.sEsc.onItemSelectedListener = this
        binding.sEsc.adapter = spinner2
    }

    private fun getDB() {
        val exp = binding.tvExp.text.toString()
        db.collection(getString(db_expedientes)).document(exp).get().addOnSuccessListener {
            binding.etFirstDate.setText(it.get(getString(exp_fecha_de_primer_valoracion)) as String?)
            binding.etName.setText(it.get(getString(exp_name)) as String?)
            binding.etAge.setText(it.get(getString(exp_age)) as String?)
            binding.tvSex.text = it.get(getString(exp_sex)) as String?
            binding.etPlaceObirth.setText(it.get(getString(exp_lugar_de_nacimiento)) as String?)
            binding.etDoBirth.setText(it.get(getString(exp_fecha_de_nacimiento)) as String?)
            binding.tvEsc.text = it.get(getString(exp_escolaridad)) as String?
            binding.tvCivil.text = it.get(getString(exp_estado_civil)) as String?
            binding.etNumHab.setText(it.get(getString(exp_numero_de_habitacion)) as String?)
            binding.etOcupation.setText(it.get(getString(exp_ocupacion)) as String?)
            binding.etUsualPlace.setText(it.get(getString(exp_lugar_habitual)) as String?)
            binding.etCaretaker.setText(it.get(getString(exp_cuidador_responsable)) as String?)
            if (binding.tvSex.text.isNotEmpty())
                if (binding.tvSex.text.toString() == "Masculino") binding.rbSex1.isChecked =
                    true else binding.rbSex2.isChecked = true
            //if(binding.tvEsc.text.isNotEmpty()) binding.sEsc.visibility = View.GONE
            //if(binding.tvCivil.text.isNotEmpty()) binding.sEdoCivil.visibility = View.GONE
        }
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_signos_vitales)).get().addOnSuccessListener {
                binding.etTemp.setText(it.get(getString(exp_temperatura)) as String?)
                binding.etHeight.setText(it.get(getString(exp_talla)) as String?)
                binding.etWeight.setText(it.get(getString(exp_peso)) as String?)
                binding.etTA.setText(it.get(getString(exp_ta)) as String?)
                binding.etFC.setText(it.get(getString(exp_fc)) as String?)
                binding.etFR.setText(it.get(getString(exp_fr)) as String?)
                binding.etIMC.setText(it.get(getString(exp_imc)) as String?)
                binding.eteApetito.setText(it.get(getString(exp_apetito)) as String?)
                binding.etSed.setText(it.get(getString(exp_sed)) as String?)
            }
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_antecendentes_hf)).get().addOnSuccessListener {
                binding.etFamO1.setText(it.get(getString(illness_tuberculosis)) as String?)
                binding.etFamO2.setText(it.get(getString(illness_hipertension)) as String?)
                binding.etFamO3.setText(it.get(getString(illness_diabetes)) as String?)
                binding.etFamO4.setText(it.get(getString(illness_infarto_al_miocardio)) as String?)
                binding.etFamO5.setText(it.get(getString(illness_demencia)) as String?)
                binding.etFamO6.setText(it.get(getString(illness_cancer)) as String?)
                binding.etFamO7.setText(it.get(getString(illness_otro)) as String?)
            }
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_antecedentes_p)).get().addOnSuccessListener {
                binding.etPatO1.setText(it.get(getString(illness_tuberculosis)) as String?)
                binding.etPatO2.setText(it.get(getString(illness_hipertension)) as String?)
                binding.etPatO3.setText(it.get(getString(illness_diabetes)) as String?)
                binding.etPatO4.setText(it.get(getString(illness_dislipidemias)) as String?)
                binding.etPatO5.setText(it.get(getString(illness_demencia)) as String?)
                binding.etPatO6.setText(it.get(getString(illness_cancer)) as String?)
                binding.etPatO7.setText(it.get(getString(illness_osteoartritis)) as String?)
                binding.etPatO8.setText(it.get(getString(illness_avc)) as String?)
                binding.etPatO9.setText(it.get(getString(illness_cardiovascular)) as String?)
                binding.etPatO10.setText(it.get(getString(illness_transfusiones)) as String?)
                binding.etPatO11.setText(it.get(getString(illness_dolor)) as String?)
                binding.etPatO12.setText(it.get(getString(illness_caidas)) as String?)
                binding.etPatO13.setText(it.get(getString(illness_hepatitis)) as String?)
                binding.etPatO14.setText(it.get(getString(illness_hospitalizaciones)) as String?)
                binding.etPatO15.setText(it.get(getString(illness_otro)) as String?)
            }
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(antecedentes_pNp)).get().addOnSuccessListener {
                binding.etNoPat1.setText(it.get(getString(ant_alimentacion)) as String?)
                binding.etNoPat2.setText(it.get(getString(ant_dentadura)) as String?)
                binding.etNoPat3.setText(it.get(getString(ant_audicion)) as String?)
                binding.etNoPat4.setText(it.get(getString(ant_vision)) as String?)
                binding.etNoPat5.setText(it.get(getString(ant_actividad_fisica)) as String?)
                binding.etNoPat6.setText(it.get(getString(ant_pasatiempos_y_vicios)) as String?)
                binding.etNoPat7.setText(it.get(getString(ant_conocimiento_del_entorno)) as String?)
                binding.etNoPat8.setText(it.get(getString(ant_medicamentos)) as String?)
            }
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_aparato_sistema)).get().addOnSuccessListener {
                binding.etExpSistem1.setText(it.get(getString(ant_cardiovascular)) as String?)
                binding.etExpSistem2.setText(it.get(getString(ant_respiratorio)) as String?)
                binding.etExpSistem3.setText(it.get(getString(ant_digestivo)) as String?)
                binding.etExpSistem4.setText(it.get(getString(ant_genitourinario)) as String?)
                binding.etExpSistem5.setText(it.get(getString(ant_hormonal_endocrino)) as String?)
                binding.etExpSistem6.setText(it.get(getString(ant_nervioso)) as String?)
                binding.etExpSistem7.setText(it.get(getString(ant_piel)) as String?)
            }
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_problemas_geriatricos)).get().addOnSuccessListener {
                binding.etSindromes1.setText(it.get(getString(obs_vertigo_mareo)) as String?)
                binding.etSindromes2.setText(it.get(getString(obs_delirio)) as String?)
                binding.etSindromes3.setText(it.get(getString(obs_deterioro_cognitivo)) as String?)
                binding.etSindromes4.setText(it.get(getString(obs_sincopes)) as String?)
                binding.etSindromes5.setText(it.get(getString(obs_fragilidad)) as String?)
                binding.etSindromes6.setText(it.get(getString(obs_dolor_cronico)) as String?)
                binding.etSindromes7.setText(it.get(getString(obs_debilidad_auditiva)) as String?)
                binding.etSindromes8.setText(it.get(getString(obs_debilidad_visual)) as String?)
                binding.etSindromes9.setText(it.get(getString(obs_insomnio)) as String?)
                binding.etSindromes10.setText(it.get(getString(obs_polifarmacia)) as String?)
                binding.etSindromes11.setText(it.get(getString(obs_incontinencia_urinaria)) as String?)
                binding.etSindromes12.setText(it.get(getString(obs_estrenimiento)) as String?)
                binding.etSindromes13.setText(it.get(getString(obs_ulceras_por_presion)) as String?)
                binding.etSindromes14.setText(it.get(getString(obs_inmovilidad)) as String?)
                binding.etSindromes15.setText(it.get(getString(obs_auxiliar_de_marcha)) as String?)
                binding.etSindromes16.setText(it.get(getString(illness_caidas)) as String?)
                binding.etSindromes17.setText(it.get(getString(obs_fracturas)) as String?)
                binding.etSindromes18.setText(it.get(getString(obs_desnutricion)) as String?)
            }
        binding.tvRes.text = binding.tvSex.text
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this, "Seleccione alguna opción", Toast.LENGTH_SHORT).show()
    }


    private fun showDate() {
        val datePicker = DatePickerFragment { day, month, year -> onDaySelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    private fun onDaySelected(day: Int, month: Int, year: Int) {
        binding.etDoBirth.setText("$day/${month + 1}/$year")
    }

    private fun showDateB() {
        val datePicker = DatePickerFragment { day, month, year -> onDaySelectedB(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    private fun onDaySelectedB(day: Int, month: Int, year: Int) {
        binding.etFirstDate.setText("$day/${month + 1}/$year")
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.getString("EXP")
        val email = bundle?.getString("email")
        binding.tvExp.text = exp
        binding.tvEmail.text = email
    }

    fun noApply() {
/*        if (binding.etFam1.text.isEmpty()) binding.etFam1.setText("NA")
        if (binding.etFam2.text.isEmpty()) binding.etFam2.setText("NA")
        if (binding.etFam3.text.isEmpty()) binding.etFam3.setText("NA")
        if (binding.etFam4.text.isEmpty()) binding.etFam4.setText("NA")
        if (binding.etFam5.text.isEmpty()) binding.etFam5.setText("NA")
        if (binding.etFam6.text.isEmpty()) binding.etFam6.setText("NA")
        if (binding.etFam7.text.isEmpty()) binding.etFam7.setText("NA")*/
        if (binding.etFamO1.text.isEmpty()) binding.etFamO1.setText(getString(exp_na_texto))
        if (binding.etFamO2.text.isEmpty()) binding.etFamO2.setText(getString(exp_na_texto))
        if (binding.etFamO3.text.isEmpty()) binding.etFamO3.setText(getString(exp_na_texto))
        if (binding.etFamO4.text.isEmpty()) binding.etFamO4.setText(getString(exp_na_texto))
        if (binding.etFamO5.text.isEmpty()) binding.etFamO5.setText(getString(exp_na_texto))
        if (binding.etFamO6.text.isEmpty()) binding.etFamO6.setText(getString(exp_na_texto))
        if (binding.etFamO7.text.isEmpty()) binding.etFamO7.setText(getString(exp_na_texto))
/*        if (binding.etPat1.text.isEmpty()) binding.etPat1.setText(getString(string.exp_na_texto))
        if (binding.etPat2.text.isEmpty()) binding.etPat2.setText(getString(string.exp_na_texto))
        if (binding.etPat3.text.isEmpty()) binding.etPat3.setText(getString(string.exp_na_texto))
        if (binding.etPat4.text.isEmpty()) binding.etPat4.setText(getString(string.exp_na_texto))
        if (binding.etPat5.text.isEmpty()) binding.etPat5.setText(getString(string.exp_na_texto))
        if (binding.etPat6.text.isEmpty()) binding.etPat6.setText(getString(string.exp_na_texto))
        if (binding.etPat7.text.isEmpty()) binding.etPat7.setText(getString(string.exp_na_texto))
        if (binding.etPat8.text.isEmpty()) binding.etPat8.setText(getString(string.exp_na_texto))
        if (binding.etPat9.text.isEmpty()) binding.etPat9.setText(getString(string.exp_na_texto))
        if (binding.etPat10.text.isEmpty()) binding.etPat10.setText(getString(string.exp_na_texto))
        if (binding.etPat11.text.isEmpty()) binding.etPat11.setText(getString(string.exp_na_texto))
        if (binding.etPat12.text.isEmpty()) binding.etPat12.setText(getString(string.exp_na_texto))
        if (binding.etPat13.text.isEmpty()) binding.etPat13.setText(getString(string.exp_na_texto))
        if (binding.etPat14.text.isEmpty()) binding.etPat14.setText(getString(string.exp_na_texto))
        if (binding.etPat15.text.isEmpty()) binding.etPat15.setText(getString(string.exp_na_texto))*/
        if (binding.etPatO1.text.isEmpty()) binding.etPatO1.setText(getString(exp_na_texto))
        if (binding.etPatO2.text.isEmpty()) binding.etPatO2.setText(getString(exp_na_texto))
        if (binding.etPatO3.text.isEmpty()) binding.etPatO3.setText(getString(exp_na_texto))
        if (binding.etPatO4.text.isEmpty()) binding.etPatO4.setText(getString(exp_na_texto))
        if (binding.etPatO5.text.isEmpty()) binding.etPatO5.setText(getString(exp_na_texto))
        if (binding.etPatO6.text.isEmpty()) binding.etPatO6.setText(getString(exp_na_texto))
        if (binding.etPatO7.text.isEmpty()) binding.etPatO7.setText(getString(exp_na_texto))
        if (binding.etPatO8.text.isEmpty()) binding.etPatO8.setText(getString(exp_na_texto))
        if (binding.etPatO9.text.isEmpty()) binding.etPatO9.setText(getString(exp_na_texto))
        if (binding.etPatO10.text.isEmpty()) binding.etPatO10.setText(getString(exp_na_texto))
        if (binding.etPatO11.text.isEmpty()) binding.etPatO11.setText(getString(exp_na_texto))
        if (binding.etPatO12.text.isEmpty()) binding.etPatO12.setText(getString(exp_na_texto))
        if (binding.etPatO13.text.isEmpty()) binding.etPatO13.setText(getString(exp_na_texto))
        if (binding.etPatO14.text.isEmpty()) binding.etPatO14.setText(getString(exp_na_texto))
        if (binding.etPatO15.text.isEmpty()) binding.etPatO15.setText(getString(exp_na_texto))
        if (binding.etNoPat1.text.isEmpty()) binding.etNoPat1.setText(getString(exp_na_texto))
        if (binding.etNoPat2.text.isEmpty()) binding.etNoPat2.setText(getString(exp_na_texto))
        if (binding.etNoPat3.text.isEmpty()) binding.etNoPat3.setText(getString(exp_na_texto))
        if (binding.etNoPat4.text.isEmpty()) binding.etNoPat4.setText(getString(exp_na_texto))
        if (binding.etNoPat5.text.isEmpty()) binding.etNoPat5.setText(getString(exp_na_texto))
        if (binding.etNoPat6.text.isEmpty()) binding.etNoPat6.setText(getString(exp_na_texto))
        if (binding.etNoPat7.text.isEmpty()) binding.etNoPat7.setText(getString(exp_na_texto))
        if (binding.etNoPat8.text.isEmpty()) binding.etNoPat8.setText(getString(exp_na_texto))
        if (binding.etExpSistem1.text.isEmpty()) binding.etExpSistem1.setText(getString(exp_na_texto))
        if (binding.etExpSistem2.text.isEmpty()) binding.etExpSistem2.setText(getString(exp_na_texto))
        if (binding.etExpSistem3.text.isEmpty()) binding.etExpSistem3.setText(getString(exp_na_texto))
        if (binding.etExpSistem4.text.isEmpty()) binding.etExpSistem4.setText(getString(exp_na_texto))
        if (binding.etExpSistem5.text.isEmpty()) binding.etExpSistem5.setText(getString(exp_na_texto))
        if (binding.etExpSistem6.text.isEmpty()) binding.etExpSistem6.setText(getString(exp_na_texto))
        if (binding.etExpSistem7.text.isEmpty()) binding.etExpSistem7.setText(getString(exp_na_texto))
        if (binding.etSindromes1.text.isEmpty()) binding.etSindromes1.setText(getString(exp_na_texto))
        if (binding.etSindromes2.text.isEmpty()) binding.etSindromes2.setText(getString(exp_na_texto))
        if (binding.etSindromes3.text.isEmpty()) binding.etSindromes3.setText(getString(exp_na_texto))
        if (binding.etSindromes4.text.isEmpty()) binding.etSindromes4.setText(getString(exp_na_texto))
        if (binding.etSindromes5.text.isEmpty()) binding.etSindromes5.setText(getString(exp_na_texto))
        if (binding.etSindromes6.text.isEmpty()) binding.etSindromes6.setText(getString(exp_na_texto))
        if (binding.etSindromes7.text.isEmpty()) binding.etSindromes7.setText(getString(exp_na_texto))
        if (binding.etSindromes8.text.isEmpty()) binding.etSindromes8.setText(getString(exp_na_texto))
        if (binding.etSindromes9.text.isEmpty()) binding.etSindromes9.setText(getString(exp_na_texto))
        if (binding.etSindromes10.text.isEmpty()) binding.etSindromes10.setText(
            getString(
                exp_na_texto
            )
        )
        if (binding.etSindromes11.text.isEmpty()) binding.etSindromes11.setText(
            getString(
                exp_na_texto
            )
        )
        if (binding.etSindromes12.text.isEmpty()) binding.etSindromes12.setText(
            getString(
                exp_na_texto
            )
        )
        if (binding.etSindromes13.text.isEmpty()) binding.etSindromes13.setText(
            getString(
                exp_na_texto
            )
        )
        if (binding.etSindromes14.text.isEmpty()) binding.etSindromes14.setText(
            getString(
                exp_na_texto
            )
        )
        if (binding.etSindromes15.text.isEmpty()) binding.etSindromes15.setText(
            getString(
                exp_na_texto
            )
        )
        if (binding.etSindromes16.text.isEmpty()) binding.etSindromes16.setText(
            getString(
                exp_na_texto
            )
        )
        if (binding.etSindromes17.text.isEmpty()) binding.etSindromes17.setText(
            getString(
                exp_na_texto
            )
        )
        if (binding.etSindromes18.text.isEmpty()) binding.etSindromes18.setText(
            getString(
                exp_na_texto
            )
        )
        if (
            binding.etFirstDate.text.isEmpty() or binding.etName.text.isEmpty() or binding.etAge.text.isEmpty() or
            binding.etPlaceObirth.text.isEmpty() or binding.etDoBirth.text.isEmpty() or binding.etNumHab.text.isEmpty() or
            binding.etOcupation.text.isEmpty() or binding.etUsualPlace.text.isEmpty() or binding.etCaretaker.text.isEmpty() or
            binding.etTemp.text.isEmpty() or binding.etHeight.text.isEmpty() or binding.etWeight.text.isEmpty() or
            binding.etTA.text.isEmpty() or binding.etFC.text.isEmpty() or binding.etFR.text.isEmpty() or
            binding.etIMC.text.isEmpty() or binding.eteApetito.text.isEmpty() or binding.etSed.text.isEmpty()
        ) {
            if (binding.etName.text.isEmpty()) binding.etName.error = "Requerido"
            if (binding.etFirstDate.text.isEmpty()) binding.etFirstDate.error = "Requerido"
            if (binding.etAge.text.isEmpty()) binding.etAge.error = "Requerido"
            if (binding.etPlaceObirth.text.isEmpty()) binding.etPlaceObirth.error = "Requerido"
            if (binding.etDoBirth.text.isEmpty()) binding.etDoBirth.error = "Requerido"
            if (binding.etOcupation.text.isEmpty()) binding.etOcupation.error = "Requerido"
            if (binding.etNumHab.text.isEmpty()) binding.etNumHab.error = "Requerido"
            if (binding.etUsualPlace.text.isEmpty()) binding.etUsualPlace.error = "Requerido"
            if (binding.etCaretaker.text.isEmpty()) binding.etCaretaker.error = "Requerido"
            if (binding.etTemp.text.isEmpty()) binding.etTemp.error = "Requerido"
            if (binding.etHeight.text.isEmpty()) binding.etHeight.error = "Requerido"
            if (binding.etWeight.text.isEmpty()) binding.etWeight.error = "Requerido"
            if (binding.etTA.text.isEmpty()) binding.etTA.error = "Requerido"
            if (binding.etFC.text.isEmpty()) binding.etFC.error = "Requerido"
            if (binding.etFR.text.isEmpty()) binding.etFR.error = "Requerido"
            if (binding.etIMC.text.isEmpty()) binding.etIMC.error = "Requerido"
            if (binding.eteApetito.text.isEmpty()) binding.eteApetito.error = "Requerido"
            if (binding.etSed.text.isEmpty()) binding.etSed.error = "Requerido"

            Toast.makeText(this, getString(warning_texto_vacio), Toast.LENGTH_LONG).show()
        } else sDatos()
    }

    private fun sDatos() {
        val exp = binding.tvExp.text.toString()
        val list: MutableList<String> = mutableListOf()
        var sex = ""
//        binding.tvExp.text = binding.sEsc.selectedItem.toString()
//        binding.tvCivil.text = binding.sEdoCivil.selectedItem.toString()
        list.add(0, binding.etFirstDate.text.toString())
        list.add(1, binding.etName.text.toString())
        list.add(2, binding.etAge.text.toString())
        if (binding.rbSex1.isChecked) sex = "Masculino"
        else if (binding.rbSex2.isChecked) sex = "Femenino"
        list.add(3, sex)
        list.add(4, binding.etPlaceObirth.text.toString())
        list.add(5, binding.etDoBirth.text.toString())
        list.add(6, binding.sEsc.selectedItem.toString())
        list.add(7, binding.sEdoCivil.selectedItem.toString())
        list.add(8, binding.etNumHab.text.toString())
        list.add(9, binding.etOcupation.text.toString())
        list.add(10, binding.etUsualPlace.text.toString())
        list.add(11, binding.etCaretaker.text.toString())
        //signos vitales
        list.add(12, binding.etTemp.text.toString())
        list.add(13, binding.etHeight.text.toString())
        list.add(14, binding.etWeight.text.toString())
        list.add(15, binding.etTA.text.toString())
        list.add(16, binding.etFC.text.toString())
        list.add(17, binding.etFR.text.toString())
        list.add(18, binding.etIMC.text.toString())
        list.add(19, binding.eteApetito.text.toString())
        list.add(20, binding.etSed.text.toString())
/*        //antecedentes heredofamiiares
        list.add(21, binding.etFam1.text.toString() + "\t" + binding.etFamO1.text.toString())
        list.add(22, binding.etFam2.text.toString() + "\t" + binding.etFamO2.text.toString())
        list.add(23, binding.etFam3.text.toString() + "\t" + binding.etFamO3.text.toString())
        list.add(24, binding.etFam4.text.toString() + "\t" + binding.etFamO4.text.toString())
        list.add(25, binding.etFam5.text.toString() + "\t" + binding.etFamO5.text.toString())
        list.add(26, binding.etFam6.text.toString() + "\t" + binding.etFamO6.text.toString())
        list.add(27, binding.etFam7.text.toString() + "\t" + binding.etFamO7.text.toString())
        //antecedentes personales patologicos
        list.add(28, binding.etPat1.text.toString() + "\t" + binding.etPatO1.text.toString())
        list.add(29, binding.etPat2.text.toString() + "\t" + binding.etPatO2.text.toString())
        list.add(30, binding.etPat3.text.toString() + "\t" + binding.etPatO3.text.toString())
        list.add(31, binding.etPat4.text.toString() + "\t" + binding.etPatO4.text.toString())
        list.add(32, binding.etPat5.text.toString() + "\t" + binding.etPatO5.text.toString())
        list.add(33, binding.etPat6.text.toString() + "\t" + binding.etPatO6.text.toString())
        list.add(34, binding.etPat7.text.toString() + "\t" + binding.etPatO7.text.toString())
        list.add(35, binding.etPat8.text.toString() + "\t" + binding.etPatO8.text.toString())
        list.add(36, binding.etPat9.text.toString() + "\t" + binding.etPatO9.text.toString())
        list.add(37, binding.etPat10.text.toString() + "\t" + binding.etPatO10.text.toString())
        list.add(38, binding.etPat11.text.toString() + "\t" + binding.etPatO11.text.toString())
        list.add(39, binding.etPat12.text.toString() + "\t" + binding.etPatO12.text.toString())
        list.add(40, binding.etPat13.text.toString() + "\t" + binding.etPatO13.text.toString())
        list.add(41, binding.etPat14.text.toString() + "\t" + binding.etPatO14.text.toString())
        list.add(42, binding.etPat15.text.toString() + "\t" + binding.etPatO15.text.toString())*/
        //antecedentes heredofamiiares
        list.add(21, binding.etFamO1.text.toString())
        list.add(22, binding.etFamO2.text.toString())
        list.add(23, binding.etFamO3.text.toString())
        list.add(24, binding.etFamO4.text.toString())
        list.add(25, binding.etFamO5.text.toString())
        list.add(26, binding.etFamO6.text.toString())
        list.add(27, binding.etFamO7.text.toString())
        //antecedentes personales patologicos
        list.add(28, binding.etPatO1.text.toString())
        list.add(29, binding.etPatO2.text.toString())
        list.add(30, binding.etPatO3.text.toString())
        list.add(31, binding.etPatO4.text.toString())
        list.add(32, binding.etPatO5.text.toString())
        list.add(33, binding.etPatO6.text.toString())
        list.add(34, binding.etPatO7.text.toString())
        list.add(35, binding.etPatO8.text.toString())
        list.add(36, binding.etPatO9.text.toString())
        list.add(37, binding.etPatO10.text.toString())
        list.add(38, binding.etPatO11.text.toString())
        list.add(39, binding.etPatO12.text.toString())
        list.add(40, binding.etPatO13.text.toString())
        list.add(41, binding.etPatO14.text.toString())
        list.add(42, binding.etPatO15.text.toString())
        //Antecedentes personales no patologicos
        list.add(43, binding.etNoPat1.text.toString())
        list.add(44, binding.etNoPat2.text.toString())
        list.add(45, binding.etNoPat3.text.toString())
        list.add(46, binding.etNoPat4.text.toString())
        list.add(47, binding.etNoPat5.text.toString())
        list.add(48, binding.etNoPat6.text.toString())
        list.add(49, binding.etNoPat7.text.toString())
        list.add(50, binding.etNoPat8.text.toString())
        //Aparato o Sistema
        list.add(51, binding.etExpSistem1.text.toString())
        list.add(52, binding.etExpSistem2.text.toString())
        list.add(53, binding.etExpSistem3.text.toString())
        list.add(54, binding.etExpSistem4.text.toString())
        list.add(55, binding.etExpSistem5.text.toString())
        list.add(56, binding.etExpSistem6.text.toString())
        list.add(57, binding.etExpSistem7.text.toString())
        //Sindromes y problemas geriatricos
        list.add(58, binding.etSindromes1.text.toString())
        list.add(59, binding.etSindromes2.text.toString())
        list.add(60, binding.etSindromes3.text.toString())
        list.add(61, binding.etSindromes4.text.toString())
        list.add(62, binding.etSindromes5.text.toString())
        list.add(63, binding.etSindromes6.text.toString())
        list.add(64, binding.etSindromes7.text.toString())
        list.add(65, binding.etSindromes8.text.toString())
        list.add(66, binding.etSindromes9.text.toString())
        list.add(67, binding.etSindromes10.text.toString())
        list.add(68, binding.etSindromes11.text.toString())
        list.add(69, binding.etSindromes12.text.toString())
        list.add(70, binding.etSindromes13.text.toString())
        list.add(71, binding.etSindromes14.text.toString())
        list.add(72, binding.etSindromes15.text.toString())
        list.add(73, binding.etSindromes16.text.toString())
        list.add(74, binding.etSindromes17.text.toString())
        list.add(75, binding.etSindromes18.text.toString())
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        db.collection(getString(db_expedientes)).document(exp).update(
            hashMapOf(
                getString(db_aplicante) to binding.tvEmail.text.toString(),
                getString(exp_fecha_de_primer_valoracion) to list[0],
                getString(exp_name) to list[1],
                getString(exp_age) to list[2],
                getString(exp_sex) to list[3],
                getString(exp_lugar_de_nacimiento) to list[4],
                getString(exp_fecha_de_nacimiento) to list[5],
                getString(exp_escolaridad) to list[6],
                getString(exp_estado_civil) to list[7],
                getString(exp_numero_de_habitacion) to list[8],
                getString(exp_ocupacion) to list[9],
                getString(exp_lugar_habitual) to list[10],
                getString(exp_cuidador_responsable) to list[11],
                "Ultíma modificación (día)" to day,
                "Ultíma modificación (mes)" to month,
                "Ultíma modificación (año)" to year
            ).toMap()
        )
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_signos_vitales)).set(
                hashMapOf(
                    getString(exp_temperatura) to list[12],
                    getString(exp_talla) to list[13],
                    getString(exp_peso) to list[14],
                    getString(exp_ta) to list[15],
                    getString(exp_fc) to list[16],
                    getString(exp_fr) to list[17],
                    getString(exp_imc) to list[18],
                    getString(exp_apetito) to list[19],
                    getString(exp_sed) to list[20]
                ).toMap()
            )
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_antecendentes_hf)).set(
                hashMapOf(
                    getString(illness_tuberculosis) to list[21],
                    getString(illness_hipertension) to list[22],
                    getString(illness_diabetes) to list[23],
                    getString(illness_infarto_al_miocardio) to list[24],
                    getString(illness_demencia) to list[25],
                    getString(illness_cancer) to list[26],
                    getString(illness_otro) to list[27]
                ).toMap()
            )
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_antecedentes_p)).set(
                hashMapOf(
                    getString(illness_tuberculosis) to list[28],
                    getString(illness_hipertension) to list[29],
                    getString(illness_diabetes) to list[30],
                    getString(illness_dislipidemias) to list[31],
                    getString(illness_demencia) to list[32],
                    getString(illness_cancer) to list[33],
                    getString(illness_osteoartritis) to list[34],
                    getString(illness_avc) to list[35],
                    getString(illness_cardiovascular) to list[36],
                    getString(illness_transfusiones) to list[37],
                    getString(illness_dolor) to list[38],
                    getString(illness_caidas) to list[39],
                    getString(illness_hepatitis) to list[40],
                    getString(illness_hospitalizaciones) to list[41],
                    getString(illness_otro) to list[42]
                ).toMap()
            )
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(antecedentes_pNp)).set(
                hashMapOf(
                    getString(ant_alimentacion) to list[43],
                    getString(ant_dentadura) to list[44],
                    getString(ant_audicion) to list[45],
                    getString(ant_vision) to list[46],
                    getString(ant_actividad_fisica) to list[47],
                    getString(ant_pasatiempos_y_vicios) to list[48],
                    getString(ant_conocimiento_del_entorno) to list[49],
                    getString(ant_medicamentos) to list[50]
                ).toMap()
            )
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_aparato_sistema)).set(
                hashMapOf(
                    getString(ant_cardiovascular) to list[51],
                    getString(ant_respiratorio) to list[52],
                    getString(ant_digestivo) to list[53],
                    getString(ant_genitourinario) to list[54],
                    getString(ant_hormonal_endocrino) to list[55],
                    getString(ant_nervioso) to list[56],
                    getString(ant_piel) to list[57]
                ).toMap()
            )
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_problemas_geriatricos)).set(
                hashMapOf(
                    getString(obs_vertigo_mareo) to list[58],
                    getString(obs_delirio) to list[59],
                    getString(obs_deterioro_cognitivo) to list[60],
                    getString(obs_sincopes) to list[61],
                    getString(obs_fragilidad) to list[62],
                    getString(obs_dolor_cronico) to list[63],
                    getString(obs_debilidad_auditiva) to list[64],
                    getString(obs_debilidad_visual) to list[65],
                    getString(obs_insomnio) to list[66],
                    getString(obs_polifarmacia) to list[67],
                    getString(obs_incontinencia_urinaria) to list[68],
                    getString(obs_estrenimiento) to list[69],
                    getString(obs_ulceras_por_presion) to list[70],
                    getString(obs_inmovilidad) to list[71],
                    getString(obs_auxiliar_de_marcha) to list[72],
                    getString(illness_caidas) to list[73],
                    getString(obs_fracturas) to list[74],
                    getString(obs_desnutricion) to list[75]
                ).toMap()
            )
        val intent = Intent()
        intent.putExtra("HIS_DATA", binding.tvExp.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}