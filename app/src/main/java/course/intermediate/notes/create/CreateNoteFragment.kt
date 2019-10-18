package course.intermediate.notes.create

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import course.intermediate.notes.R
import course.intermediate.notes.foundations.CreateActivityScope
import course.intermediate.notes.foundations.NullFieldChecker
import course.intermediate.notes.models.Note
import course.intermediate.notes.notes.INoteModel
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class CreateNoteFragment : Fragment(), NullFieldChecker {

    @Inject
    lateinit var model: INoteModel

    @Inject
    lateinit var stateModel: StateModel

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toothpick.inject(this, CreateActivityScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("Mickey", "text change")
                stateModel.updateState(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }

    fun saveNote(callback: (Boolean) -> Unit) {
        GlobalScope.launch {
            createNote()?.let { note ->
                model.addNote(note) { success ->
                    callback.invoke(success)
                }
            } ?: callback.invoke(false)
        }
    }

    private fun createNote(): Note? = if (!hasNullField()) {
        Note(description = noteEditText.editableText.toString())
    } else {
        null
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()

    companion object {
        fun newInstance() = CreateNoteFragment()
    }
}
