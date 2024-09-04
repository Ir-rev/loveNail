package ru.pervov.lovenail.mock_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.pervov.lovenail.mock_screen.databinding.FragmentMockBinding
import ru.pervov.lovenail.mock_screen.view_model.CreateMockViewModel
import ru.pervov.lovenail.mock_screen.view_model.CreateMockViewModelAction
import ru.pervov.lovenail.mock_screen.view_model.CreateMockViewModelFactory

class CreateMockFragment : Fragment() {

    private var viewModel: CreateMockViewModel? = null
    private var binding: FragmentMockBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            CreateMockViewModelFactory()
        )[CreateMockViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMockBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = binding ?: return
        val viewModel = viewModel ?: return

        lifecycleScope.launch {
            viewModel.action.collect{ action ->
                when(action) {
                    is CreateMockViewModelAction.ShowToast -> {
                        Toast.makeText(requireContext(), "${action.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.buttonClear.setOnClickListener {
            viewModel.clearMock()
        }
        binding.buttonAddMock.setOnClickListener {
            viewModel.addMock()
        }
    }
}