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

    fun addNewTask(task: Task) {
        _taskList.value?.add(task)
    }
}