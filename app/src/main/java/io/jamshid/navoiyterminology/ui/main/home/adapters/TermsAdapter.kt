package io.jamshid.navoiyterminology.ui.main.home.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import io.jamshid.navoiyterminology.R
import io.jamshid.navoiyterminology.data.local.entities.Term
import io.jamshid.navoiyterminology.databinding.TermItemRcvBinding
import io.jamshid.navoiyterminology.utils.OnItemClickListener

class TermsAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TermsAdapter.ViewHolder>() {

    private var termList: ArrayList<Term> = ArrayList()

    inner class ViewHolder(private val binding: TermItemRcvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        @RequiresApi(Build.VERSION_CODES.Q)
        fun onBind(position: Int, term: Term) {
            binding.apply {
                tvNameTerm.text = term.name!!.indexOfInsertCharacter().uppercase()
                tvDescriptionTerm.text = term.description
                if (term.status!!) {
                    imgTermsStar.setImageResource(R.drawable.ic_fill_star)
                } else {
                    imgTermsStar.setImageResource(R.drawable.ic_star)
                }

                imgTermsStar.setOnClickListener {
                    onItemClickListener.onImageClick(
                        position,
                        term.copy(status = !term.status!!)
                    )
                    if (term.status!!) {
                        imgTermsStar.setImageResource(R.drawable.ic_star)
                    } else {
                        imgTermsStar.setImageResource(R.drawable.ic_fill_star)
                    }
                    notifyItemChanged(position)
                    termList[position] = term.copy(status = !term.status!!)
                }

                imgTermsSelectable.setOnClickListener {

                    if (onItemClickListener.onClick(position)) {
                        binding.apply {
                            imgTermsSelectable.setImageResource(R.drawable.ic_close)
                            tvDescriptionTerm.visibility = View.VISIBLE
                            line1.visibility = View.VISIBLE
                            imgTermsStar.visibility = View.VISIBLE
                            tvNameTerm.isSingleLine = false
                        }
                    } else {
                        imgTermsSelectable.setImageResource(R.drawable.ic_add)
                        tvDescriptionTerm.visibility = View.GONE
                        line1.visibility = View.GONE
                        tvNameTerm.isSingleLine = true
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

private fun String.indexOfInsertCharacter(): String {

    val ind = this.indexOf(',')
    val builder = StringBuilder()
    builder.append(this)
    if (ind > -1) {
        builder.insert(ind, ' ')
        builder.insert(ind + 2, ' ')
    }
    return builder.toString()

}
