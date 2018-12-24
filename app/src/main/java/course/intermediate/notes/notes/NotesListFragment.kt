package course.intermediate.notes.notes


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import course.intermediate.notes.R
import course.intermediate.notes.models.Note
import course.intermediate.notes.tasks.TasksListFragment
import kotlinx.android.synthetic.main.fragment_notes_list.*

class NotesListFragment : Fragment() {

    lateinit var viewModel: NoteViewModel
    lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            if (it is NotesListFragment.TouchActionDelegate) {
                touchActionDelegate = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteAdapter(viewModel.getFakeData(), touchActionDelegate)
        recyclerView.adapter = adapter

    }

    private fun bindViewModel() {
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    companion object {

        fun newInstance() = NotesListFragment()
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }

}
