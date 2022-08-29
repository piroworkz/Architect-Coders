package com.piroworkz.architectcoders.app.data.utils

import com.google.firebase.firestore.DocumentSnapshot
import com.piroworkz.architectcoders.app.ui.common.tryCatch
import com.piroworkz.architectcoders.domain.*

fun DocumentSnapshot?.toDomainUser(): User =
    User(
        uid = this?.get("uid") as String,
        name = get("name") as String,
        email = get("email") as String,
        phone = get("phone") as String,
        photoUrl = get("photoUrl") as String
    )

fun DocumentSnapshot?.toProduct() = Product(
    model = this?.get("id") as String,
    description = get("description") as String,
    price = (get("price") as String).toInt(),
    title = get("title") as String,
    imageUrl = get("imageUrl") as String,
    smallSize = (get("inventory.smallSize") as String).toInt(),
    mediumSize = (get("inventory.mediumSize") as String).toInt(),
    largeSize = (get("inventory.largeSize") as String).toInt(),
    favorite = false
)

fun DocumentSnapshot.toBanner() = Banner(
    model = get("id") as String,
    bannerUrl = get("bannerUrl") as String,
)

fun DocumentSnapshot?.toDomainShippingAddress(): ShippingAddress? = tryCatch {
    ShippingAddress(
        userId = this?.get("userId") as String,
        street = get("street") as String,
        town = get("town") as String,
        city = get("city") as String,
        state = get("state") as String,
        zipCode = get("zipCode").toString()
    )
}.fold(
    ifLeft = { null },
    ifRight = { it }
)

fun DocumentSnapshot?.toDomainBillingAddress(): BillingAddress? = tryCatch {
    BillingAddress(
        userId = this?.get("userId") as String,
        street = get("street") as String,
        town = get("town") as String,
        city = get("city") as String,
        state = get("state") as String,
        zipCode = get("zipCode").toString()
    )
}.fold(
    ifLeft = { null },
    ifRight = { it }
)

fun DocumentSnapshot?.toDomainPaymentMethod(): PaymentMethod? = tryCatch {
    PaymentMethod(
        userId = this?.get("userId") as String,
        card_holder = get("card_holder") as String,
        card_number = get("card_number") as String,
        card_issuer = get("card_issuer") as String,
        card_expiration = get("card_expiration") as String,
        card_cvc = get("card_cvc") as String
    )
}.fold(
    ifLeft = { null },
    ifRight = { it }
)