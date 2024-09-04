package ru.pervov.lovenail.procedure_create_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.pervov.lovenail.procedure_create_screen.databinding.FragmentProcedureCreateBinding
import ru.pervov.lovenail.procedure_create_screen.adapter.ProcedureCreateAdapter
import ru.pervov.lovenail.procedure_create_screen.view_model.ProcedureCreateAction
import ru.pervov.lovenail.procedure_create_screen.view_model.ProcedureCreateViewModel
import ru.pervov.lovenail.procedure_create_screen.view_model.ProcedureListViewModelFactory

const val PROCEDURE_ID = "PROCEDURE_ID"

class ProcedureCreateFragment : Fragment() {

    private var viewModel: ProcedureCreateViewModel? = null
    private var binding: FragmentProcedureCreateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ProcedureListViewModelFactory(arguments?.getString(PROCEDURE_ID))
        )[ProcedureCreateViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProcedureCreateBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val viewModel = viewModel ?: return

        binding.procedureCreateRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel.state.collect {
                binding.procedureCreateRecyclerView.adapter = ProcedureCreateAdapter(it)
            }
        }
        lifecycleScope.launch {
            viewModel.action.collect { action ->
                when (action) {
                    is ProcedureCreateAction.ShowToast -> {
                        context?.let { context ->
                            Toast.makeText(
                                context,
                                action.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    is ProcedureCreateAction.ProcedureCreatedOrUpdated -> activity?.onBackPressed()
                }
            }
        }
        binding.buttonCreateProcedure.setOnClickListener {
            viewModel.createClient()
        }
    }
}