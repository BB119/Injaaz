package com.example.injaaz.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.injaaz.R
import com.example.injaaz.databinding.FragmentTasksBinding


class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val sharedTasksViewModel: TasksViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskAdapter()
        binding.apply {


            tasksRecyclerView.adapter = adapter
            val linearLayout = LinearLayoutManager(this@TasksFragment.context)
            tasksRecyclerView.layoutManager = linearLayout
            // Add a divider between the views
            /*val dividerItemDecoration = DividerItemDecoration(this@TasksFragment.context, linearLayout.orientation)
            tasksRecyclerView.addItemDecoration(dividerItemDecoration)*/

            sharedTasksViewModel.taskList.observe(this@TasksFragment.viewLifecycleOwner) { items ->
                items.let {
                    adapter.submitList(it)
                }
            }

            newTaskFab.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_tasks_to_newTaskFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}