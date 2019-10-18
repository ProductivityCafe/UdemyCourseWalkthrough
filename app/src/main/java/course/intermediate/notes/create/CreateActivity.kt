package course.intermediate.notes.create

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import course.intermediate.notes.R
import course.intermediate.notes.foundations.CreateActivityScope
import course.intermediate.notes.navigation.NavigationActivity
import toothpick.Toothpick
import javax.inject.Inject

class CreateActivity : AppCompatActivity(), CreateNoteFragment.OnFragmentInteractionListener, CreateTaskFragment.OnFragmentInteractionListener {

    var saveEnabled: Boolean = false

    @Inject
    lateinit var stateModel: StateModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        Toothpick.inject(this, CreateActivityScope.scope)

        supportActionBar?.title = ""

        intent?.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY).run {

            if (this == NavigationActivity.FRAGMENT_VALUE_TASK) {
                createFragment(CreateTaskFragment.newInstance())
            } else if (this == NavigationActivity.FRAGMENT_VALUE_NOTE) {
                createFragment(CreateNoteFragment.newInstance())
            }
        }

        stateModel._isEnabledLiveData.observe(this, Observer {
            saveEnabled = it
            invalidateOptionsMenu()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        menu?.findItem(R.id.saveItem)?.isEnabled = saveEnabled
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {

            R.id.saveItem -> {

                supportFragmentManager.findFragmentById(R.id.fragmentHolder)?.run {

                    if (this is CreateTaskFragment) {
                        this.saveTask { success ->
                            if (success) {
                                this@CreateActivity.supportFinishAfterTransition()
                            } else {
                                Toast.makeText(this@CreateActivity, getString(R.string.toast_error_saving), Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else if (this is CreateNoteFragment) {
                        this.saveNote { success ->
                            if (success) {
                                this@CreateActivity.supportFinishAfterTransition()
                            } else {
                                Toast.makeText(this@CreateActivity, getString(R.string.toast_error_saving), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun createFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }

    override fun onFragmentInteraction() {

    }
}
