package io.jamshid.navoiyterminology.utils

import io.jamshid.navoiyterminology.data.local.entities.Term

interface OnItemClickListener {
    fun onClick(pos:Int,status:Boolean):Boolean
    fun onImageClick(term: Term)
}