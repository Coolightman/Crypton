package com.coolightman.crypton.data.network

import com.coolightman.crypton.domain.entity.CoinInfoList
import com.coolightman.crypton.domain.entity.CoinPriceRawData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val API_KEY =
            "bf97aec7175be2f689e2aace11816e210f390fe208ec27ce0654fad4e1926cbd"
        private const val HEADER_KEY_NAME = "Apikey"
        private const val HEADER_KEY = "$HEADER_KEY_NAME: $API_KEY"

        private const val PARAM_CRYPTOS = "fsyms"
        private const val PARAM_CURRENCIES = "tsyms"
        private const val PARAM_LIMIT = "limit"
        private const val PARAM_CURRENCY = "tsym"
    }

    @Headers(HEADER_KEY)
    @GET("top/totalvolfull")
    suspend fun loadCoinInfoListData(
        @Query(PARAM_LIMIT) limit: Int,
        @Query(PARAM_CURRENCY) currency: String
    ): Response<CoinInfoList>


    @Headers(HEADER_KEY)
    @GET("pricemultifull")
    suspend fun loadFullPriceList(
        @Query(PARAM_CRYPTOS)cryptos: String,
        @Query(PARAM_CURRENCIES) currencies: String
    ): Response<CoinPriceRawData>

}