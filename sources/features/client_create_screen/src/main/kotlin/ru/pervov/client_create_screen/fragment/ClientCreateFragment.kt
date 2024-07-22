package ru.pervov.client_create_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.pervov.client_create_screen.adapter.ClientCreateAdapter
import ru.pervov.client_create_screen.view_model.ClientCreateAction
import ru.pervov.client_create_screen.view_model.ClientCreateViewModel
import ru.pervov.client_create_screen.view_model.ClientListViewModelFactory
import ru.pervov.lovenail.client_create_screen.databinding.FragmentClientCreateBinding

const val CLIENT_ID = "CLIENT_ID"

class ClientCreateFragment : Fragment() {

    private var viewModel: ClientCreateViewModel? = null
    private var binding: FragmentClientCreateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ClientListViewModelFactory(arguments?.getString(CLIENT_ID))
        )[ClientCreateViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentClientCreateBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val viewModel = viewModel ?: return

        binding.clientCreateRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel.state.collect {
                binding.clientCreateRecyclerView.adapter = ClientCreateAdapter(it)
            }
        }
        lifecycleScope.launch {
            viewModel.action.collect { action ->
                when (action) {
                    is ClientCreateAction.ShowToast -> {
                        context?.let { context ->
                            Toast.makeText(
                                context,
                                action.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    is ClientCreateAction.ClientCreatedOrUpdate -> activity?.onBackPressed()
                }
            }
        }
        binding.buttonCreateClient.setOnClickListener {
            viewModel.createClient()
        }
    }
}