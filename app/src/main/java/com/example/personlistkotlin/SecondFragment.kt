package com.example.personlistkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.personlistkotlin.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private val model: PersonsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       if (model.adding.value!!){
           binding.textviewId.visibility = View.GONE
           binding.edittextName.visibility = View.GONE
           binding.edittextAddress.visibility = View.GONE
           binding.edittextAge.visibility = View.GONE

       }
        val selectedPerson: Person? = model.selected.value
        if (selectedPerson == null){
            binding.textviewId.text = "No Person selected"
        }else{
            binding.textviewId.text = selectedPerson.id.toString()
            binding.edittextName.setText(selectedPerson.name)
            binding.edittextAddress.setText(selectedPerson.address)
            binding.edittextAge.setText(selectedPerson.age.toString())
        }

        binding.buttonDelete.setOnClickListener{
            if (selectedPerson == null){
                binding.textviewId.text = "No Person selected"
            }else{
                model.remove(selectedPerson.id)
                findNavController().popBackStack()
            }
        }

        binding.buttonUpdate.setOnClickListener{
            val name = binding.edittextName.text.trim().toString()
            val address = binding.edittextAddress.text.trim().toString()
            val age = binding.edittextAge.text.trim().toString().toInt()
            val person = Person(
                name = name,
                address = address,
                age = age
            )
            model.update(selectedPerson!!.id, person)
            findNavController().popBackStack()

        }

        binding.buttonAdd.setOnClickListener{

            val name = binding.edittextName.text.trim().toString()
            val address = binding.edittextAddress.text.trim().toString()
            val age = binding.edittextAge.text.trim().toString().toInt()
            val person = Person(
                name = name,
                address = address,
                age = age
            )
            model.add(person)
            findNavController().popBackStack()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}