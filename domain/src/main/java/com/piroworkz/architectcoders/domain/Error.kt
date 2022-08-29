package com.piroworkz.architectcoders.domain

sealed interface Error {
    object Server : Error
    object InputOutput : Error
    object Auth : Error
    object Unknown : Error
    object Connectivity : Error
}