package com.vanzaga.miprimeraapp

import android.content.Context
import android.content.SharedPreferences

class Preferences(contex: Context) {

    // Constantes
    companion object {

        // Nombre de las preferencias compartidas
        const val PREFS_NAME = "myDatabase"
        const val TASKS = "tasks_value"
    }

    // Obtenemos las preferencias compartidas
    val prefs: SharedPreferences = contex.getSharedPreferences(PREFS_NAME, 0)

    // Esta función se encarga de guardar las tareas en las preferencias compartidas
    fun saveTasks (tasks: List<String>) {
        prefs.edit().putStringSet(TASKS, tasks.toSet()).apply()
    }

    // Esta función se encarga de obtener las tareas guardadas en las preferencias compartidas
    fun getTasks(): MutableList<String> {
        return prefs.getStringSet(TASKS, emptySet<String>())!!.toMutableList() ?: mutableListOf()
    }

}