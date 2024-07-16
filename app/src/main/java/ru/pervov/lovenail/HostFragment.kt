package ru.pervov.lovenail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HostFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findNavController()
            .navigate(
                HostFragmentDirections
                    .actionHostFragmentToClientListFragment()
            )
    }
}