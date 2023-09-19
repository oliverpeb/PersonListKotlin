package com.example.personlistkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personlistkotlin.databinding.FragmentPersonlistBinding

class PersonListFragment : Fragment() {

    private var _binding: FragmentPersonlistBinding? = null
    private val viewModel: PersonsViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPersonlistBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.persons.observe(viewLifecycleOwner) {persons->
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            val adapter = PersonAdapter(persons) {position ->
                viewModel.selected.value = viewModel[position]
                viewModel.adding.value = false
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            binding.recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}