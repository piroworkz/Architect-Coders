package com.piroworkz.architectcoders.app.data.utils

import com.piroworkz.architectcoders.app.data.local.entities.*
import com.piroworkz.architectcoders.domain.*

fun List<Banner>.toDatabaseBanner(): List<DbBanner> = this.map {
    DbBanner(
        model = it.model,
        bannerUrl = it.bannerUrl
    )
}

//fun Product.toDatabase(): DbProduct {
//    return DbProduct(
//        model = model,
//        description = description,
//        price = price,
//        title = title,
//        imageUrl = imageUrl,
//        smallSize = smallSize,
//        mediumSize = mediumSize,
//        largeSize = largeSize,
//        favorite = favorite
//    )
//}

fun List<Product>.toDatabaseProduct(): List<DbProduct> = this.map {
    DbProduct(
        model = it.model,
        description = it.description,
        price = it.price,
        title = it.title,
        imageUrl = it.imageUrl,
        smallSize = it.smallSize,
        mediumSize = it.mediumSize,
        largeSize = it.largeSize,
        favorite = it.favorite
    )
}

fun CartItem.toDbCartItem() = DbCartItem(
    model = model,
    description = description,
    price = price,
    title = title,
    imageUrl = imageUrl,
    smallSize = smallSize,
    mediumSize = mediumSize,
    largeSize = largeSize
)


fun User.toDatabase(): DbUser = DbUser(
    name = name,
    email = email,
    uid = uid,
    phone = phone,
    photoUrl = photoUrl
)

fun ShippingAddress.toDatabase() = DbShippingAddress(
    userId = userId,
    street = street,
    town = town,
    city = city,
    state = state,
    zipCode = zipCode
)

fun BillingAddress.toDatabase() = DbBillingAddress(
    userId = userId,
    street = street,
    town = town,
    city = city,
    state = state,
    zipCode = zipCode
)

fun PaymentMethod.toDatabase() = DbPaymentMethod(
    userId = userId,
    card_holder = card_holder,
    card_number = card_number,
    card_issuer = card_issuer,
    card_expiration = card_expiration,
    card_cvc = card_cvc
)