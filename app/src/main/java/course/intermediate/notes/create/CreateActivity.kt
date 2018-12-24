package course.intermediate.notes.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import course.intermediate.notes.R
import course.intermediate.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        intent?.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY).run {

            textView.text = if (this == NavigationActivity.FRAGMENT_VALUE_TASK) {
                "this is a task!"
            } else if (this == NavigationActivity.FRAGMENT_VALUE_NOTE) {
                "this is a note!"
            } else {
                "something went wrong!"
            }

        }
    }
}
