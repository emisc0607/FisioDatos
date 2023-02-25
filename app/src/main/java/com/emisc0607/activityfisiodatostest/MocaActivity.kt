package com.emisc0607.activityfisiodatostest

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.emisc0607.activityfisiodatostest.databinding.ActivityMocaBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.OutputStream

class MocaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMocaBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var storageReference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMocaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showExp()
        binding.bRes.setOnClickListener { switches() }
        binding.bSave.setOnClickListener {
            if(binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(binding.root, "Presione RESULTADO antes de guardar", Snackbar.LENGTH_SHORT).show()
        }
        storageReference = FirebaseStorage.getInstance().reference
        cameraCode()
        getSnapshot(binding.tvExp.text.toString())
    }

    private fun switches() {
        val moca = Array(30) { 0 }
        val restas = Array(5) { 0 }
        val mis = Array(5) { 0 }
        if (binding.etMoca1.text.isEmpty() or binding.etMoca2.text.isEmpty() or binding.etEscolaridad.text.isEmpty()) {
            if (binding.etMoca1.text.isEmpty()) binding.etMoca1.error = "Requerido"
            if (binding.etMoca2.text.isEmpty()) binding.etMoca2.error = "Requerido"
            if (binding.etEscolaridad.text.isEmpty()) binding.etEscolaridad.error = "Requerido"
            Toast.makeText(this, "No deje ningún campo vacío", Toast.LENGTH_LONG).show()
        }
        else {
            val p1 = binding.etMoca1.text.toString().toInt()
            val p2 = binding.etMoca2.text.toString().toInt()
            val p3 = binding.etEscolaridad.text.toString().toInt()
            //visuoespacial = 5
            if (binding.cbMoca1.isChecked) moca[0] = 1
            if (binding.cbMoca2.isChecked) moca[1] = 1
            if (binding.cbMoca3.isChecked) moca[2] = 1
            if (binding.cbMoca4.isChecked) moca[3] = 1
            if (binding.cbMoca5.isChecked) moca[4] = 1
            //identificacion = 3
            if (binding.cbMoca6.isChecked) moca[5] = 1
            if (binding.cbMoca7.isChecked) moca[6] = 1
            if (binding.cbMoca8.isChecked) moca[7] = 1
            //atencion - serie de numeros = 2
            if (binding.cbMoca9.isChecked) moca[8] = 1
            if (binding.cbMoca10.isChecked) moca[9] = 1
            //atencion - serie de letras = 1
            if (p1 >= 2) moca[10] = 1
            //atencion - restas = 3
            if (binding.cbMoca11.isChecked) restas[0] = 1
            if (binding.cbMoca12.isChecked) restas[1] = 1
            if (binding.cbMoca13.isChecked) restas[2] = 1
            if (binding.cbMoca14.isChecked) restas[3] = 1
            if (binding.cbMoca15.isChecked) restas[4] = 1
            if (restas.sum() >= 4) moca[11] = 3
            else if (restas.sum() in 2..3) moca[11] = 2
            else if (restas.sum() == 1) moca[11] = 1
            else moca[11] = 0
            //lenguaje - repetir  = 2
            if (binding.cbMoca16.isChecked) moca[12] = 1
            if (binding.cbMoca17.isChecked) moca[13] = 1
            //lenguaje - fluides del lenguaje = 1
            if (p2 > 10) moca[14] = 1
            //abstraccion = 2
            if (binding.cbMoca18.isChecked) moca[15] = 1
            if (binding.cbMoca19.isChecked) moca[16] = 1
            //recuedo diferido = 5
            if (binding.rbMoca1.isChecked) {
                mis[0] = 3
                moca[17] = 1
            }
            if (binding.rbMoca2.isChecked) mis[0] = 2
            if (binding.rbMoca3.isChecked) mis[0] = 1
            if (binding.rbMoca4.isChecked) {
                mis[1] = 3
                moca[18] = 1
            }
            if (binding.rbMoca5.isChecked) mis[1] = 2
            if (binding.rbMoca6.isChecked) mis[1] = 1
            if (binding.rbMoca7.isChecked) {
                mis[2] = 3
                moca[19] = 1
            }
            if (binding.rbMoca8.isChecked) mis[2] = 2
            if (binding.rbMoca9.isChecked) mis[2] = 1
            if (binding.rbMoca10.isChecked) {
                mis[3] = 3
                moca[20] = 1
            }
            if (binding.rbMoca11.isChecked) mis[3] = 2
            if (binding.rbMoca12.isChecked) mis[3] = 1
            if (binding.rbMoca13.isChecked) {
                mis[4] = 3
                moca[21] = 1
            }
            if (binding.rbMoca14.isChecked) mis[4] = 2
            if (binding.rbMoca15.isChecked) mis[4] = 1
            //orientacion = 6

            if (binding.cbMoca20.isChecked) moca[22] = 1
            if (binding.cbMoca21.isChecked) moca[23] = 1
            if (binding.cbMoca22.isChecked) moca[24] = 1
            if (binding.cbMoca23.isChecked) moca[25] = 1
            if (binding.cbMoca24.isChecked) moca[26] = 1
            if (binding.cbMoca25.isChecked) moca[27] = 1
            //escolaridad
            if (p3 <= 12) moca[28] = 1

            val r = ContextCompat.getColor(this, R.color.red)
            val y = ContextCompat.getColor(this, R.color.yellow)
            val g = ContextCompat.getColor(this, R.color.green)
            if (moca.sum() > 25) {
                binding.tvRes.setBackgroundColor(g)
                binding.tvRes.text = "MSI:\t${mis.sum()}\n" + "Se considera normal:\t${moca.sum()}"
            } else {
                binding.tvRes.setBackgroundColor(y)
                binding.tvRes.text =
                    "MSI:\t${mis.sum()}\n" + "Probable transtorno cognitivo:\t${moca.sum()}"
            }
        }
    }

    //Camera
    var photo = 0
    private fun cameraCode() {

        binding.bCamera1.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    createPhotoFile()
                    val photoUri: Uri =
                        FileProvider.getUriForFile(
                            this,
                            BuildConfig.APPLICATION_ID + ".fileprovider", file
                        )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            photo = 1
            openCamera.launch(intent)
        }
        binding.bCamera2.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    createPhotoFile()
                    val photoUri: Uri =
                        FileProvider.getUriForFile(
                            this,
                            BuildConfig.APPLICATION_ID + ".fileprovider", file
                        )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            photo = 2
            openCamera.launch(intent)
        }
        binding.bCamera3.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    createPhotoFile()
                    val photoUri: Uri =
                        FileProvider.getUriForFile(
                            this,
                            BuildConfig.APPLICATION_ID + ".fileprovider", file
                        )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            photo = 3
            openCamera.launch(intent)
        }
    }

    private lateinit var file: File
    private val openCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val bitmap = getBitmap()
                if (photo == 1) {
                    Glide.with(this)
                        .load(bitmap)
                        .centerCrop()
                        .into(binding.image1)
                    //binding.image1.rotation = 90f
                    saveToGallery()
                } else if (photo == 2) {
                    Glide.with(this)
                        .load(bitmap)
                        .centerCrop()
                        .into(binding.image2)
                    //binding.image2.rotation = 90f
                    saveToGallery()
                } else if (photo == 3) {
                    Glide.with(this)
                        .load(bitmap)
                        .centerCrop()
                        .into(binding.image3)
                    //binding.image3.rotation = 90f
                    saveToGallery()
                }
            }
        }

    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${binding.tvExp.text}_Moca_", ".jpg", dir)
    }

    private fun saveToGallery() {
        val content = createContent()
        val uri = saveImg(content)
        postSnapshot(uri)
        clearContents(content, uri)
        Toast.makeText(this, getString(R.string.image_saved), Toast.LENGTH_LONG).show()
    }

    private fun getSnapshot(exp: String){

        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas)).get().addOnSuccessListener {
            val url1 = it.get("Foto Moca1") as String?
            if (url1 != null) {
                Glide.with(this)
                    .load(url1)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.image1)
            }
            val url2 = it.get("Foto Moca2") as String?
            if (url2 != null) {
                Glide.with(this)
                    .load(url2)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.image2)
            }
            val url3 = it.get("Foto Moca3") as String?
            if (url3 != null) {
                Glide.with(this)
                    .load(url3)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.image3)
            }
        }
    }
    private fun postSnapshot(uri: Uri) {
        val storage =
            storageReference.child("expedientes").child("IMG_${binding.tvExp.text}_Moca$photo")
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
        db.collection(getString(R.string.db_expedientes)).document(key)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas)).update(
                "Foto Moca$photo", url
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
    fun save(exp: String) {
        db.collection(getString(R.string.db_expedientes)).document(exp)
            .collection(getString(R.string.db_datos_interes))
            .document(getString(R.string.db_escalas))
            .update(
                getString(R.string.escala_moca), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}