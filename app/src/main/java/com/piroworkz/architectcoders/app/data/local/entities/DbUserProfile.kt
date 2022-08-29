package com.piroworkz.architectcoders.app.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class DbUserProfile(
    @Embedded
    val dbUser: DbUser?,
    @Relation(
        parentColumn = "email",
        entityColumn = "userId"
    )
    val shippingAddress: DbShippingAddress?,

    @Relation(
        parentColumn = "email",
        entityColumn = "userId"
    )
    val billingAddress: DbBillingAddress?,
    @Relation(
        parentColumn = "email",
        entityColumn = "userId"
    )
    val paymentMethod: DbPaymentMethod?
)