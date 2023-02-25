package com.emisc0607.activityfisiodatostest


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.emisc0607.activityfisiodatostest.R.*
import com.emisc0607.activityfisiodatostest.R.string.*
import com.emisc0607.activityfisiodatostest.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.coroutines.*
import java.io.*
import java.util.*
import kotlin.concurrent.schedule


private lateinit var data: ArrayAdapter<String>

enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var currentId:String=""
    private val menuScalesLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("MENS_DATA").orEmpty()
                //Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                currentId=data
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val fichaIdLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("FID_DATA").orEmpty()
                //Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                currentId=data
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val historyLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("HIS_DATA").orEmpty()
                //Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                currentId=data
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val evalFLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("EVA_DATA").orEmpty()
                //Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                currentId=data
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }
    private val indicALauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ActivityResult ->
            if (ActivityResult.resultCode == RESULT_OK) {
                val data = ActivityResult.data?.getStringExtra("IND_DATA").orEmpty()
                ////Toast.makeText(this, data, Toast.LENGTH_LONG).show()
                //Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
                currentId=data
            } else {
                //Toast.makeText(this, "Sin datos guardados", Toast.LENGTH_SHORT).show()
            }
        }

    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    //DataBase
    private val db = FirebaseFirestore.getInstance()
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val exp = binding.etExp.text.toString()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        data = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        data.addAll(listOf("Centro de día", "Centro de larga estadía"))
        binding.sTypeInt.onItemSelectedListener = this
        binding.sTypeInt.adapter = data
        //binding.tvProvider.visibility = View.GONE
        storageReference = FirebaseStorage.getInstance().reference
        db.collection(getString(db_expedientes)).get().addOnSuccessListener {
            val indice = it.documents.lastIndex
            binding.etExp.setText(indice.toString())
        }
        binding.bSaveNew.setOnClickListener {
            db.collection(getString(db_expedientes)).get().addOnSuccessListener {
                val indice = it.documents.lastIndex + 1
                db.collection(getString(db_expedientes)).document(indice.toString()).set(
                    hashMapOf(
                        "Fecha de creación (día)" to day,
                        "Fecha de creación (mes)" to month,
                        "Fecha de creación (año)" to year
                    )
                )
                db.collection(getString(db_expedientes)).document(indice.toString())
                    .collection(getString(db_datos_interes)).document(
                        getString(
                            strAbandono
                        )
                    ).set(
                        hashMapOf(
                            "Tipo de centro" to "Centro de día"
                        )
                    )
                db.collection(getString(com.emisc0607.activityfisiodatostest.R.string.db_expedientes))
                    .document(indice.toString())
                    .collection(getString(com.emisc0607.activityfisiodatostest.R.string.db_datos_interes))
                    .document(
                        getString(
                            com.emisc0607.activityfisiodatostest.R.string.db_escalas
                        )
                    )
                    .set(
                        hashMapOf(
                            getString(com.emisc0607.activityfisiodatostest.R.string.escala_tinetti) to ""
                        )
                    )
                db.collection(getString(com.emisc0607.activityfisiodatostest.R.string.db_expedientes))
                    .document(indice.toString())
                    .collection(getString(com.emisc0607.activityfisiodatostest.R.string.db_datos_interes))
                    .document(getString(com.emisc0607.activityfisiodatostest.R.string.exp_evaluacion_fisio))
                    .set(
                        hashMapOf(
                            getString(com.emisc0607.activityfisiodatostest.R.string.evf_motivo_de_consulta) to ""
                        )
                    )
                binding.etExp.setText(indice.toString())
            }

        }
        binding.bSearchExp.setOnClickListener {
            if (binding.etExp.text.isNotEmpty()) {
                db.collection(getString(db_expedientes)).get().addOnSuccessListener {
                    val exist = binding.etExp.text.toString().toInt()
                    if (exist == it.documents.lastIndex) {
                        showAlert("Este expediente no existe\nSi desea crearlo presione: CREAR")
                    } else {
                        showAlert("El expediente $exist ya existe, para descargarlo presione el botón de Compartir")
                    }
                }
            } else {
                showErrorName()
            }
        }

        botones()
        setDate()
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        val username = bundle?.getString("username")
        setup(email ?: "", username ?: "", provider ?: "")
        //Save user
        val prefs =
            getSharedPreferences(getString(prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.putString("username", username)
        prefs.apply()

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.visibility = View.GONE
        binding.SaveDB.visibility = View.GONE
        binding.DeleteDB.visibility = View.GONE
        binding.GetDB.visibility = View.GONE
        binding.etAddress.visibility = View.GONE
        binding.etPhone.visibility = View.GONE
        crashButton.setOnClickListener {
            //Enviar informacion adicional
            FirebaseCrashlytics.getInstance().setUserId(email!!)
            FirebaseCrashlytics.getInstance().setCustomKey("provider", provider!!)
            //Enviar log de contexto
            FirebaseCrashlytics.getInstance().log("Se ha pulsado el botón crash")
            //Forzado de error
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(
            crashButton, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        //Remote config
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                val showErrorButton = Firebase.remoteConfig.getBoolean("show_error_button")
                val errorButtonText = Firebase.remoteConfig.getString("error_button_text")
                if (showErrorButton) {
                    crashButton.visibility = View.VISIBLE
                    binding.SaveDB.visibility = View.VISIBLE
                    binding.DeleteDB.visibility = View.VISIBLE
                    binding.GetDB.visibility = View.VISIBLE
                    binding.etAddress.visibility = View.VISIBLE
                    binding.etPhone.visibility = View.VISIBLE
                }
                crashButton.text = errorButtonText
            }
        }
        binding.fabShare.setOnClickListener { fabActionShare() }
        binding.fabDownloadDB.setOnClickListener {
            downloadDB()
        }
    }

    private fun downloadDB() {
        val lastIndex = binding.etExp.text.toString().toInt()
        for (index in 0..lastIndex) {
            Log.e("index", index.toString())
            getFromDB(index.toString())
            alertDialogMakePDF(index.toString(), "FisioDatosDataBase")
            binding.pdf1.text = ""
            binding.pdf2.text = ""
            binding.pdf3.text = ""
            binding.pdf4.text = ""
            binding.pdf5.text = ""
            binding.pdf6.text = ""
            binding.pdf7.text = ""
            binding.pdf8.text = ""
            binding.pdf9.text = ""
            binding.pdf10.text = ""
        }

    }


    private fun fabActionShare() {
        getFromDB(binding.etExp.text.toString())
        alertDialogMakePDF(binding.etExp.text.toString(), "FisioDatosDoc")
    }

    private fun getFromDB(exp: String) {
        //Historia clinica
        db.collection(getString(db_expedientes))
            .document(exp).get().addOnSuccessListener {
                binding.pdf1.text = it.data.toString()
                    .removePrefix("{")
                    .replace(", ", "\n")
                    .replace("=", " = ")
                    .removeSuffix("}")
                Log.e("exp", exp)
            }
        //Signos vitales
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_signos_vitales)).get().addOnSuccessListener {
                binding.pdf2.text = it.data.toString()
                    .removePrefix("{")
                    .replace(", ", "\n")
                    .replace("=", " = ")
                    .removeSuffix("}")
            }
