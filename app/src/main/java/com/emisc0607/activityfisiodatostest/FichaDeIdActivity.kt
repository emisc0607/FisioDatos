package com.emisc0607.activityfisiodatostest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.emisc0607.activityfisiodatostest.R.*
import com.emisc0607.activityfisiodatostest.databinding.ActivityFichaDeIdBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.OutputStream
import java.util.Calendar


class FichaDeIdActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var spinner1: ArrayAdapter<String>
    private lateinit var spinner2: ArrayAdapter<String>
    private lateinit var binding: ActivityFichaDeIdBinding
    private lateinit var file: File
    private lateinit var storageReference: StorageReference
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFichaDeIdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //spinners()
        showExp()
        val exp = binding.tvExp.text.toString()
        getDB(exp)
        binding.bSave.setOnClickListener { save() }
        //cameraCode()
        binding.bCamera.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { _ ->
                    createPhotoFile()
                    val photoUri: Uri =
                        FileProvider.getUriForFile(
                            this,
                            BuildConfig.APPLICATION_ID + ".fileprovider", file
                        )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            openCamera.launch(intent)
            //saveToGallery()
            //openCamera.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
        binding.bSelect.setOnClickListener {
            saveToGallery()
        }
        storageReference = FirebaseStorage.getInstance().reference

        db.collection(getString(string.db_expedientes)).document(exp).get().addOnSuccessListener {
            val url = it.get("Foto Id") as String?
            if (url != null) {
                Glide.with(this)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .circleCrop()
                    .into(binding.imageView)
            }
        }
    }

    private fun getDB(exp: String) {
        val illness: MutableList<String> = mutableListOf()
        val sindromes: MutableList<String> = mutableListOf()
        db.collection(getString(string.db_expedientes)).document(exp).get().addOnSuccessListener {
            binding.tvEmail.text = (it.get(getString(string.db_aplicante)) as String?)
            binding.etName.setText(it.get(getString(string.exp_name)) as String?)
            binding.etAge.setText(it.get(getString(string.exp_age)) as String?)
            binding.etDoBirth.setText(it.get(getString(string.exp_fecha_de_nacimiento)) as String?)
            binding.etEsc.setText(it.get(getString(string.exp_escolaridad)) as String?)
            binding.etCivil.setText(it.get(getString(string.exp_estado_civil)) as String?)
            binding.etEmail.setText(it.get("Email") as String?)
            binding.etPhone.setText(it.get("Teléfono") as String?)
        }
        db.collection(getString(string.db_expedientes)).document(exp)
            .collection(getString(string.db_datos_interes))
            .document(getString(string.exp_signos_vitales)).get().addOnSuccessListener {
                binding.etWeight.setText(it.get(getString(string.exp_peso)) as String?)
                binding.etHeight.setText(it.get(getString(string.exp_talla)) as String?)
            }
        var diagnostico = ""
        val patologia = Array(15) { "" }
        patologia[0] = getString(string.illness_tuberculosis)
        patologia[1] = getString(string.illness_hipertension)
        patologia[2] = getString(string.illness_diabetes)
        patologia[3] = getString(string.illness_dislipidemias)
        patologia[4] = getString(string.illness_demencia)
        patologia[5] = getString(string.illness_cancer)
        patologia[6] = getString(string.illness_osteoartritis)
        patologia[7] = getString(string.illness_avc)
        patologia[8] = getString(string.illness_cardiovascular)
        patologia[9] = getString(string.illness_transfusiones)
        patologia[10] = getString(string.illness_dolor)
        patologia[11] = getString(string.illness_caidas)
        patologia[12] = getString(string.illness_hepatitis)
        patologia[13] = getString(string.illness_hospitalizaciones)
        patologia[14] = getString(string.illness_otro)

        db.collection(getString(string.db_expedientes)).document(exp)
            .collection(getString(string.db_datos_interes))
            .document(getString(string.exp_antecedentes_p)).get().addOnSuccessListener {
                illness.add(0, it.get(getString(string.illness_tuberculosis)) as String)
                illness.add(1, it.get(getString(string.illness_hipertension)) as String)
                illness.add(2, it.get(getString(string.illness_diabetes)) as String)
                illness.add(3, it.get(getString(string.illness_dislipidemias)) as String)
                illness.add(4, it.get(getString(string.illness_demencia)) as String)
                illness.add(5, it.get(getString(string.illness_cancer)) as String)
                illness.add(6, it.get(getString(string.illness_osteoartritis)) as String)
                illness.add(7, it.get(getString(string.illness_avc)) as String)
                illness.add(8, it.get(getString(string.illness_cardiovascular)) as String)
                illness.add(9, it.get(getString(string.illness_transfusiones)) as String)
                illness.add(10, it.get(getString(string.illness_dolor)) as String)
                illness.add(11, it.get(getString(string.illness_caidas)) as String)
                illness.add(12, it.get(getString(string.illness_hepatitis)) as String)
                illness.add(13, it.get(getString(string.illness_hospitalizaciones)) as String)
                illness.add(14, it.get(getString(string.illness_otro)) as String)
                for (i in illness.indices) {
                    if (illness[i] != "NA") {
                        diagnostico += patologia[i] + ": " + illness[i] + "\n"
                    }

                }
//                Toast.makeText(this, diagnostico, Toast.LENGTH_SHORT).show()
                binding.etIllness.setText(diagnostico)
            }
        db.collection(getString(string.db_expedientes)).document(exp)
            .collection(getString(string.db_datos_interes))
            .document(getString(string.exp_problemas_geriatricos)).get().addOnSuccessListener {
                val checkedCB = Array(7) { false }
                sindromes.add(0, it.get(getString(string.obs_auxiliar_de_marcha)) as String)
                sindromes.add(1, it.get(getString(string.obs_debilidad_auditiva)) as String)
                sindromes.add(2, it.get(getString(string.obs_incontinencia_urinaria)) as String)
                sindromes.add(3, it.get(getString(string.obs_debilidad_visual)) as String)
                sindromes.add(4, it.get(getString(string.obs_deterioro_cognitivo)) as String)
                sindromes.add(5, it.get(getString(string.obs_ulceras_por_presion)) as String)
                sindromes.add(6, it.get(getString(string.obs_inmovilidad)) as String)
                for (i in sindromes.indices) {
                    checkedCB[i] = sindromes[i] != "NA"
                }
                binding.sAuxM.isChecked = checkedCB[0]
                binding.sAuxA.isChecked = checkedCB[1]
                binding.sIncont.isChecked = checkedCB[2]
                binding.sGlasses.isChecked = checkedCB[3]
                binding.sDetC.isChecked = checkedCB[4]
                binding.sUpPres.isChecked = checkedCB[5]
                binding.sInmov.isChecked = checkedCB[6]
            }
        db.collection(getString(string.db_expedientes)).document(exp)
            .collection(getString(string.db_datos_interes))
            .document(getString(string.exp_evaluacion_fisio)).get().addOnSuccessListener {
                binding.etFisioOint.setText(it.get(getString(string.evf_intervencion_objetivos)) as String?)
                binding.etFisioEint.setText(it.get(getString(string.evf_intervencion_estrategias)) as String?)
            }
    }

    //Camera
    private val openCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // val data = result.data!!
                // val bitmap = data.extras!!.get("data") as Bitmap
                val bitmap = getBitmap()
                Glide.with(this)
                    .load(bitmap)
                    .centerCrop()
                    .circleCrop()
                    .into(binding.imageView)
                //binding.imageView.rotation = 90f
            }
        }

    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${binding.tvExp.text}_", ".jpg", dir)
    }

    private fun saveToGallery() {
        val content = createContent()
        val uri = saveImg(content)
        postSnapshot(uri)
        clearContents(content, uri)
        Toast.makeText(this, getString(string.image_saved), Toast.LENGTH_LONG).show()
    }

    private fun postSnapshot(uri: Uri) {
        val storage = storageReference.child("expedientes").child("IMG_${binding.tvExp.text}_ID")
        storage.putFile(uri).addOnSuccessListener {
            Snackbar.make(
                binding.root,
                "Imagen publicada",
                Snackbar.LENGTH_LONG
            ).show()
            it.storage.downloadUrl.addOnSuccessListener {
                saveSnapshot(binding.tvExp.text.toString(), it.toString())
            }
        }.addOnFailureListener {
            Snackbar.make(
                binding.root,
                "Error al publicar",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun saveSnapshot(key: String, url: String) {
        db.collection(getString(R.string.db_expedientes)).document(key).update(
            "Foto Id", url
        )
    }

    private fun createContent(): ContentValues {
        val fileName = file.name
        val fileType = "image/jpg"
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.Files.FileColumns.MIME_TYPE, fileType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
    }

    private fun saveImg(content: ContentValues): Uri {
        var outputStream: OutputStream?
        var uri: Uri?
        application.contentResolver.also { resolver ->
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
            outputStream = resolver.openOutputStream(uri!!)
        }
        outputStream.use { output ->
            getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, output)
        }
        return uri!!
    }

    private fun clearContents(content: ContentValues, uri: Uri) {
        content.clear()
        content.put(MediaStore.MediaColumns.IS_PENDING, 0)
        contentResolver.update(uri, content, null, null)
    }

    private fun getBitmap(): Bitmap {
        return BitmapFactory.decodeFile(file.toString())
    }

    //Camera
    /*private fun spinners() {
        //Spinner EdoCivil
        spinner1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
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
        spinner2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        spinner2.addAll(
            listOf(
                "Ninguna",
                "Primaria",
                "Secundaria",
                "Preparatoria",
                "Licenciatura",
                "Posgrado"
            )
        )
        binding.sEsc.onItemSelectedListener = this
        binding.sEsc.adapter = spinner2

        binding.etDoBirth.setOnClickListener { showDate() }
    }*/

    private fun showDate() {
        val datePicker = DatePickerFragment { day, month, year -> onDaySelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    private fun onDaySelected(day: Int, month: Int, year: Int) {
        binding.etDoBirth.setText("$day/$month/$year")
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.getString("EXP")
        binding.tvExp.text = exp
    }

    fun save() {
        if (
            binding.etName.text.isNotEmpty() or
            binding.etAge.text.isNotEmpty() or
            binding.etDoBirth.text.isNotEmpty() or
            binding.etHeight.text.isNotEmpty() or
            binding.etWeight.text.isNotEmpty() or
            binding.etEmail.text.isNotEmpty() or
            binding.etPhone.text.isNotEmpty() or
            binding.etIllness.text.isNotEmpty() or
            binding.etSport.text.isNotEmpty() or
            binding.etTimeSport.text.isNotEmpty() or
            binding.etNutri.text.isNotEmpty()
        ) {
            val fid1 =
                binding.tvName.text.toString() + ":\t" + binding.etName.text.toString() + "\n"
            val fid2 = binding.tvExp.text.toString() + "\n"
            val fid3 = binding.tvAge.text.toString() + ":\t" + binding.etAge.text.toString() + "\n"
            val fid4 =
                binding.tvDoBirth.text.toString() + ":\t" + binding.etDoBirth.text.toString() + "\n"
            val fid5 =
                binding.tvHeight.text.toString() + ":\t" + binding.etHeight.text.toString() + "\n"
            val fid6 =
                binding.tvWeight.text.toString() + ":\t" + binding.etWeight.text.toString() + "\n"
            val fid7 =
                binding.tvEmail.text.toString() + ":\t" + binding.etEmail.text.toString() + "\n"
            val fid8 =
                binding.tvPhone.text.toString() + ":\t" + binding.etPhone.text.toString() + "\n"
//            val fid9 = binding.tvSchool.text.toString() + ":\t" + binding.sEsc.selectedItem + "\n"
//            val fid10 =
//                binding.tvEdoCivil.text.toString() + ":\t" + binding.sEdoCivil.selectedItem + "\n"
            val fid11 =
                binding.tvIllness.text.toString() + ":\t" + binding.etIllness.text.toString() + "\n"
            val fid12 =
                binding.tvSport.text.toString() + ":\t" + binding.etSport.text.toString() + "\n"
            val fid29 =
                binding.tvTimeSport.text.toString() + ":\t" + binding.etTimeSport.text.toString() + " años\n"
            val fid13 =
                binding.tvNutri.text.toString() + ":\t" + binding.etNutri.text.toString() + "\n"
            var fid24 = ""
            val fid14 = if (binding.sDent.isChecked) binding.sDent.text.toString() + ":\tSi\n"
            else binding.sDent.text.toString() + ":\t No\n"
            val fid15 = if (binding.sAuxA.isChecked) binding.sAuxA.text.toString() + ":\t Si\n"
            else binding.sAuxA.text.toString() + ":\t No\n"
            val fid16 = if (binding.sAuxM.isChecked) binding.sAuxM.text.toString() + ":\t Si\n"
            else binding.sAuxM.text.toString() + ":\t No\n"
            val fid17 =
                if (binding.sAuxADVH.isChecked) binding.sAuxADVH.text.toString() + ":\t Si\n"
                else binding.sAuxADVH.text.toString() + ":\t No\n"
            val fid18 = if (binding.sIncont.isChecked) binding.sIncont.text.toString() + ":\t Si\n"
            else binding.sIncont.text.toString() + ":\t No\n"
            val fid19 =
                if (binding.sGlasses.isChecked) binding.sGlasses.text.toString() + ":\t Si\n"
                else binding.sGlasses.text.toString() + ":\t No\n"
            val fid20 = if (binding.sTransC.isChecked) binding.sTransC.text.toString() + ":\t Si\n"
            else binding.sTransC.text.toString() + ":\t No\n"
            val fid21 = if (binding.sDetC.isChecked) binding.sDetC.text.toString() + ":\t Si\n"
            else binding.sDetC.text.toString() + ":\t No\n"
            val fid22 = if (binding.sUpPres.isChecked) binding.sUpPres.text.toString() + ":\t Si\n"
            else binding.sUpPres.text.toString() + ":\t No\n"
            val fid23 = if (binding.sInmov.isChecked) binding.sInmov.text.toString() + ":\t Si\n"
            else binding.sInmov.text.toString() + ":\t No\n"
            if (binding.cbRiscA.isChecked) fid24 =
                binding.tvRisC.text.toString() + ":\t" + binding.cbRiscA.text.toString() + "\n"
            if (binding.cbRiscB.isChecked) fid24 =
                binding.tvRisC.text.toString() + ":\t" + binding.cbRiscB.text.toString() + "\n"
            if (binding.cbRiscM.isChecked) fid24 =
                binding.tvRisC.text.toString() + ":\t" + binding.cbRiscM.text.toString() + "\n"
            val fid25 = binding.tvFisio.text.toString() + "\n"
            val fid26 =
                binding.tvFisioOint.text.toString() + ":\t" + binding.etFisioOint.text.toString() + "\n"
            val fid27 =
                binding.tvFisioEint.text.toString() + ":\t" + binding.etFisioEint.text.toString() + "\n"

            val fid = fid1 + fid2 + fid3 + fid4 + fid5 + fid6 + fid7 + fid8 +
                    fid11 + fid12 + fid29 + fid13 + fid14 + fid15 + fid16 + fid17 +
                    fid18 + fid19 + fid20 + fid21 + fid22 + fid23 + fid24 + fid25 +
                    fid26 + fid27
            val intent = Intent()
            intent.putExtra("FID_DATA", binding.tvExp.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        } else {
            Toast.makeText(this, "No deje ningún campo vacío", Toast.LENGTH_LONG).show()
        }
        if (binding.etEmail.text.isNotEmpty()) db.collection(getString(string.db_expedientes))
            .document(binding.tvExp.text.toString())
            .update("Email", binding.etEmail.text.toString())
        if (binding.etPhone.text.isNotEmpty()) db.collection(getString(string.db_expedientes))
            .document(binding.tvExp.text.toString())
            .update("Teléfono", binding.etPhone.text.toString())
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val eSelected = spinner2.getItem(p2)
        binding.tvEsc.text = eSelected
        val cSelected = spinner1.getItem(p2)
        binding.tvCivil.text = cSelected
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
