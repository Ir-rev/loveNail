package ru.pervov.week_calendar_screen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ru.pervov.lovenail.week_calendar_screen.databinding.ItemCalendarEmptyBinding
import ru.pervov.week_calendar_screen.models.CalendarItem

internal const val AMOUNT_PILLAR = 7
internal const val AMOUNT_LINES = 24

class CalendarAdapter(private val list: List<CalendarItem.Event>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[getRealPosition(position)]
    }

    override fun getItemId(position: Int): Long {
        return list[getRealPosition(position)].id
    }

    private fun getRealPosition(position: Int): Int {
        val currentPillar = position / AMOUNT_PILLAR
        val currentLine = position % AMOUNT_PILLAR
        return (currentPillar + currentLine * AMOUNT_LINES)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return ItemCalendarEmptyBinding.inflate(LayoutInflater.from(parent?.context), parent, false).apply {
            Log.d("checkResult", "getView: $position ${(getRealPosition(position))}")
            textView.text = list[getRealPosition(position)].id.toString()
        }.root
    }
}