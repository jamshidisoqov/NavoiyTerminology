package io.jamshid.navoiyterminology.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import io.jamshid.navoiyterminology.databinding.HomeFragmentBinding
import io.jamshid.navoiyterminology.ui.main.home.adapters.TermsAdapter
import io.jamshid.navoiyterminology.utils.OnItemClickListener

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: TermsAdapter
    private var oldPosition = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adapter = TermsAdapter(object : OnItemClickListener {
            override fun onClick(pos: Int, status: Boolean): Boolean {
                val old = oldPosition
                oldPosition = pos
                if (old!=pos){
                    adapter.notifyItemChanged(oldPosition)
                    return false
                }else{
                    return true
                }
            }
        })

        _binding = HomeFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}