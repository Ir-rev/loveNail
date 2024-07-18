package ru.pervov.client_create_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.pervov.lovenail.client_create_screen.databinding.ItemNameInputBinding
import ru.pervov.lovenail.client_create_screen.databinding.ItemPhoneInputBinding
import ru.pervov.lovenail.client_create_screen.databinding.ItemPriceInputBinding
import ru.pervov.lovenail.client_create_screen.databinding.ItemWearTimeInputBinding

private enum class ClientCreateViewType(val type: Int) {
    PHONE_INPUT(0),
    NAME(1),
    WEAR_TIME(2),
    PRICE(3),
}

class ClientCreateAdapter(
    private val clientCreateItemList: List<CreateClientItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ClientCreateViewType.PHONE_INPUT.type -> {
                PhoneNumberHolder(
                    ItemPhoneInputBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            ClientCreateViewType.NAME.type -> {
                NameInputHolder(
                    ItemNameInputBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            ClientCreateViewType.WEAR_TIME.type -> {
                WearTimeInputHolder(
                    ItemWearTimeInputBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            ClientCreateViewType.PRICE.type -> {
                PriceInputHolder(
                    ItemPriceInputBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> throw IllegalStateException("ClientCreateViewType не содержит нужной верстки")
        }
    }

    override fun getItemCount(): Int {
        return clientCreateItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is PhoneNumberHolder -> {}
            is NameInputHolder -> {}
            is WearTimeInputHolder -> {}
            is PriceInputHolder -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (clientCreateItemList[position]) {
            is CreateClientItem.PhoneInput -> ClientCreateViewType.PHONE_INPUT.type
            is CreateClientItem.Name -> ClientCreateViewType.NAME.type
            is CreateClientItem.WearTime -> ClientCreateViewType.WEAR_TIME.type
            is CreateClientItem.Price -> ClientCreateViewType.PRICE.type
        }
    }

    class PhoneNumberHolder(
        binding: ItemPhoneInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class NameInputHolder(
        binding: ItemNameInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class WearTimeInputHolder(
        binding: ItemWearTimeInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class PriceInputHolder(
        binding: ItemPriceInputBinding
    ) : RecyclerView.ViewHolder(binding.root)
}