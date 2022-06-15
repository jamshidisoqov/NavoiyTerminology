package io.jamshid.navoiyterminology.ui.main.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.jamshid.navoiyterminology.databinding.InfoFragmentBinding

class InfoFragment : Fragment() {
    private lateinit var binding: InfoFragmentBinding
    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InfoFragmentBinding.inflate(inflater,container,false)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }


}