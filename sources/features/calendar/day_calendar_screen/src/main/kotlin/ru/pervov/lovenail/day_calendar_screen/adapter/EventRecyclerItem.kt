package ru.pervov.lovenail.day_calendar_screen.adapter

import ru.pervov.lovenail.calendar_api.models.Event

sealed class EventRecyclerItem {

    class EmptyListItem() : EventRecyclerItem()
    class EventItem(val event: Event) : EventRecyclerItem()

}