package com.example.schachklubbingen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val database = Firebase.firestore

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var allAppointments = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.mainActivity = this

        viewManager = GridLayoutManager(this, 3)
        viewAdapter = CustomAdapter(allAppointments)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            layoutManager = viewManager
            adapter = viewAdapter

        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.chessclub_bingen)
        toolbar.inflateMenu(R.menu.toolbar_menu)

        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            if (item.itemId == R.id.reload) {
                readData()
            }
            return@OnMenuItemClickListener true
        })

        readData()

        update_data_button.setOnClickListener {
            storeAppointments()
        }
    }

    private fun readData() {
        database.collection(getString(R.string.appointments))
            .get()
            .addOnSuccessListener { result ->
                allAppointments.clear()
                viewAdapter.notifyDataSetChanged()

                for (document in result) {
                    allAppointments.add(document.id + " - " + document.data.toString())
                }

                viewAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }

    private fun storeAppointments() {
        val dateInput = "Donnerstag"
        val topicInput = "irgendwas komisches, das schon was lustiges an sich hat"

        val appointment = hashMapOf(
            dateInput to topicInput
        )

        database.collection(getString(R.string.appointments))
            .add(appointment)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

    public fun showEventEditingDialog() {
        var exampleDialog = ExampleDialog()
        exampleDialog.show(supportFragmentManager, "Dialog")
    }
}