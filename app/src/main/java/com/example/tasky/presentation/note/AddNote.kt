package com.example.tasky.presentation.note

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.style.StyleSpan
import android.widget.Toast
import com.example.tasky.data.Note
import com.example.tasky.databinding.ActivityAddNoteBinding
import com.example.tasky.utilites.HTMLManager
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note: Note
    private lateinit var old_note: Note
    var isUpdate = false

    inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializable(key) as? T
    }

    inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            old_note = intent.serializable<Note>("current_note") as Note
            binding.edTitle.setText(old_note.title)
            binding.edDesc.setText(HTMLManager.getFromHtml(old_note.desc!!).trim())
            isUpdate = true
        }catch (e: java.lang.Exception){
            e.printStackTrace()
        }
        binding.imgBold.setOnClickListener{
            setBoldForSelectedText()
        }
        binding.imgImg.setOnClickListener{

        }
        binding.imgCheck.setOnClickListener{
            val title = binding.edTitle.text.toString()
            val desc = binding.edDesc.text

            if(title.isNotEmpty() || desc.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                if(isUpdate){
                    note = Note(
                        old_note.id,title,desc = HTMLManager.toHtml(desc),formatter.format(Date())
                    )
                }
                else{
                    note = Note(
                        null, title, desc = HTMLManager.toHtml(desc), formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            else{
                Toast.makeText(this@AddNote, "Please enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setBoldForSelectedText() = with(binding) {
        val startPos = edDesc.selectionStart
        val endPos = edDesc.selectionEnd

        val style = edDesc.text.getSpans(startPos, endPos, StyleSpan::class.java)
        var boldStyle: StyleSpan? = null
        if(style.isNotEmpty()){
            edDesc.text.removeSpan(style[0])
        }
        else{
            boldStyle = StyleSpan(Typeface.BOLD)
        }
        edDesc.text.setSpan(boldStyle, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        edDesc.text.trim()
        edDesc.setSelection(startPos)
    }
}