package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emisc0607.activityfisiodatostest.databinding.ActivityDinamarcaBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class dinamarcaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinamarcaBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDinamarcaBinding.inflate(layoutInflater)
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

        binding.rbDin1.setOnCheckedChangeListener { _, isChecked ->
            binding.rg2.clearCheck()
            binding.rg3.clearCheck()
            binding.rg4.clearCheck()
            binding.rg5.clearCheck()
        }
        binding.rbDin4.setOnCheckedChangeListener { _, isChecked ->
            binding.rg1.clearCheck()
            binding.rg3.clearCheck()
            binding.rg4.clearCheck()
            binding.rg5.clearCheck()
        }
        binding.rbDin7.setOnCheckedChangeListener { _, isChecked ->
            binding.rg2.clearCheck()
            binding.rg1.clearCheck()
            binding.rg4.clearCheck()
            binding.rg5.clearCheck()
        }
        binding.rbDin10.setOnCheckedChangeListener { _, isChecked ->
            binding.rg2.clearCheck()
            binding.rg3.clearCheck()
            binding.rg1.clearCheck()
            binding.rg5.clearCheck()
        }
        binding.rbDin13.setOnCheckedChangeListener { _, isChecked ->
            binding.rg2.clearCheck()
            binding.rg3.clearCheck()
            binding.rg4.clearCheck()
            binding.rg1.clearCheck()
        }
    }

    private fun switches() {
        val din = arrayOf<String>("1A", "2A", "3A", "4A", "5A", "1B", "2B", "3B", "4B", "5B")
        //equilibrio sentado
        if (binding.rbDin1.isChecked()) {
            if (binding.rbDin2.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(0)}")
            }
            if (binding.rbDin3.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(5)}")
            }

        } else if (binding.rbDin4.isChecked()) {
            if (binding.rbDin5.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(1)}")
            }
            if (binding.rbDin6.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(6)}")
            }

        } else if (binding.rbDin7.isChecked()) {
            if (binding.rbDin8.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(2)}")
            }
            if (binding.rbDin9.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(7)}")
            }
        } else if (binding.rbDin10.isChecked()) {
            if (binding.rbDin11.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(3)}")
            }
            if (binding.rbDin12.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(8)}")
            }
        } else if (binding.rbDin13.isChecked()) {
            if (binding.rbDin14.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(4)}")
            }
            if (binding.rbDin15.isChecked()) {
                binding.tvRes.setText("Etapa ${din.get(9)}")
            }
        }
    }

    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_dinamarca), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}