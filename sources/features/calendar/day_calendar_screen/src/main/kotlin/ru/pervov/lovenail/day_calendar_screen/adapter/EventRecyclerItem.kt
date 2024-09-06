package ru.pervov.lovenail.day_calendar_screen.adapter

import ru.pervov.lovenail.calendar_domain.model.EventDomain

sealed class EventRecyclerItem {

    class EmptyListItem() : EventRecyclerItem()
    class EventItem(val event: EventDomain) : EventRecyclerItem()

}