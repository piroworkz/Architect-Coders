package com.piroworkz.architectcoders.app.data.utils

import com.google.firebase.auth.FirebaseUser
import com.piroworkz.architectcoders.app.data.local.entities.DbBanner
import com.piroworkz.architectcoders.app.data.local.entities.DbCartItem
import com.piroworkz.architectcoders.app.data.local.entities.DbProduct
import com.piroworkz.architectcoders.app.data.local.entities.DbUserProfile
import com.piroworkz.architectcoders.app.data.remote.model.RemotePostalAddress
import com.piroworkz.architectcoders.domain.*

fun DbUserProfile.toDomain() = UserProfile(
    user = dbUser?.let {
        User(
            it.name,
            dbUser.email,
            dbUser.uid,
            dbUser.phone,
            dbUser.photoUrl
        )
    },
    shippingAddress = shippingAddress?.let {
        ShippingAddress(
            it.userId,
            it.street,
            it.town,
            it.city,
            it.state,
            it.zipCode
        )
    },
    billingAddress = billingAddress?.let {
        BillingAddress(
            it.userId,
            it.street,
            it.town,
            it.city,
            it.state,
            it.zipCode
        )
    },
    paymentMethod = paymentMethod?.let {
        PaymentMethod(
            it.userId,
            it.card_holder,
            it.card_number,
            it.card_issuer,
            it.card_expiration,
            it.card_cvc
        )
    }

)

fun DbCartItem.toDomain(): CartItem =
    CartItem(
        model = model,
        description = description,
        price = price,
        title = title,
        imageUrl = imageUrl,
        smallSize = smallSize,
        mediumSize = mediumSize,
        largeSize = largeSize
    )

fun DbProduct.toDomain(): Product {
    return Product(
        model = model,
        description = description,
        price = price,
        title = title,
        imageUrl = imageUrl,
        smallSize = smallSize,
        mediumSize = mediumSize,
        largeSize = largeSize,
        favorite = favorite
    )
}

fun DbBanner.toDomain(): Banner {
    return Banner(
        model = model,
        bannerUrl = bannerUrl
    )
}

fun FirebaseUser?.toDomain(): User? {
    return this?.let {
        User(
            uid = it.uid,
            name = it.displayName!!,
            email = it.email!!,
            phone = it.phoneNumber ?: String(),
            photoUrl = it.photoUrl.toString()
        )
    }
}

fun RemotePostalAddress.toDomainPostalCode(): PostalAddress {
    return PostalAddress(
        zipCode = postalCode,
        state = adminName1,
        city = adminName2,
        town = placeName
    )
}