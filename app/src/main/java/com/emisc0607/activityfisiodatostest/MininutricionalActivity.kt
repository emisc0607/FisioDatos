package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityMininutricionalBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class MininutricionalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMininutricionalBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMininutricionalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showExp()
        binding.bRes.setOnClickListener { switches() }
        binding.bSave.setOnClickListener {
            if (binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(
                binding.root,
                "Presione RESULTADO antes de guardar",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        binding.rbMiN25.setOnClickListener {
            binding.etConsume.isEnabled = false
            binding.etConsume.setText("N/A")
        }
    }

    private fun switches() {

        val min1 = Array(6) { 0.0 }
        val min2 = Array(13) { 0.0 }

        if (binding.etCB.text.isNotEmpty() or binding.etCP.text.isNotEmpty() or binding.etConsume.text.isNotEmpty()) {
            var crib = ""
            var eval: String
            //apetito
            if (binding.rbMiN1.isChecked) {
                min1[0] = 0.0
            }
            if (binding.rbMiN2.isChecked) {
                min1[0] = 1.0
            }
            if (binding.rbMiN3.isChecked) {
                min1[0] = 2.0
            }
            //perdida de peso
            if (binding.rbMiN4.isChecked) {
                min1[1] = 0.0
            }
            if (binding.rbMiN5.isChecked) {
                min1[1] = 1.0
            }
            if (binding.rbMiN6.isChecked) {
                min1[1] = 2.0
            }
            if (binding.rbMiN7.isChecked) {
                min1[1] = 3.0
            }
            //movilidad

            if (binding.rbMiN8.isChecked) {
                min1[2] = 0.0
            }
            if (binding.rbMiN9.isChecked) {
                min1[2] = 1.0
            }
            if (binding.rbMiN10.isChecked) {
                min1[2] = 2.0
            }
            //enfermedad
            if (binding.rbMiN11.isChecked) {
                min1[3] = 0.0
            }
            if (binding.rbMiN12.isChecked) {
                min1[3] = 1.0
            }
            //Problemas neuropsicologicos
            if (binding.rbMiN13.isChecked) {
                min1[4] = 0.0
            }
            if (binding.rbMiN14.isChecked) {
                min1[4] = 1.0
            }
            if (binding.rbMiN15.isChecked) {
                min1[4] = 2.0
            }
            //imc
            if (binding.etIMC.text.isNotEmpty()) {
                if (binding.etIMC.text.toString().toDouble() < 19.0) {
                    min1[5] = 0.0
                } else if (binding.etIMC.text.toString().toDouble() in 19.0..21.0) {
                    min1[5] = 1.0
                } else if (binding.etIMC.text.toString().toDouble() in 21.0..23.0) {
                    min1[5] = 2.0
                } else if (binding.etIMC.text.toString().toDouble() > 23.0) {
                    min1[5] = 3.0
                } else Toast.makeText(this, "Inserte un IMC", Toast.LENGTH_SHORT).show()
            }
            //independecia
            if (binding.etCB.text.isNotEmpty()) {
                if (binding.rbMiN16.isChecked) {
                    min2[0] = 1.0
                }
                if (binding.rbMiN17.isChecked) {
                    min2[0] = 0.0
                }
            } else Toast.makeText(this, "Inserte un CB", Toast.LENGTH_SHORT).show()
            //medicamentos
            if (binding.etCB.text.isNotEmpty()) {
                if (binding.rbMiN18.isChecked) {
                    min2[1] = 0.0
                }
                if (binding.rbMiN19.isChecked) {
                    min2[1] = 1.0
                }
            } else Toast.makeText(this, "Inserte un CP", Toast.LENGTH_SHORT).show()
            //ulceras
            if (binding.rbMiN20.isChecked) {
                min2[2] = 0.0
            }
            if (binding.rbMiN21.isChecked) {
                min2[2] = 1.0
            }
            //comidas completas
            if (binding.rbMiN22.isChecked) {
                min2[3] = 0.0
            }
            if (binding.rbMiN23.isChecked) {
                min2[3] = 1.0
            }
            if (binding.rbMiN24.isChecked) {
                min2[3] = 2.0
            }
            //conusme
            var consume = ""
            binding
            if (binding.rbMiN25.isChecked) {
                min2[4] = 0.0
            }
            if (binding.rbMiN26.isChecked) {
                min2[4] = 0.5
            }
            if (binding.rbMiN27.isChecked) {
                min2[4] = 1.0
            }
            //frutas y verduras
            if (binding.rbMiN28.isChecked) {
                min2[5] = 0.0
            }
            if (binding.rbMiN29.isChecked) {
                min2[5] = 1.0
            }
            //vasos
            if (binding.rbMiN30.isChecked) {
                min2[6] = 0.0
            }
            if (binding.rbMiN31.isChecked) {
                min2[6] = 0.5
            }
            if (binding.rbMiN32.isChecked) {
                min2[6] = 1.0
            }
            //alimentarse
            if (binding.rbMiN33.isChecked) {
                min2[7] = 0.0
            }
            if (binding.rbMiN34.isChecked) {
                min2[7] = 1.0
            }
            if (binding.rbMiN35.isChecked) {
                min2[7] = 2.0
            }
            //nutrido
            if (binding.rbMiN36.isChecked) {
                min2[8] = 0.0
            }
            if (binding.rbMiN37.isChecked) {
                min2[8] = 1.0
            }
            if (binding.rbMiN38.isChecked) {
                min2[8] = 2.0
            }
            //estado de salud
            if (binding.rbMiN39.isChecked) {
                min2[9] = 0.0
            }
            if (binding.rbMiN40.isChecked) {
                min2[9] = 1.0
            }
            if (binding.rbMiN41.isChecked) {
                min2[9] = 2.0
            }
            if (binding.rbMiN42.isChecked) {
                min2[10] = 2.0
            }
            //cb
            if (binding.etCB.text.toString().toDouble() < 21.0) {
                min2[11] = 0.0
            } else if (binding.etCB.text.toString().toDouble() in 21.0..22.0) {
                min2[11] = 0.5
            } else if (binding.etCB.text.toString().toDouble() > 22.0) {
                min2[11] = 1.0
            }
            //cp
            if (binding.etCP.text.toString().toDouble() < 31.5) {
                min2[12] = 0.0
            } else if (binding.etCP.text.toString().toDouble() > 31.5) {
                min2[12] = 1.0
            }
            val r = ContextCompat.getColor(this, R.color.red)
            val y = ContextCompat.getColor(this, R.color.yellow)
            val g = ContextCompat.getColor(this, R.color.green)
            consume = binding.etConsume.text.toString()
            if (min1.sum() < 8.0) {
                crib = "Cribaje: Malnutrición\n"
            }
            if (min1.sum() in 8.0..11.0) {
                crib = "Cribaje: Riesgo de malnutrición\n"
            }
            if (min1.sum() > 11.0) {
                crib = "Cribaje: Estado nutricional normal\n"
            }
            if (min1.sum() + min2.sum() < 17.0) {
                eval = crib + "Evaluación: Mal estado nutricional" + "\nConsume:\t" + consume +
                        binding.tvRes.setBackgroundColor(r)
                binding.tvRes.text = eval
            }
            if (min1.sum() + min2.sum() in 17.0..23.5) {
                eval = crib + "Evaluación: Riesgo de malnutrición " + "\n Consume:\t" + consume
                binding.tvRes.setBackgroundColor(y)
                binding.tvRes.text = eval
            }
            if (min1.sum() + min2.sum() > 24.0) {
                eval =
                    crib + "Evaluación: Estado nutricional satisfactorio" + "\nConsume:\t" + consume
                binding.tvRes.setBackgroundColor(g)
                binding.tvRes.text = eval
            }
        } else {
            if (binding.etCB.text.isEmpty()) binding.etCB.error = "Requerido"
            if (binding.etCP.text.isEmpty()) binding.etCP.error = "Requerido"
            if (binding.etConsume.text.isEmpty()) binding.etConsume.error = "Requerido"
            Toast.makeText(this, "No deje ningún campo vacío", Toast.LENGTH_LONG).show()
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_mininutricional), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}