package ru.beryukhov.kotliniosandroid.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.date_item.view.*
import ru.beryukhov.kotliniosandroid.R
import ru.beryukhov.kotliniosandroid.baserecyclerview.IBaseListItem

/**
 * Created by Andrey Beryukhov
 */
data class DateItem(val date: String?, val startTime: String?, val endTime: String?, val duration: String) : IBaseListItem {
    override fun getLayoutId(): Int {
        return R.layout.date_item
    }

}

class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val date = view.date_tv
    val startTime = view.start_time_tv
    val endTime = view.end_time_tv
    val duration = view.duration_tv
}