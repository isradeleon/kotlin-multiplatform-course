package com.isradeleon.kmpappv2.data.mapper

import com.isradeleon.kmpappv2.data.remote.dto.CoinDto
import com.isradeleon.kmpappv2.data.remote.dto.CoinPriceDto
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = uuid,
        rank = rank,
        symbol = symbol,
        name = name,
        description = description,
        color = color,
        iconUrl = iconUrl,
        price = price,
        change = change
    )
}

fun CoinPriceDto.toPriceHistoryItem(): PriceHistoryItem {
    return PriceHistoryItem(
        price = price ?: 0.0,
        timestamp = timestamp
    )
}