package io.jamshid.navoiyterminology.ui.main.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import io.jamshid.navoiyterminology.R
import io.jamshid.navoiyterminology.databinding.SavedWordsFragmentBinding

class SavedWordsFragment : Fragment() {

    private var _binding: SavedWordsFragmentBinding? = null
    private val binding: SavedWordsFragmentBinding get() = _binding!!
    private val viewModel: SavedWordsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = SavedWordsFragmentBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}