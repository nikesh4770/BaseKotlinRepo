package com.app.recipe.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.recipe.R
import com.app.recipe.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var _binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Obtain binding
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        // Bind layout with ViewModel
        _binding.viewModel = homeViewModel
        // LiveData needs the lifecycle owner
        _binding.lifecycleOwner = viewLifecycleOwner
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.searchText.setOnClickListener {
            homeViewModel.performComplexRecipeSearch(_binding.homeSearchBar.editTxtSearch.text.toString())
        }
    }

    override fun onDestroyView() {
        _binding.unbind()
        super.onDestroyView()
    }
}