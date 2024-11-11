package com.example.simplenoteapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.simplenoteapp.databinding.ActivityUpdatenoteBinding

class UpdatenoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdatenoteBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatenoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.updatetitleEditText.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updatetitleEditText.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Change saved", Toast.LENGTH_SHORT).show()
        }
    }
}