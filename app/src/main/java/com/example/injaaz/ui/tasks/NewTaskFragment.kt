package com.example.injaaz.ui.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.injaaz.R
import com.example.injaaz.data.model.Task
import com.example.injaaz.databinding.FragmentNewTaskBinding


/**
 * A simple [Fragment] subclass.
 */
class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private val sharedTasksViewModel: TasksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewTaskBinding.inflate(layoutInflater)

        binding.DiscardButton.setOnClickListener {
            findNavController().navigate(R.id.action_newTaskFragment_to_navigation_tasks)
        }

        binding.saveButton.setOnClickListener {
            val taskName = binding.newTaskNameLayoutEditText.text.toString()
            sharedTasksViewModel.addNewTask(Task(taskName, false))
            findNavController().navigate(R.id.action_newTaskFragment_to_navigation_tasks)
        }


        return binding.root
    }
}