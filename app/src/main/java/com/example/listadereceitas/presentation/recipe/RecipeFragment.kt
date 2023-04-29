package com.example.listadereceitas.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.listadereceitas.R
import com.example.listadereceitas.databinding.FragmentFirstBinding
import com.example.listadereceitas.presentation.recipe.adapter.RecipeAdapter

class RecipeFragment : Fragment() {


    private lateinit var binding: FragmentFirstBinding
    private val adapter by lazy { RecipeAdapter() }
    private val viewModel: RecipeViewModel by viewModels { RecipeViewModel.Factory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupAdapter()
        observeStates()
    }

    fun setupListeners(){
        binding.fabAdd.setOnClickListener {

        }

    }

    fun setupAdapter(){
        binding.rvRecipes.adapter = adapter
    }

    private fun observeStates(){
        viewModel.state.observe(viewLifecycleOwner){ state ->
            when(state){
                RecipeState.Loading -> {binding.pbLoading.isVisible = true}

                RecipeState.Empty -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(requireContext(), getString(R.string.msg_toast_sem_receita), Toast.LENGTH_LONG).show()
                }

                is RecipeState.Error -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }

                is RecipeState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(state.recipe)
                }
            }

        }
    }



}