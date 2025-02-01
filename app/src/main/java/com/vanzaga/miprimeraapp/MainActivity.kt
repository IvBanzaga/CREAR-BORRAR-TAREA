package com.vanzaga.miprimeraapp

import android.app.ActivityManager.AppTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vanzaga.miprimeraapp.TaskApplication.Companion.prefs

class MainActivity : AppCompatActivity() {

    lateinit var btnAddTask: Button
    lateinit var etTask: EditText
    lateinit var rvTasks: RecyclerView
    lateinit var adapter: TaskAdapter

    var tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuramos la barra de herramientas
        setSupportActionBar(findViewById(R.id.my_toolbar))

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializamos la interfaz de usuario
        initUi()


    }

    // Esta función se encarga de inicializar la interfaz de usuario
    private fun initUi() {
        initView()
        initListeners()
        initRecyclerView()
    }

    // Esta función se encarga de inicializar el RecyclerView
    private fun initRecyclerView() {
        tasks = prefs.getTasks()
        rvTasks.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(tasks) {deleteTask(it)}
        rvTasks.adapter = adapter
    }

    // Esta función se encarga de eliminar una tarea de la lista de tareas y guardarla en las preferencias compartidas
    private fun deleteTask(position: Int) {
        tasks.removeAt(position)
        adapter.notifyDataSetChanged()
        prefs.saveTasks(tasks)
    }

    // Esta función se encarga de inicializar los listeners de los elementos de la vista
    private fun initListeners() {
        btnAddTask.setOnClickListener{
            addTask()
        }
    }

    // Esta función se encarga de agregar una tarea a la lista de tareas y guardarla en las preferencias compartidas
    private fun addTask() {
        val taskToAdd: String = etTask.text.toString()
        tasks.add(taskToAdd)
        adapter.notifyDataSetChanged()
        etTask.text.clear()
        prefs.saveTasks(tasks)
    }

    // Esta función se encarga de inicializar los elementos de la vista
    private fun initView() {
        btnAddTask = findViewById(R.id.btnAddTask)
        etTask = findViewById(R.id.etTask)
        rvTasks = findViewById(R.id.rvTasks)
    }

}