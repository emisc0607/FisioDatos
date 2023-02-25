package com.emisc0607.activityfisiodatostest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.*
import com.emisc0607.activityfisiodatostest.databinding.ActivityMenuScalesBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class MenuScalesActivity : AppCompatActivity() {

    private var resEscalas = "\n"

    //Se obtiene resultados de las escalas
    private val tinettiLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("TIN_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bTinetti.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val dontownLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("DON_DATA").orEmpty()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bECR.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val dinamarcaLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("DIN_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bDinamarca.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val nortonLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("NOR_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bNorton.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val bradenLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("BRA_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bBraden.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val barthelLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("BAR_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bBarthel.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val lawtonLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("LAW_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bLawtonNdBrody.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val katzLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("KAT_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bKatz.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val oarsLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("OAR_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bOars.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val yesavageLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("YES_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bYesavage.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val bbrLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("BAT_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bBatBr.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val sftLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("SFT_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bSFT.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val mocaLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("MOC_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bMoca.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val miniMentLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("MIM_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bMinimental.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val miniCogLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("MIC_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bMinicog.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val gdsLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("GDS_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bGDS.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val frailLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("FRA_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bFrail.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val sarcfLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("SAR_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bTilbur.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val miniNutLauncher =
        registerForActivityResult(StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("MIN_DATA").orEmpty()
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                resEscalas += "${binding.bMiniN.text}--->\t$data\n"
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }

    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityMenuScalesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuScalesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showExp()
        showDate()
        binding.bSave.setOnClickListener {
            save()
        }
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val map = hashMapOf(
            "Ultíma modificación (día)" to day,
            "Ultíma modificación (mes)" to month,
            "Ultíma modificación (año)" to year
        )
        db.collection(getString(R.string.db_expedientes)).document(binding.tvExp.text.toString())
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas)).update(
            map.toMap()
        )
        botones()
    }

    fun save() {
        val intent = Intent()
        val menuScalesResult = binding.tvExp.text.toString()
        intent.putExtra("MENS_DATA", menuScalesResult)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }

    private fun showDate() {
        val bundle = intent.extras
        val date = bundle?.get("DATE")
        binding.tvDate.text = "$date"
    }

    private fun botones() {
        binding.bTinetti.setOnClickListener {
            val intent = Intent(this, tinettiActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            tinettiLauncher.launch(intent)
        }
        binding.bECR.setOnClickListener {
            val intent = Intent(this, ECRDowntonActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            dontownLauncher.launch(intent)
        }
        binding.bDinamarca.setOnClickListener {
            val intent = Intent(this, dinamarcaActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            dinamarcaLauncher.launch(intent)
        }
        binding.bYesavage.setOnClickListener {
            val intent = Intent(this, YesavageActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            yesavageLauncher.launch(intent)
        }
        binding.bNorton.setOnClickListener {
            val intent = Intent(this, nortonActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            nortonLauncher.launch(intent)
        }
        binding.bBraden.setOnClickListener {
            val intent = Intent(this, bradenActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            bradenLauncher.launch(intent)
        }
        binding.bBarthel.setOnClickListener {
            val intent = Intent(this, barthelActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            barthelLauncher.launch(intent)
        }
        binding.bLawtonNdBrody.setOnClickListener {
            val intent = Intent(this, lawtonActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            lawtonLauncher.launch(intent)
        }
        binding.bKatz.setOnClickListener {
            val intent = Intent(this, katzActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            katzLauncher.launch(intent)
        }
        binding.bOars.setOnClickListener {
            val intent = Intent(this, oarsActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            oarsLauncher.launch(intent)
        }
        binding.bYesavage.setOnClickListener {
            val intent = Intent(this, YesavageActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            yesavageLauncher.launch(intent)
        }
        binding.bBatBr.setOnClickListener {
            val intent = Intent(this, BateryBrActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            bbrLauncher.launch(intent)
        }
        binding.bSFT.setOnClickListener {
            val intent = Intent(this, SftActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            sftLauncher.launch(intent)
        }
        binding.bMoca.setOnClickListener {
            val intent = Intent(this, MocaActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            mocaLauncher.launch(intent)
        }
        binding.bMinimental.setOnClickListener {
            val intent = Intent(this, MinimentalActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            miniMentLauncher.launch(intent)
        }
        binding.bMinicog.setOnClickListener {
            val intent = Intent(this, MinicogActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            miniCogLauncher.launch(intent)
        }
        binding.bGDS.setOnClickListener {
            val intent = Intent(this, GdsActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            gdsLauncher.launch(intent)
        }
        binding.bFrail.setOnClickListener {
            val intent = Intent(this, FrailActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            frailLauncher.launch(intent)
        }
        binding.bTilbur.setOnClickListener {
            val intent = Intent(this, SarcfActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            sarcfLauncher.launch(intent)
        }
        binding.bMiniN.setOnClickListener {
            val intent = Intent(this, MininutricionalActivity::class.java)
            intent.putExtra("EXP", binding.tvExp.text)
            miniNutLauncher.launch(intent)
        }
    }
}