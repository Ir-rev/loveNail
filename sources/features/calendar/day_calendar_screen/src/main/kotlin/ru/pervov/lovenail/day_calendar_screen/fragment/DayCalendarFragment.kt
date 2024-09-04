package ru.pervov.lovenail.day_calendar_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import ru.pervov.lovenail.day_calendar_screen.R
import ru.pervov.lovenail.day_calendar_screen.adapter.DayCalendarAdapter
import ru.pervov.lovenail.day_calendar_screen.databinding.FragmentDayCalendarBinding
import ru.pervov.lovenail.day_calendar_screen.view_model.DayCalendarViewModel
import ru.pervov.lovenail.day_calendar_screen.view_model.DayCalendarViewModelFactory
import ru.pervov.lovenail.day_calendar_screen.view_model.DayCalendarViewModelState

class DayCalendarFragment : Fragment() {

    private var viewModel: DayCalendarViewModel? = null
    private var binding: FragmentDayCalendarBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            DayCalendarViewModelFactory()
        )[DayCalendarViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDayCalendarBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val viewModel = viewModel ?: return

        Glide.with(this)
            .load(R.drawable.gif_loading)
            .into(binding.imageViewLoading)

        binding.eventListRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                setVisibleErrorScreen(false)
                setVisibleLoadingScreen(false)
                when (state) {
                    is DayCalendarViewModelState.Error -> setVisibleErrorScreen(true)
                    is DayCalendarViewModelState.Loading -> setVisibleLoadingScreen(true)
                    is DayCalendarViewModelState.Success -> {
                        binding.eventListRecyclerView.adapter =
                            DayCalendarAdapter(state.eventList) {
                                Toast.makeText(requireContext(), "жмяк жмяк", Toast.LENGTH_SHORT).show()
                            }
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