package com.piroworkz.architectcoders.domain

sealed class TextInputError {
    data class ZipCode(val error: String) : TextInputError()
    object IsEmpty : TextInputError()
}