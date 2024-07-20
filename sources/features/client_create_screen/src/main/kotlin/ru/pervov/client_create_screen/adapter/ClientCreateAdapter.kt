package ru.pervov.client_create_screen.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.vicmikhailau.maskededittext.doAfterMaskedTextChanged
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
        when (holder) {
            is PhoneNumberHolder -> {
                val item = clientCreateItemList[position] as? CreateClientItem.PhoneInput ?: return
                val editTextPhoneNumber = holder.binding.editTextPhoneNumber
                item.phoneNumber?.let { editTextPhoneNumber.setText(it) }
                editTextPhoneNumber.doAfterMaskedTextChanged {
                    item.phoneNumber = it.toString()
                }

            }

            is NameInputHolder -> {
                val item = clientCreateItemList[position] as? CreateClientItem.NameInput ?: return
                val editTextName = holder.binding.editTextName

                item.name?.let { editTextName.setText(it) }
                editTextName.doOnTextChanged { text, _, _, _ ->
                    item.name = text.toString()
                }

            }

            is WearTimeInputHolder -> {
                val item =
                    clientCreateItemList[position] as? CreateClientItem.WearTimeInput ?: return
                val editTextWearTime = holder.binding.editTextWearTime

                item.wearTime?.let { editTextWearTime.setText(it.toString()) }
                editTextWearTime.doOnTextChanged { text, _, _, _ ->
                    item.wearTime = text.toString().toIntOrNull()
                }

            }

            is PriceInputHolder -> {
                val item = clientCreateItemList[position] as? CreateClientItem.PriceInput ?: return
                val editTextPrice = holder.binding.editTextPrice

                item.price?.let { editTextPrice.setText(it.toString()) }
                editTextPrice.doOnTextChanged { text, _, _, _ ->
                    item.price = text.toString().toIntOrNull()
                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (clientCreateItemList[position]) {
            is CreateClientItem.PhoneInput -> ClientCreateViewType.PHONE_INPUT.type
            is CreateClientItem.NameInput -> ClientCreateViewType.NAME.type
            is CreateClientItem.WearTimeInput -> ClientCreateViewType.WEAR_TIME.type
            is CreateClientItem.PriceInput -> ClientCreateViewType.PRICE.type
        }
    }

    class PhoneNumberHolder(
        val binding: ItemPhoneInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class NameInputHolder(
        val binding: ItemNameInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class WearTimeInputHolder(
        val binding: ItemWearTimeInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class PriceInputHolder(
        val binding: ItemPriceInputBinding
    ) : RecyclerView.ViewHolder(binding.root)
}