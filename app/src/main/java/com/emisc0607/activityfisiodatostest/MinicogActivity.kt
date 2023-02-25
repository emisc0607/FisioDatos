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
import com.emisc0607.activityfisiodatostest.databinding.ActivityMinicogBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.OutputStream
import java.util.*

class MinicogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMinicogBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var storageReference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMinicogBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showExp()
        binding.bRes.setOnClickListener { switches() }
        binding.bSave.setOnClickListener {
            if(binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(binding.root, "Presione RESULTADO antes de guardar", Snackbar.LENGTH_SHORT).show()
        }
        storageReference = FirebaseStorage.getInstance().reference
        getSnapshot(binding.tvExp.text.toString())
        cameraCode()
    }

    private fun switches() {
        val mic = Array(4) { 0 }
        if (binding.etP1.text.isEmpty() or binding.etP2.text.isEmpty() or binding.etP3.text.isEmpty()) {
            if (binding.etP1.text.isEmpty()) binding.etP1.error = "Requerido"
            if (binding.etP2.text.isEmpty()) binding.etP2.error = "Requerido"
            if (binding.etP3.text.isEmpty()) binding.etP3.error = "Requerido"
            Toast.makeText(this, "No deje ningun campo vacío", Toast.LENGTH_LONG).show()
        } else {
            if (binding.cb1.isChecked) mic[0] = 1
            if (binding.cb2.isChecked) mic[1] = 1
            if (binding.cb3.isChecked) mic[2] = 1
            if (binding.cb4.isChecked) mic[3] = 2
            val r = ContextCompat.getColor(this, R.color.red)
            val y = ContextCompat.getColor(this, R.color.yellow)
            val g = ContextCompat.getColor(this, R.color.green)
            if (mic.sum() in 0..2) {
                binding.tvRes.setBackgroundColor(r)
                binding.tvRes.text = "Probable deterioro cognitivo:\t${mic.sum()}"
            } else {
                binding.tvRes.setBackgroundColor(g)
                binding.tvRes.text = "Poco probable que haya deterioro cognitivo:\t${mic.sum()}"
            }
        }
    }

    private lateinit var file: File
    private fun cameraCode() {
        binding.bCamera1.setOnClickListener {
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
        }
    }

    //Camera
    private val openCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val bitmap = getBitmap()
                Glide.with(this)
                    .load(bitmap)
                    .centerCrop()
                    .into(binding.image1)
                //binding.image1.rotation = 90f
                saveToGallery()
            }
        }

    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${binding.tvExp.text}_Minicog", ".jpg", dir)
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
            val url1 = it.get("Foto Minicog") as String?
            if (url1 != null) {
                Glide.with(this)
                    .load(url1)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.image1)
            }
        }
    }
    private fun postSnapshot(uri: Uri) {
        val storage =
            storageReference.child("expedientes").child("IMG_${binding.tvExp.text}_Minicog")
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
                "Foto Minicog", url
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
                getString(R.string.escala_minicog), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}