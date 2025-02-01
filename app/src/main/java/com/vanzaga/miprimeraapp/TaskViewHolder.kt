package com.vanzaga.miprimeraapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Esta clase se encarga de representar la vista de un elemento de la lista de tareas

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val ivTaskDone: ImageView = view.findViewById(R.id.ivTaskDone)

    fun render(task: String, onItemDone: (Int) -> Unit) {
        tvTask.text = task
        ivTaskDone.setOnClickListener {onItemDone(adapterPosition)}
    }
}