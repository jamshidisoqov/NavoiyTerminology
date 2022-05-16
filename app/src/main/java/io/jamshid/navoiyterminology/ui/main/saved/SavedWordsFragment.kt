package io.jamshid.navoiyterminology.ui.main.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.jamshid.navoiyterminology.R
import io.jamshid.navoiyterminology.data.local.entities.Term
import io.jamshid.navoiyterminology.databinding.SavedWordsFragmentBinding
import io.jamshid.navoiyterminology.ui.main.home.adapters.TermsAdapter
import io.jamshid.navoiyterminology.utils.Constants
import io.jamshid.navoiyterminology.utils.OnItemClickListener
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedWordsFragment : Fragment() {

    private var _binding: SavedWordsFragmentBinding? = null
    private val binding: SavedWordsFragmentBinding get() = _binding!!
    private val viewModel: SavedWordsViewModel by viewModel()
    private lateinit var adapter:TermsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = SavedWordsFragmentBinding.inflate(inflater,container,false)

        Constants.current = hashSetOf()

        viewModel.getSavedTerms()

        adapter = TermsAdapter(object : OnItemClickListener{
            override fun onClick(pos: Int): Boolean {
                val bool=Constants.current.contains(pos)
                if(bool){
                    Constants.current.remove(pos)
                }else{
                    Constants.current.add(pos)
                }

                return bool
            }

            override fun onImageClick(pos: Int,term: Term) {
                viewModel.updateTerm(term)
                viewModel.getSavedTerms()
            }
        })


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.savedTerm.collectLatest {
                if (it.data!=null)
            adapter.setData(it.data!!)
            }
        }

        binding.rcvSelectableTerms.adapter = adapter

        binding.imgBack.setOnClickListener { findNavController().navigateUp() }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}