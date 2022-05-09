package io.jamshid.navoiyterminology.ui.main.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.navoiyterminology.R
import io.jamshid.navoiyterminology.data.local.entities.Term
import io.jamshid.navoiyterminology.databinding.TermItemRcvBinding
import io.jamshid.navoiyterminology.utils.OnItemClickListener

class TermsAdapter(private val onItemClickListener: OnItemClickListener,private val ctx:Context) :
    RecyclerView.Adapter<TermsAdapter.ViewHolder>() {

    private var termList: ArrayList<Term> = ArrayList()

    inner class ViewHolder(private val binding: TermItemRcvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        @RequiresApi(Build.VERSION_CODES.Q)
        fun onBind(position: Int, term: Term) {
            binding.apply {
                tvNameTerm.text = term.name
                tvDescriptionTerm.text = term.description
                if (term.status) {
                    imgTermsStar.setImageResource(R.drawable.ic_fill_star)
                }
                imgTermsStar.setOnClickListener {
                    onItemClickListener.onImageClick(term.copy(status = !term.status))
                    if (term.status) { 
                        imgTermsStar.setImageResource(R.drawable.ic_star)
                    }else{
                        imgTermsStar.setImageResource(R.drawable.ic_fill_star)
                    }
                    termList[position]=term.copy(status = !term.status)
                }
                imgTermsSelectable.setOnClickListener {

                    if (!onItemClickListener.onClick(position,term.status)) {
                        binding.apply {
                            imgTermsSelectable.setImageResource(R.drawable.ic_close)
                            tvDescriptionTerm.visibility = View.VISIBLE
                            line1.visibility = View.VISIBLE
                            imgTermsStar.visibility = View.VISIBLE
                        }
                    } else {
                        imgTermsSelectable.setImageResource(R.drawable.ic_add)
                        tvDescriptionTerm.visibility = View.GONE
                        line1.visibility = View.GONE
                        imgTermsStar.visibility = View.GONE
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TermItemRcvBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.term_item_rcv, parent, false)
            )
        )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position, termList[position])
    }

    override fun getItemCount(): Int = termList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Term>) {
        termList = list as ArrayList<Term>
        notifyDataSetChanged()
    }
}