package com.example.injaaz.ui.tasks

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.injaaz.R
import com.example.injaaz.data.model.Task
import com.example.injaaz.databinding.FragmentTasksBinding
import com.google.android.material.snackbar.Snackbar


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

        setupRecyclerView(binding)

        binding.newTaskFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_tasks_to_newTaskFragment)
        }

    }

    private fun setupRecyclerView(tasksBinding: FragmentTasksBinding) {
        val adapter = TaskAdapter()
        tasksBinding.apply {
            tasksRecyclerView.adapter = adapter
            val linearLayout = LinearLayoutManager(this@TasksFragment.context)
            tasksRecyclerView.layoutManager = linearLayout
            // Add a divider between the views
            /*val dividerItemDecoration = DividerItemDecoration(this@TasksFragment.context, linearLayout.orientation)
            tasksRecyclerView.addItemDecoration(dividerItemDecoration)*/

            // Observe the list of tasks
            sharedTasksViewModel.taskList.observe(this@TasksFragment.viewLifecycleOwner) { items ->
                items.let {
                    adapter.submitList(it)
                }
            }
        }

        // dragDirs parameter takes 0 (meaning false) because no drag and drop behavior is wanted
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            private val deleteIcon = ContextCompat.getDrawable(this@TasksFragment.requireContext(), R.drawable.ic_delete)
            private val intrinsicWidth = deleteIcon?.intrinsicWidth ?: 0
            private val intrinsicHeight = deleteIcon?.intrinsicHeight ?: 0
            private val background = ColorDrawable()
            private val backgroundColor = Color.parseColor("#f44336")

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // No dragging and dropping is happening
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val taskPosition = viewHolder.adapterPosition

                val taskToDelete = sharedTasksViewModel.getTaskAtPosition(taskPosition)
                sharedTasksViewModel.deleteTaskAtPosition(taskPosition)

                // Notify the adapter to update the UI
                binding.tasksRecyclerView.adapter?.notifyItemRemoved(viewHolder.adapterPosition)

                // Display a snackbar to cancel deleting the task
                Snackbar.make(binding.tasksRecyclerView, "Deleted Task", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        if (taskToDelete != null) {
                            sharedTasksViewModel.addNewTask(taskToDelete, taskPosition)
                            // Notify the adapter to update the UI
                            binding.tasksRecyclerView.adapter?.notifyItemInserted(taskPosition)
                        }
                    }.show()
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                     dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top

                // Draw the red delete background
                background.color = backgroundColor
                background.setBounds(
                    itemView.right + dX.toInt(),
                    itemView.top + viewHolder.itemView.paddingTop,
                    itemView.right,
                    itemView.bottom - viewHolder.itemView.paddingBottom
                )
                background.draw(c)

                // Calculate position of delete icon
                val iconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val iconMargin = (itemHeight - intrinsicHeight) / 2
                val iconLeft = itemView.right - iconMargin - intrinsicWidth
                val iconRight = itemView.right - iconMargin
                val iconBottom = iconTop + intrinsicHeight

                // Draw the delete icon
                deleteIcon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                deleteIcon?.draw(c)

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }).attachToRecyclerView(binding.tasksRecyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}