//            Antecedentes familiares
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_antecendentes_hf)).get().addOnSuccessListener {
                binding.pdf3.text = it.data.toString()
                    .removePrefix("{")
                    .replace(", ", "\n")
                    .replace("=", " = ")
                    .removeSuffix("}")
            }
//            antecedentes patologicos
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_antecedentes_p)).get().addOnSuccessListener {

                binding.pdf4.text = it.data.toString()
                    .removePrefix("{")
                    .replace(", ", "\n")
                    .replace("=", " = ")
                    .removeSuffix("}")
            }
//            antecedentes no patologicos
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(antecedentes_pNp)).get().addOnSuccessListener {

                binding.pdf5.text = it.data.toString()
                    .removePrefix("{")
                    .replace(", ", "\n")
                    .replace("=", " = ")
                    .removeSuffix("}")
            }
//            aparato sistema
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_aparato_sistema)).get().addOnSuccessListener {

                binding.pdf6.text = it.data.toString()
                    .removePrefix("{")
                    .replace(", ", "\n")
                    .replace("=", " = ")
                    .removeSuffix("}")
            }
//            problemas geriatricos
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_problemas_geriatricos)).get().addOnSuccessListener {

                binding.pdf7.text = it.data.toString()
                    .removePrefix("{")
                    .replace(", ", "\n")
                    .replace("=", " = ")
                    .removeSuffix("}")
            }
