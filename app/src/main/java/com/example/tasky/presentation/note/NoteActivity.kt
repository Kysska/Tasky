package com.example.tasky.presentation.note

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tasky.R
import com.example.tasky.data.Note
import com.example.tasky.data.db.NoteDatabase
import com.example.tasky.databinding.ActivityMainBinding
import java.io.Serializable

class NoteActivity : AppCompatActivity(),  NoteListAdapter.NoteClickListener, PopupMenu.OnMenuItemClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: NoteDatabase
    private lateinit var vm: NoteViewModel
    lateinit var adapter: NoteListAdapter
    lateinit var selectedNote: Note

    private val updateNote = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if(result.resultCode == Activity.RESULT_OK){
            val note = result.data?.serializable("note") as? Note
            if(note != null){
                vm.updateNote(note)
            }
        }

    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            initUI()
            vm = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

            vm.allnotes.observe(this){list ->
               list?.let{
                   adapter.updateList(list)
               }
            }

            database = NoteDatabase.getDatabase(this)
        }

    inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializable(key) as? T
    }

    inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }

    private fun initUI(){
        binding.recyclerViewNote.setHasFixedSize(true)
        binding.recyclerViewNote.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        adapter = NoteListAdapter(this, this)
        binding.recyclerViewNote.adapter = adapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->

            if(result.resultCode == Activity.RESULT_OK){
                val note = result.data?.serializable("note") as? Note
                if(note != null){
                    vm.insertNote(note)
                }
            }

        }
        binding.fbNote.setOnClickListener{
            val intent = Intent(this, AddNote::class.java)
            getContent.launch(intent)
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    adapter.filterList(newText)
                }
                return true

            }

        })

    }

    override fun onItemClicked(note: Note) {
        val intent = Intent(this@NoteActivity, AddNote::class.java)
        intent.putExtra("current_note", note)
        updateNote.launch(intent)

    }

    override fun onLongItemClicked(note: Note, cardView: CardView) {
        selectedNote = note
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {
        val popup = PopupMenu(this, cardView)
        popup.setOnMenuItemClickListener(this@NoteActivity)
        popup.inflate(R.menu.pop_up_menu_note)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.delete_note){
            vm.deleteNote(selectedNote)
            return true
        }
        return false
    }
}

