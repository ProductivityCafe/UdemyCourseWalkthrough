package course.intermediate.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T : Any>(
    protected val masterList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(list: List<T>) {
        masterList.clear()
        masterList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) {
        TYPE_ADD_BUTTON
    } else {
        TYPE_INFO
    }

    override fun getItemCount(): Int = masterList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder) {
            holder.onBind(Unit)
        } else {
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1])
        }
    }

    abstract class BaseViewHolder<E>(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(data: E)
    }

    abstract class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)

    companion object {

        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1

    }
}