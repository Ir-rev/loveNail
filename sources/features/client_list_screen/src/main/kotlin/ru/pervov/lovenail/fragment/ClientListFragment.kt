package ru.pervov.lovenail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.pervov.lovenail.adapter.ClientListAdapter
import ru.pervov.lovenail.client_list_screen.databinding.FragmentClientListBinding
import ru.pervov.lovenail.view_model.ClientListViewModel
import ru.pervov.lovenail.view_model.ClientListViewModelFactory
import ru.pervov.lovenail.view_model.ClientListViewModelState

class ClientListFragment : Fragment() {

    private var viewModel: ClientListViewModel? = null
    private var binding: FragmentClientListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ClientListViewModelFactory()
        )[ClientListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentClientListBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val viewModel = viewModel ?: return

        binding.clientListRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is ClientListViewModelState.Error -> {
                        // todo
                    }

                    is ClientListViewModelState.Loading -> {
                        // todo
                    }

                    is ClientListViewModelState.Success -> {
                        binding.clientListRecyclerView.adapter = ClientListAdapter(state.clientList)
                    }
                }
            }
        }
        binding.addClientImageView.setOnClickListener {

        }
    }
}