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
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.emisc0607.activityfisiodatostest.databinding.ActivityMinimentalBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.OutputStream
import java.util.*

class MinimentalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMinimentalBinding
    private lateinit var storageReference: StorageReference
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMinimentalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        storageReference = FirebaseStorage.getInstance().reference
        showExp()
        binding.bRes.setOnClickListener { switches() }
        cameraCode()
        binding.bSave.setOnClickListener {
            if(binding.tvRes.text.isNotEmpty()) save(binding.tvExp.text.toString())
            else Snackbar.make(binding.root, "Presione RESULTADO antes de guardar", Snackbar.LENGTH_SHORT).show()
        }
        binding.etP1.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            var errorStr: String? = null
            if (!focused) {
                if (binding.etP1.text.isEmpty()) {
                    errorStr = "Required"
                    Toast.makeText(this, errorStr, Toast.LENGTH_SHORT).show()
                } else binding.cb24.text = binding.etP1.text.toString()
            }
        }
        binding.etP2.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            var errorStr: String? = null
            if (!focused) {
                if (binding.etP2.text.isEmpty()) {
                    errorStr = "Required"
                    Toast.makeText(this, errorStr, Toast.LENGTH_SHORT).show()
                } else binding.cb25.text = binding.etP2.text.toString()
            }
        }
        binding.etP3.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            var errorStr: String? = null
            if (!focused) {
                if (binding.etP3.text.isEmpty()) {
                    errorStr = "Required"
                    Toast.makeText(this, errorStr, Toast.LENGTH_SHORT).show()
                } else binding.cb26.text = binding.etP3.text.toString()
            }
        }

        getSnapshot(binding.tvExp.text.toString())

    }

    private fun switches() {
        val mim = Array(37) { 0 }
        if (binding.etNR.text.isEmpty() or binding.etP1.text.isEmpty()
            or binding.etP2.text.isEmpty()
            or binding.etP3.text.isEmpty()
        ) {
            if (binding.etNR.text.isEmpty()) binding.etNR.error = "Requerido"
            if (binding.etP1.text.isEmpty()) binding.etP1.error = "Requerido"
            if (binding.etP2.text.isEmpty()) binding.etP2.error = "Requerido"
            if (binding.etP3.text.isEmpty()) binding.etP3.error = "Requerido"

            Toast.makeText(this, "No deje ningun campo vacío", Toast.LENGTH_LONG).show()
        } else {
            if (binding.cb1.isChecked) mim[0] = 1
            if (binding.cb2.isChecked) mim[1] = 1
            if (binding.cb3.isChecked) mim[2] = 1
            if (binding.cb4.isChecked) mim[3] = 1
            if (binding.cb5.isChecked) mim[4] = 1
            if (binding.cb6.isChecked) mim[5] = 1
            if (binding.cb7.isChecked) mim[6] = 1
            if (binding.cb8.isChecked) mim[7] = 1
            if (binding.cb9.isChecked) mim[8] = 1
            if (binding.cb10.isChecked) mim[9] = 1
            if (binding.cb11.isChecked) mim[10] = 1
            if (binding.cb12.isChecked) mim[11] = 1
            if (binding.cb13.isChecked) mim[12] = 1
            if (binding.cb14.isChecked) mim[13] = 1
            if (binding.cb15.isChecked) mim[14] = 1
            if (binding.cb16.isChecked) mim[15] = 1
            if (binding.cb17.isChecked) mim[16] = 1
            if (binding.cb18.isChecked) mim[17] = 1
            if (binding.cb19.isChecked) mim[18] = 1
            if (binding.cb20.isChecked) mim[19] = 1
            if (binding.cb21.isChecked) mim[20] = 1
            if (binding.cb22.isChecked) mim[21] = 1
            if (binding.cb23.isChecked) mim[22] = 1
            if (binding.cb24.isChecked) mim[23] = 1
            if (binding.cb25.isChecked) mim[24] = 1
            if (binding.cb26.isChecked) mim[25] = 1
//            if (binding.cb27.isChecked) mim[26]=1
//            if (binding.cb28.isChecked) mim[27]=1
            if (binding.cb29.isChecked) mim[28] = 3
            if (binding.cb30.isChecked) mim[29] = 1
            if (binding.cb31.isChecked) mim[30] = 3
//            if (binding.cb34.isChecked) mim[33]=1
            if (binding.cb35.isChecked) mim[34] = 2
            if (binding.cb36.isChecked) mim[35] = 1
            if (binding.cb37.isChecked) mim[36] = 1
            val r = ContextCompat.getColor(this, R.color.red)
            val y = ContextCompat.getColor(this, R.color.yellow)
            val g = ContextCompat.getColor(this, R.color.green)

            if (mim.sum() < 24) {
                binding.tvRes.setBackgroundColor(r)
                binding.tvRes.text = "Demencia:\t${mim.sum()}"
            } else {
                binding.tvRes.setBackgroundColor(g)
                binding.tvRes.text = "Sano:\t${mim.sum()}"
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
                    saveToGallery()
                } else if (photo == 2) {
                    Glide.with(this)
                        .load(bitmap)
                        .centerCrop()
                        .into(binding.image2)
                    saveToGallery()
                }
            }
        }

    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${binding.tvExp.text}_Minimental_", ".jpg", dir)
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
            val url1 = it.get("Foto Minimental1") as String?
            if (url1 != null) {
                Glide.with(this)
                    .load(url1)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.image1)
            }
            val url2 = it.get("Foto Minimental2") as String?
            if (url2 != null) {
                Glide.with(this)
                    .load(url2)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.image2)
            }
        }
    }
    private fun postSnapshot(uri: Uri) {
        val storage =
            storageReference.child("expedientes").child("IMG_${binding.tvExp.text}_Minimental$photo")
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
                "Foto Minimental$photo", url
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
                getString(R.string.escala_minimental), binding.tvRes.text.toString()
            )
        finish()
    }

    fun showExp() {
        val bundle = intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text = "$exp"
    }
}