package course.intermediate.notes.notes


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import course.intermediate.notes.R
import course.intermediate.notes.models.Note
import course.intermediate.notes.tasks.TasksListFragment
import kotlinx.android.synthetic.main.fragment_notes_list.*

class NotesListFragment : Fragment() {

    lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteAdapter(
            mutableListOf(
                Note("pi is not exactly 3.14"),
                Note("A double double is Canadian for coffee two cream two sugar")
            ),
            touchActionDelegate
        )
        recyclerView.adapter = adapter

    }

    companion object {

        fun newInstance() = NotesListFragment()
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }

}
