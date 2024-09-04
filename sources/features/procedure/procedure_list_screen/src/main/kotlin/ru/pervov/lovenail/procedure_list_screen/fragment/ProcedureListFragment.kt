package ru.pervov.lovenail.procedure_list_screen.fragment

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
import ru.pervov.lovenail.procedure_list_screen.adapter.ProcedureListAdapter
import ru.pervov.lovenail.procedure_list_screen.R
import ru.pervov.lovenail.procedure_list_screen.databinding.FragmentProcedureListBinding
import ru.pervov.lovenail.procedure_list_screen.view_model.ProcedureListViewModel
import ru.pervov.lovenail.procedure_list_screen.view_model.ProcedureListViewModelFactory
import ru.pervov.lovenail.procedure_list_screen.view_model.ProcedureListViewModelState
import ru.pervov.lovenail.utils.NavigationAction
import ru.pervov.lovenail.utils.NavigationHolder

class ProcedureListFragment : Fragment() {

    private var viewModel: ProcedureListViewModel? = null
    private var binding: FragmentProcedureListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ProcedureListViewModelFactory()
        )[ProcedureListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProcedureListBinding.inflate(inflater, container, false)
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

        binding.procedureListRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                setVisibleErrorScreen(false)
                setVisibleLoadingScreen(false)
                when (state) {
                    is ProcedureListViewModelState.Error -> {
                        setVisibleErrorScreen(true)
                    }

                    is ProcedureListViewModelState.Loading -> {
                        setVisibleLoadingScreen(true)
                    }

                    is ProcedureListViewModelState.Success -> {
                        binding.procedureListRecyclerView.adapter =
                            ProcedureListAdapter(state.procedureList) { id: String ->
                                openCreateOrUpdateProcedureScreen(id)
                            }
                    }
                }
            }
        }
        binding.addProcedureImageView.setOnClickListener {
            openCreateOrUpdateProcedureScreen(null)
        }
    }

    private fun openCreateOrUpdateProcedureScreen(id: String?) {
        (activity as? NavigationHolder)?.doNavigation(NavigationAction.OpenCreateOrUpdateProcedure(id))
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