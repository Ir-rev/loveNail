package ru.pervov.lovenail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import ru.pervov.lovenail.adapter.ClientListAdapter
import ru.pervov.lovenail.client_list_screen.R
import ru.pervov.lovenail.client_list_screen.databinding.FragmentClientListBinding
import ru.pervov.lovenail.view_model.ClientListViewModel
import ru.pervov.lovenail.view_model.ClientListViewModelFactory
import ru.pervov.lovenail.view_model.ClientListViewModelState
import ru.pervov.utils.NavigationAction
import ru.pervov.utils.NavigationHolder

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

        Glide.with(this)
            .load(R.drawable.gif_loading)
            .into(binding.imageViewLoading)

        binding.clientListRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                setVisibleErrorScreen(false)
                setVisibleLoadingScreen(false)
                when (state) {
                    is ClientListViewModelState.Error -> {
                        setVisibleErrorScreen(true)
                    }

                    is ClientListViewModelState.Loading -> {
                        setVisibleLoadingScreen(true)
                    }

                    is ClientListViewModelState.Success -> {
                        binding.clientListRecyclerView.adapter = ClientListAdapter(state.clientList)
                    }
                }
            }
        }
        binding.addClientImageView.setOnClickListener {
            (activity as? NavigationHolder)?.doNavigation(NavigationAction.OpenCreateClient())
        }
    }

    private fun setVisibleErrorScreen(isVisible: Boolean) {
        val binding = binding ?: return
        binding.containerError.isVisible = isVisible
    }

    private fun setVisibleLoadingScreen(isVisible: Boolean) {
        val binding = binding ?: return
        binding.containerError.isVisible = isVisible
    }
}