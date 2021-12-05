package com.coolightman.crypton.data.mapper

import com.coolightman.crypton.data.database.model.CoinInfoDbModel
import com.coolightman.crypton.data.network.dto.CoinInfoDto
import com.coolightman.crypton.data.network.dto.CoinInfoJsonContainerDto
import com.coolightman.crypton.data.network.dto.CoinNamesListDto
import com.coolightman.crypton.domain.entity.CoinInfo
import com.coolightman.crypton.utils.TimeConverter
import com.google.gson.Gson

class CoinMapper {

    companion object {
        const val LOGO_URL_ROOT = "https://www.cryptocompare.com"
    }

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        changeHour = dto.changeHour,
        changePctHour = dto.changePctHour,
        openHour = dto.openHour,
        highHour = dto.highHour,
        lowHour = dto.lowHour,
        changeDay = dto.changeDay,
        changePctDay = dto.changePctDay,
        openDay = dto.openDay,
        highDay = dto.highDay,
        lowDay = dto.lowDay,
        change24Hour = dto.change24Hour,
        changePct24Hour = dto.changePct24Hour,
        open24Hours = dto.open24Hours,
        high24Hours = dto.high24Hours,
        low24Hours = dto.low24Hours,
        volumeHour = dto.volumeHour,
        volumeHourTo = dto.volumeHourTo,
        volumeDay = dto.volumeDay,
        volumeDayTo = dto.volumeDayTo,
        volume24Hours = dto.volume24Hours,
        volume24HoursTo = dto.volume24HoursTo,
        lastVolume = dto.lastVolume,
        lastVolumeTo = dto.lastVolumeTo,
        lastMarket = dto.lastMarket,
        imageUrl = LOGO_URL_ROOT + dto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val coinInfoJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = coinInfoJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfoJson = coinInfoJson.getAsJsonObject(currencyKey)
                val coinInfo = Gson().fromJson(priceInfoJson, CoinInfoDto::class.java)
                result.add(coinInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names.joinToString(",") { it.coinName.name }
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo {
        return CoinInfo(
            fromSymbol = dbModel.fromSymbol,
            toSymbol = dbModel.toSymbol,
            price = dbModel.price,
            lastUpdate = TimeConverter.convertTimestampToTime(dbModel.lastUpdate),
            changeHour = dbModel.changeHour,
            changePctHour = dbModel.changePctHour,
            openHour = dbModel.openHour,
            highHour = dbModel.highHour,
            lowHour = dbModel.lowHour,
            changeDay = dbModel.changeDay,
            changePctDay = dbModel.changePctDay,
            openDay = dbModel.openDay,
            highDay = dbModel.highDay,
            lowDay = dbModel.lowDay,
            change24Hour = dbModel.change24Hour,
            changePct24Hour = dbModel.changePct24Hour,
            open24Hours = dbModel.open24Hours,
            high24Hours = dbModel.high24Hours,
            low24Hours = dbModel.low24Hours,
            volumeHour = dbModel.volumeHour,
            volumeHourTo = dbModel.volumeHourTo,
            volumeDay = dbModel.volumeDay,
            volumeDayTo = dbModel.volumeDayTo,
            volume24Hours = dbModel.volume24Hours,
            volume24HoursTo = dbModel.volume24HoursTo,
            lastVolume = dbModel.lastVolume,
            lastVolumeTo = dbModel.lastVolumeTo,
            lastMarket = dbModel.lastMarket,
            imageUrl = dbModel.imageUrl
        )
    }

}