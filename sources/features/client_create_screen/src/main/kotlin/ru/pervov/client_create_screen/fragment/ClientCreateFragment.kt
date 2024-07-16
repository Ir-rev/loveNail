package ru.pervov.client_create_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.pervov.client_create_screen.view_model.ClientCreateViewModel
import ru.pervov.client_create_screen.view_model.ClientListViewModelFactory
import ru.pervov.lovenail.client_create_screen.databinding.FragmentClientCreateBinding

class ClientCreateFragment : Fragment() {

    private var viewModel: ClientCreateViewModel? = null
    private var binding: FragmentClientCreateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ClientListViewModelFactory()
        )[ClientCreateViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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


    }
}