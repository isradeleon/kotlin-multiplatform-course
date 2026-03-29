package com.isradeleon.kmpappv2.presentation.extensions

import com.isradeleon.kmpappv2.domain.model.Coin

val Coin.formattedPrice: String get() {
    return ""
}

val Coin.formattedChange: String get() {
    return ""
}

val Coin.isPositiveChange: Boolean get() {
    return change > 0
}
