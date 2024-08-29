package ru.pervov.week_calendar_screen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ru.pervov.calendar_api.models.Event
import ru.pervov.lovenail.week_calendar_screen.databinding.ItemCalendarEmptyBinding

internal const val AMOUNT_PILLAR = 7
internal const val AMOUNT_LINES = 24
internal const val ALL_CELL_CALENDAR = AMOUNT_PILLAR * AMOUNT_LINES

class CalendarAdapter(
    private val list: List<Event>
) : BaseAdapter() {

    private val cellList = mutableListOf<List<String>>()

    init {

    }

    override fun getCount(): Int {
        return ALL_CELL_CALENDAR
    }

    override fun getItem(position: Int): Any = Unit

    override fun getItemId(position: Int): Long = -1

    private fun getRealPosition(position: Int): Int {
        val currentPillar = position / AMOUNT_PILLAR
        val currentLine = position % AMOUNT_PILLAR
        return (currentPillar + currentLine * AMOUNT_LINES)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return ItemCalendarEmptyBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            .apply {
                Log.d("checkResult", "getView: $position ${(getRealPosition(position))}")
                textView.text = (getRealPosition(position) + 1).toString()
            }.root
    }
}