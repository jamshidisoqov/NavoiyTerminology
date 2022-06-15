package io.jamshid.navoiyterminology.ui.main.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.jamshid.navoiyterminology.R
import io.jamshid.navoiyterminology.data.local.entities.Term
import io.jamshid.navoiyterminology.databinding.DialogAddTermsBinding
import io.jamshid.navoiyterminology.databinding.HomeFragmentBinding
import io.jamshid.navoiyterminology.ui.main.home.adapters.TermsAdapter
import io.jamshid.navoiyterminology.utils.Constants
import io.jamshid.navoiyterminology.utils.OnItemClickListener
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: TermsAdapter

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        _binding = HomeFragmentBinding.inflate(inflater, container, false)

        viewModel.getTerms()

        adapter = TermsAdapter(object : OnItemClickListener {
            override fun onClick(pos: Int): Boolean {
                val bool=Constants.current.contains(pos)
                if(bool){
                    Constants.current.remove(pos)
                }else{
                    Constants.current.add(pos)
                }

                return bool
            }

            override fun onImageClick(pos:Int,term: Term) {
                viewModel.updateTerm(term)
            }
        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.allTerms.collectLatest {
                if (it.data != null)
                    adapter.setData(it.data!!)
            }
        }


        binding.imgSelected.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_savedWordsFragment)
        }

        binding.imgAppHome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        }

        binding.edSearch.addTextChangedListener {
            viewModel.search(it!!.toString().trim())
        }


        binding.rcvTerms.adapter = adapter

        binding.imgAddTerms.setOnClickListener {
            val dialog = Dialog(requireContext())
            val bd =
                DialogAddTermsBinding.bind(inflater.inflate(R.layout.dialog_add_terms, null, false))
            dialog.setContentView(bd.root)
            bd.apply {
                btnAdd.setOnClickListener {
                    viewModel.addTerms(
                        Term(
                            0,
                            bd.edName.text.toString(),
                            bd.edDesc.text.toString(),
                            false
                        )
                    )
                    dialog.dismiss()
                    viewModel.getTerms()

                }
            }
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }



        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        Constants.current = mutableSetOf<Int>() as HashSet<Int>
    }
}