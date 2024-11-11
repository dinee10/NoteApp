package com.example.simplenoteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplenoteapp.databinding.ActivityReadnoteBinding

class ReadnoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadnoteBinding
    private lateinit var db: NoteDatabaseHelper
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadnoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteAdapter = NoteAdapter(db.getAllNotes(), this)

        binding.notesRecycleView.layoutManager = LinearLayoutManager(this)
        binding.notesRecycleView.adapter = noteAdapter

        binding.addButton1.setOnClickListener {
            val intent = Intent(this, AddnoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.refreshData(db.getAllNotes())
    }
}