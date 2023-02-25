package com.emisc0607.activityfisiodatostest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.emisc0607.activityfisiodatostest.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    val PDF_SELECTION_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //showExp()
        showDate()
        binding.pdfView
        selectPdfFromStorage()
        binding.bShare.setOnClickListener{
            share(binding.tvExp.text.toString())
        }
    }

    private fun showDate() {
        val bundle=intent.extras
        val date = bundle?.get("DATE")
        binding.tvDate.text="$date"
    }

    private fun selectPdfFromStorage() {
        Toast.makeText(this, "Select PDF file", Toast.LENGTH_SHORT).show()
        val browseStorage = Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type= "application/*"
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(Intent.createChooser(browseStorage, "Select Pdf"), PDF_SELECTION_CODE)
    }
fun showPdfFromUri(uri: Uri){
    binding.pdfView.fromUri(uri)
        .defaultPage(0)
        .spacing(10)
        .load()
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PDF_SELECTION_CODE && resultCode == RESULT_OK && data != null){
           val selectedPDF = data.data
            if (selectedPDF != null) {
                showPdfFromUri(selectedPDF)
            }
        }
    }
    fun showExp(){
        val bundle=intent.extras
        val exp = bundle?.get("EXP")
        binding.tvExp.text="$exp"
    }
    private fun share(exp:String) {
        val shareIntent = Intent().apply {
            action=Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, "${exp}.pdf")
            flags=Intent.FLAG_GRANT_READ_URI_PERMISSION
            type="application/pdf"
        }
        val sendIntent =  Intent.createChooser(shareIntent,null)
        startActivity(sendIntent)
    }
}
