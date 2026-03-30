package com.isradeleon.kmpappv2.data.mapper

import com.isradeleon.kmpappv2.data.local.entities.FavoriteCoinEntity
import com.isradeleon.kmpappv2.data.remote.dtos.CoinDto
import com.isradeleon.kmpappv2.data.remote.dtos.CoinPriceDto
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = uuid,
        rank = rank,
        symbol = symbol,
        name = name,
        description = description,
        iconUrl = iconUrl,
        price = price,
        change = change
    )
}

fun CoinPriceDto.toPriceHistoryItem(): PriceHistoryItem {
    return PriceHistoryItem(
        price = price ?: 0.0,
        timestamp = timestamp ?: 0
    )
}

fun FavoriteCoinEntity.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        symbol = symbol,
        name = name,
        description = "",
        iconUrl = iconUrl,
        price = price,
        change = change
    )
}

fun Coin.toFavoriteCoinEntity(): FavoriteCoinEntity {
    return FavoriteCoinEntity(
        id = id,
        rank = rank,
        symbol = symbol,
        name = name,
        iconUrl = iconUrl,
        price = price,
        change = change
    )
}