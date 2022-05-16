package io.jamshid.navoiyterminology.utils

import io.jamshid.navoiyterminology.data.local.entities.Term

interface OnItemClickListener {
    fun onClick(pos:Int):Boolean
    fun onImageClick(pos: Int,term: Term)
}