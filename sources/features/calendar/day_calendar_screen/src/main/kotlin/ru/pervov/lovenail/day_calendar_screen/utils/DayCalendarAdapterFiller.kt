package ru.pervov.lovenail.day_calendar_screen.utils

import ru.pervov.lovenail.date_utils.DateWorkHelper
import ru.pervov.lovenail.day_calendar_screen.adapter.EventRecyclerItem

class DayCalendarAdapterFiller {

    private val dateWorkHelper = DateWorkHelper()

    fun fillEmptySpace(eventList: List<EventRecyclerItem>): List<EventRecyclerItem> {
        if (eventList.contains(EventRecyclerItem.EmptyListItem)) return eventList
        val resultList = mutableListOf<EventRecyclerItem>()
        val currentEventList = eventList.filterIsInstance<EventRecyclerItem.EventItem>()
        val eventTimeList = currentEventList.map {
            dateWorkHelper.getLocalDateTimeFromEpochSecond(it.event.event.dateStart)
        }
        val eventHoursList = eventTimeList.map {
            it.hour
        }
        for (time in 0 until 24) {
            if (eventHoursList.contains(time)) {
                resultList.addAll(currentEventList.filter {
                    time == dateWorkHelper.getLocalDateTimeFromEpochSecond(it.event.event.dateStart).hour
                })
            } else {
                resultList.add(EventRecyclerItem.FillerItem("$time:00"))
            }
        }
        return resultList
    }


}