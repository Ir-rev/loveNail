package ru.pervov.week_calendar_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.pervov.lovenail.week_calendar_screen.databinding.FragmentWeekCalendarBinding
import ru.pervov.week_calendar_screen.adapter.CalendarAdapter
import ru.pervov.week_calendar_screen.adapter.AMOUNT_PILLAR

class WeekCalendarFragment : Fragment() {

    private var binding: FragmentWeekCalendarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeekCalendarBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        binding.weakCalendarGridView.numColumns = AMOUNT_PILLAR

        val adapter = CalendarAdapter(emptyList())
        binding.weakCalendarGridView.adapter = adapter

    }
}