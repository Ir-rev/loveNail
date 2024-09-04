package ru.pervov.lovenail.procedure_create_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import ru.pervov.lovenail.procedure_create_screen.databinding.ItemDescriptionInputBinding
import ru.pervov.lovenail.procedure_create_screen.databinding.ItemPriceInputBinding
import ru.pervov.lovenail.procedure_create_screen.databinding.ItemProcedureNameInputBinding
import ru.pervov.lovenail.procedure_create_screen.databinding.ItemWorkTimeInputBinding

private enum class ProcedureCreateViewType(val type: Int) {
    NAME_PROCEDURE(0),
    DESCRIPTION(1),
    WORK_TIME(2),
    PRICE(3),
}

class ProcedureCreateAdapter(
    private val procedureCreateItemList: List<CreateProcedureItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ProcedureCreateViewType.NAME_PROCEDURE.type -> {
                NameProcedureHolder(
                    ItemProcedureNameInputBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ProcedureCreateViewType.DESCRIPTION.type -> {
                DescriptionInputHolder(
                    ItemDescriptionInputBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ProcedureCreateViewType.WORK_TIME.type -> {
                WorkTimeInputHolder(
                    ItemWorkTimeInputBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ProcedureCreateViewType.PRICE.type -> {
                PriceInputHolder(
                    ItemPriceInputBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> throw IllegalStateException("ProcedureCreateViewType не содержит нужной верстки")
        }
    }

    override fun getItemCount(): Int {
        return procedureCreateItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NameProcedureHolder -> {
                val item = procedureCreateItemList[position] as? CreateProcedureItem.NameProcedureInput
                    ?: return
                val editTextPhoneNumber = holder.binding.editTextName
                item.name?.let { editTextPhoneNumber.setText(it) }
                editTextPhoneNumber.doOnTextChanged { text, _, _, _ ->
                    item.name = text.toString()
                }

            }

            is DescriptionInputHolder -> {
                val item = procedureCreateItemList[position] as? CreateProcedureItem.DescriptionInput
                    ?: return
                val editTextName = holder.binding.editTextName

                item.description?.let { editTextName.setText(it) }
                editTextName.doOnTextChanged { text, _, _, _ ->
                    item.description = text.toString()
                }

            }

            is WorkTimeInputHolder -> {
                val item =
                    procedureCreateItemList[position] as? CreateProcedureItem.WorkTimeInput
                        ?: return
                val editTextWearTime = holder.binding.editTextWearTime

                item.workTime?.let { editTextWearTime.setText(it.toString()) }
                editTextWearTime.doOnTextChanged { text, _, _, _ ->
                    item.workTime = text.toString().toLongOrNull()
                }
            }

            is PriceInputHolder -> {
                val item = procedureCreateItemList[position] as? CreateProcedureItem.PriceInput
                    ?: return
                val editTextPrice = holder.binding.editTextPrice

                item.price?.let { editTextPrice.setText(it.toString()) }
                editTextPrice.doOnTextChanged { text, _, _, _ ->
                    item.price = text.toString().toIntOrNull()
                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (procedureCreateItemList[position]) {
            is CreateProcedureItem.NameProcedureInput -> ProcedureCreateViewType.NAME_PROCEDURE.type
            is CreateProcedureItem.DescriptionInput -> ProcedureCreateViewType.DESCRIPTION.type
            is CreateProcedureItem.WorkTimeInput -> ProcedureCreateViewType.WORK_TIME.type
            is CreateProcedureItem.PriceInput -> ProcedureCreateViewType.PRICE.type
        }
    }

    class NameProcedureHolder(
        val binding: ItemProcedureNameInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class DescriptionInputHolder(
        val binding: ItemDescriptionInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class WorkTimeInputHolder(
        val binding: ItemWorkTimeInputBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class PriceInputHolder(
        val binding: ItemPriceInputBinding
    ) : RecyclerView.ViewHolder(binding.root)
}