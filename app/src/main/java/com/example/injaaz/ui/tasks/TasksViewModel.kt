package com.example.injaaz.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.injaaz.data.data_source.DataSource
import com.example.injaaz.data.model.Task

class TasksViewModel : ViewModel() {

    private val _taskList = MutableLiveData<ArrayList<Task>>()
    val taskList: LiveData<ArrayList<Task>> = _taskList

    init {
        _taskList.value = DataSource.tasks
    }

    fun addNewTask(task: Task, position: Int = -1) {
        if (position != -1) {
            _taskList.value?.add(position, task)
        } else {
            _taskList.value?.add(task)
        }
    }

    fun getTaskAtPosition(position: Int): Task? {
        return _taskList.value?.get(position)
    }

    fun deleteTaskAtPosition(position: Int) {
        _taskList.value?.removeAt(position)
    }
}