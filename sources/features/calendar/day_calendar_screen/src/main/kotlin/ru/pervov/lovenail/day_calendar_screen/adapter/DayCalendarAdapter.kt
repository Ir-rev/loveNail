package ru.pervov.lovenail.day_calendar_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.pervov.lovenail.date_utils.DateWorkHelper
import ru.pervov.lovenail.day_calendar_screen.databinding.ItemEmptyEventListBinding
import ru.pervov.lovenail.day_calendar_screen.databinding.ItemEventBinding
import ru.pervov.lovenail.day_calendar_screen.databinding.ItemFillerBinding
import java.time.LocalDateTime

private const val EMPTY_ITEM = 0
private const val EVENT_ITEM = 1
private const val FILLER_ITEM = 2

class DayCalendarAdapter(
    private val eventList: List<EventRecyclerItem>,
    private val onEventClick: (id: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dateWorkHelper = DateWorkHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EVENT_ITEM -> {
                EventItemHolder(
                    ItemEventBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            FILLER_ITEM -> {
                FillerHolder(
                    ItemFillerBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            else -> {
                EventListEmptyHolder(
                    ItemEmptyEventListBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (eventList[position]) {
            is EventRecyclerItem.EventItem -> EVENT_ITEM
            is EventRecyclerItem.EmptyListItem -> EMPTY_ITEM
            is EventRecyclerItem.FillerItem -> FILLER_ITEM
        }
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EventItemHolder -> {
                val item =
                    (eventList[position] as? EventRecyclerItem.EventItem)?.event ?: return
                holder.descriptionTextView.text = item.event.description
                holder.timeWorkTextView.text = item.procedure.workTimeInMinutes.toString()
                holder.clientNameTextView.text = item.client.name
                holder.procedureNameTextView.text = item.procedure.name
                holder.root.setOnClickListener {
                    onEventClick(item.event.id)
                }
                holder.startWorkTimeTextView.text =
                    dateWorkHelper.getHoursAndMinutesFromEpochSecond(item.event.dateStart)
            }
            is FillerHolder -> {
                val time =
                    (eventList[position] as? EventRecyclerItem.FillerItem)?.time ?: return
                holder.startWorkTimeTextView.text = time
            }
        }
    }

    private class EventListEmptyHolder(
        binding: ItemEmptyEventListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private class FillerHolder(
        binding: ItemFillerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val startWorkTimeTextView = binding.startWorkTimeTextView
    }

    private class EventItemHolder(
        binding: ItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.rootContainer

        val descriptionTextView = binding.descriptionTextView
        val clientNameTextView = binding.clientNameTextView
        val procedureNameTextView = binding.procedureNameTextView
        val timeWorkTextView = binding.timeWorkTextView
        val startWorkTimeTextView = binding.startWorkTimeTextView
    }

}