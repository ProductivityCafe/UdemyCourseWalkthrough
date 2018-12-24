package course.intermediate.notes.tasks


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import course.intermediate.notes.R

class TasksListFragment : Fragment() {

    lateinit var viewModel: TaskViewModel
    lateinit var contentView: TaskListView
    lateinit var touchActionDelegate: TouchActionDelegate

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            if (it is TouchActionDelegate) {
                touchActionDelegate = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_list, container, false).apply {
            contentView = this as TaskListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setContentView()
    }

    private fun setContentView() {
        contentView.initView(touchActionDelegate, viewModel)
    }

    private fun bindViewModel() {
        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        viewModel.taskListLiveData.observe(this, Observer { taskList ->
            contentView.updateList(taskList)
        })
    }

    companion object {
        fun newInstance() = TasksListFragment()
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }

}