//            evaluacion fisioterapeuta
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(exp_evaluacion_fisio)).get().addOnSuccessListener {
                if (it.data != null) {
                    binding.pdf8.text = it.data.toString()
                        .removePrefix("{")
                        .replace(", ", "\n")
                        .replace("=", " = ")
                        .removeSuffix("}")
                } else binding.pdf8.text = "No se ha generado"

            }
//            Escalas
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes)).document(
                getString(db_escalas)
            ).get().addOnSuccessListener {
                if (it.data != null) {
                    binding.pdf9.text = it.data.toString()
                        .removePrefix("{")
                        .replace(", ", "\n")
                        .removeSuffix("}")
                } else binding.pdf9.text = "No se ha generado"
            }
//            Indicadores de abandono
        db.collection(getString(db_expedientes)).document(exp)
            .collection(getString(db_datos_interes))
            .document(getString(strAbandono)).get().addOnSuccessListener {
                if (it.data != null) {
                    binding.pdf10.text = it.data.toString()
                        .removePrefix("{")
                        .replace(", ", "\n")
                        .replace("=", " = ")
                        .removeSuffix("}")
                } else binding.pdf10.text = "No se ha generado"
            }
    }

    private fun alertDialogMakePDF(exp: String, folder: String) {
        var goToPDF: Boolean
        MaterialAlertDialogBuilder(this).setTitle("Generar PDF")
            .setMessage("¿Está seguro que desea generar el documento del expediente $exp?")
            //.setView(R.layout.password_dialog)
            .setNegativeButton("Cancelar") { _, _ -> goToPDF = false }
            .setPositiveButton("Aceptar") { _, _ ->
                goToPDF = true
                //MaterialAlertDialogBuilder(this).setView(R.layout.password_dialog).show()
                val infoFromDB = FileInfoFromDB()
                infoFromDB.historia =
                    binding.pdf1.text.toString() + "\n" +
                            binding.pdf2.text.toString() + "\n" +
                            binding.pdf3.text.toString() + "\n" +
                            binding.pdf4.text.toString() + "\n" +
                            binding.pdf5.text.toString() + "\n" +
                            binding.pdf6.text.toString() + "\n" +
                            binding.pdf7.text.toString()
                infoFromDB.evaluacion = binding.pdf8.text.toString()
                infoFromDB.escalas = binding.pdf9.text.toString()
                infoFromDB.abandono = binding.pdf10.text.toString()
                createPDF(exp, infoFromDB, goToPDF, folder)
            }
            .show()
    }


    private fun createPDF(
        exp: String,
        infoFromDB: FileInfoFromDB,
        checked: Boolean,
        folder: String
    ) {
        //Toast.makeText(this, exp, Toast.LENGTH_SHORT).show()
        try {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val date = "$day/${month + 1}/$year"
            val path =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/$folder"
            val dir = File(path)
            if (!dir.exists()) {
                dir.mkdirs()
                Toast.makeText(this, "Carpeta creada", Toast.LENGTH_SHORT).show()
            } //else Toast.makeText(this, "La carpeta ya existe", Toast.LENGTH_SHORT).show()
            val pdfFile = File(dir, "$exp.pdf")
            val doc = Document()
            val fos = FileOutputStream(pdfFile)
            PdfWriter.getInstance(doc, fos)
            doc.open()
            val imagenTop = getImageForPDF()
            val imagenBottom = getImageBottomForPDF()
            //if (doc.isOpen) Toast.makeText(this, "Open", Toast.LENGTH_SHORT).show()
            val subtitle = Paragraph(
                "Expediente $exp\n",
                FontFactory.getFont("arial", 22f, Font.BOLD, BaseColor.BLACK)
            )
            val fecha = Paragraph(
                "Fecha de creación: $date",
                FontFactory.getFont("arial", 12f, Font.NORMAL, BaseColor.BLACK)
            )
            fecha.alignment = Element.ALIGN_RIGHT
            doc.add(imagenTop)
            doc.addTitle("Fisiodatos")
            doc.addCreationDate()
            doc.add(subtitle)
            doc.add(fecha)
            doc.add(
                Paragraph(
                    getString(history_string_title) + "\n",
                    FontFactory.getFont("arial", 20f, Font.NORMAL, BaseColor.BLACK)
                )
            )
            doc.add(
                Paragraph(
                    infoFromDB.historia,
                    FontFactory.getFont("arial", 12f, Font.NORMAL, BaseColor.BLACK)
                )
            )
            doc.add(
                Paragraph(
                    getString(db_escalas) + "\n",
                    FontFactory.getFont("arial", 20f, Font.NORMAL, BaseColor.BLACK)
                )
            )
            doc.add(
                Paragraph(
                    infoFromDB.escalas,
                    FontFactory.getFont("arial", 12f, Font.NORMAL, BaseColor.BLACK)
                )
            )
            doc.add(
                Paragraph(
                    getString(exp_evaluacion_fisio) + "\n",
                    FontFactory.getFont("arial", 20f, Font.NORMAL, BaseColor.BLACK)
                )
            )
            doc.add(
                Paragraph(
                    infoFromDB.evaluacion,
                    FontFactory.getFont("arial", 12f, Font.NORMAL, BaseColor.BLACK)
                )
            )
            doc.add(
                Paragraph(
                    getString(strAbandono) + "\n",
                    FontFactory.getFont("arial", 20f, Font.NORMAL, BaseColor.BLACK)
                )
            )
            doc.add(
                Paragraph(
                    infoFromDB.abandono,
                    FontFactory.getFont("arial", 12f, Font.NORMAL, BaseColor.BLACK)
                )
            )
            doc.add(Paragraph("\n\n"))
            doc.add(imagenBottom)
            doc.close()
            Toast.makeText(this, "Expediente $exp creado en $path", Toast.LENGTH_SHORT).show()
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageForPDF(): Image {
        val bm = BitmapFactory.decodeResource(resources, drawable.welcome)
        val stream = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 50, stream)
        var img: Image? = null
        val byteArray = stream.toByteArray()
        try {
            img = Image.getInstance(byteArray)
            img.scalePercent(2f)
            img.alignment = Element.ALIGN_CENTER
            return@getImageForPDF img!!
        } catch (e: BadElementException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return img!!
    }

    private fun getImageBottomForPDF(): Image {
        val bm1 = BitmapFactory.decodeResource(resources, drawable.all)
        val stream = ByteArrayOutputStream()
        bm1.compress(Bitmap.CompressFormat.PNG, 50, stream)
        var img: Image? = null
        val byteArray = stream.toByteArray()
        try {
            img = Image.getInstance(byteArray)
            img.scalePercent(20f)
            img.alignment = Element.ALIGN_CENTER
            return img!!
        } catch (e: BadElementException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return img!!
    }

    private fun getImagesFromStorage() {
        //storageReference.root.child(getString(db_expedientes)).listAll()
    }

    private fun setup(email: String, username: String, provider: String) {
        binding.tvWelcome.text = "Bienvenid@ $username"
        //binding.tvProvider.text = provider
        binding.tvEmail.text = email
        db.collection("users").document(email).set(
            hashMapOf(
                "Nombre" to provider,
                "fecha de reg" to binding.etAddress.text.toString(),
                "phone" to binding.etPhone.text.toString()
            )
        )
        binding.SaveDB.setOnClickListener {
            db.collection("users").document(email).set(
                hashMapOf(
                    "provider" to provider,
                    "address" to binding.etAddress.text.toString(),
                    "phone" to binding.etPhone.text.toString()
                )
            )
        }
        binding.GetDB.setOnClickListener {
            db.collection(getString(db_expedientes)).document(binding.etExp.text.toString())
                .get().addOnSuccessListener {
                    binding.etAddress.setText(it.get(getString(exp_name)) as String?)
                    //binding.etPhone.setText(it.get("phone") as String?)
                }
        }
        binding.DeleteDB.setOnClickListener {
            db.collection("users").document(email).delete()
        }
    }

    //Documento de texto
    private fun txtDoc() {
        val nombreArchivo =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString() +
                    "/" + "${binding.etExp.text}.txt"
        //Toast.makeText(this, "Guardando en $nombreArchivo", Toast.LENGTH_SHORT).show()
        val file = File(nombreArchivo)
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(file)
        val bufferedWriter = BufferedWriter(fileWriter)
        bufferedWriter.write(
            "FISIODATOS \n\n" +
                    "EXPEDIENTE: \t ${binding.etExp.text}"
        )// <-- Aquí el contenido
        bufferedWriter.close()
    }

    //Fecha
    fun setDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val date = "$day/${month + 1}/$year"
        binding.tvDate.text = date

    }

    //Spinner
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p2 == 0) {
            binding.bIndAb.isEnabled = false
        }
        if (p2 == 1) {
            binding.bIndAb.isEnabled = true
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    //Menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_log_out, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == id.action_logOut) {
            val prefs =
                getSharedPreferences(getString(prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear().apply()
            FirebaseAuth.getInstance().signOut()

            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertEditDoc(botonId: Int) {
        val builder = AlertDialog.Builder(this)
        var edit: Boolean
        val exp = binding.etExp.text.toString()
        builder.setTitle("Editar").setMessage("¿Está seguro(a) que desea modificar el expediente?")
            .setPositiveButton("Aceptar") { _, _ ->
                edit = true
                when (botonId) {
                    1 -> checkExp()
                    2 -> checkScales()
                    3 -> checkAb()
                    4 -> checkEvalF()
                    5 -> checkHistCg()
                    else -> null
                }
            }
            .setNegativeButton("Cancelar") { _, _ -> edit = false }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //botones
    fun botones() {
        //binding.bSearchExp.setOnClickListener { checkVal() }

        binding.bFichaId.setOnClickListener { showAlertEditDoc(1) }
        binding.bMenuScale.setOnClickListener { showAlertEditDoc(2) }
        binding.bIndAb.setOnClickListener { showAlertEditDoc(3) }
        binding.bEvFisio.setOnClickListener { showAlertEditDoc(4) }
        binding.bHistCg.setOnClickListener { showAlertEditDoc(5) }
    }

    //historia clinica geiratrica
    fun checkHistCg() {

        if (binding.etExp.text.isNotEmpty()) {
            startActivity(intent)
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("EXP", binding.etExp.text.toString())
            intent.putExtra("DATE", binding.tvDate.text.toString())
            intent.putExtra("email", binding.tvEmail.text.toString())
            historyLauncher.launch(intent)
        } else {
            showErrorName()
        }
    }

    //Evaluacion fisioterapeutica
    fun checkEvalF() {
        if (binding.etExp.text.isNotEmpty()) {
            startActivity(intent)
            val intent = Intent(this, EvalFisioActivity::class.java)
            intent.putExtra("EXP", binding.etExp.text)
            intent.putExtra("DATE", binding.tvDate.text)
            evalFLauncher.launch(intent)
        } else {
            showErrorName()
        }
    }

    //Menu de escalas
    fun checkScales() {
        if (binding.etExp.text.isNotEmpty()) {
            startActivity(intent)
            val intent = Intent(this, MenuScalesActivity::class.java)
            intent.putExtra("EXP", binding.etExp.text)
            intent.putExtra("DATE", binding.tvDate.text)
            menuScalesLauncher.launch(intent)
        } else {
            showErrorName()
        }
    }

    //Indicadores de abandono
    fun checkAb() {
        if (binding.etExp.text.isNotEmpty()) {
            startActivity(intent)
            val intent = Intent(this, AbandonoActivity::class.java)
            intent.putExtra("EXP", binding.etExp.text.toString())
            intent.putExtra("DATE", binding.tvDate.text)
            indicALauncher.launch(intent)
        } else {
            showErrorName()
        }
    }

    //Ficha de identificacion
    fun checkExp() {
        if (binding.etExp.text.isNotEmpty()) {
            startActivity(intent)
            val intent = Intent(this, FichaDeIdActivity::class.java)
            intent.putExtra("EXP", binding.etExp.text.toString())
            intent.putExtra("DATE", binding.tvDate.text.toString())
            fichaIdLauncher.launch(intent)

        } else {
            showErrorName()
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error").setMessage(message)
            .setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showErrorName() {
        Toast.makeText(this, "El expediente no puede estar vacio", Toast.LENGTH_SHORT).show()
    }
}
