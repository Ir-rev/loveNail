package ru.pervov.lovenail.week_calendar_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.pervov.lovenail.week_calendar_screen.databinding.FragmentWeekCalendarBinding
import ru.pervov.lovenail.week_calendar_screen.adapter.AMOUNT_PILLAR
import ru.pervov.lovenail.week_calendar_screen.adapter.WeekCalendarAdapter
import ru.pervov.lovenail.week_calendar_screen.view_model.WeekCalendarViewModel
import ru.pervov.lovenail.week_calendar_screen.view_model.WeekCalendarViewModelFactory
import ru.pervov.lovenail.week_calendar_screen.view_model.WeekCalendarViewModelState

class WeekCalendarFragment : Fragment() {

    private var viewModel: WeekCalendarViewModel? = null
    private var binding: FragmentWeekCalendarBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            WeekCalendarViewModelFactory()
        )[WeekCalendarViewModel::class.java]
    }


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
        val viewModel = viewModel ?: return
        binding.weakCalendarGridView.numColumns = AMOUNT_PILLAR

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                setVisibleErrorScreen(false)
                setVisibleLoadingScreen(false)
                when(state) {
                    is WeekCalendarViewModelState.Error -> setVisibleErrorScreen(true)
                    is WeekCalendarViewModelState.Loading -> setVisibleLoadingScreen(true)
                    is WeekCalendarViewModelState.Success -> {
                        binding.weakCalendarGridView.adapter = WeekCalendarAdapter(
                            state.eventList
                        )
                    }
                }
            }
        }
    }

    private fun setVisibleErrorScreen(isVisible: Boolean) {
        val binding = binding ?: return
        binding.containerError.isVisible = isVisible
    }

    private fun setVisibleLoadingScreen(isVisible: Boolean) {
        val binding = binding ?: return
        binding.containerError.isVisible = isVisible
    }
}