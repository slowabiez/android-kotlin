package com.anara.helloworld

data class CovidResponse(
    val update: CovidUpdate
)

data class CovidUpdate(
    val harian: List<Covid>
)

data class Covid(
    val key: Long,
    val jumlah_positif: CovidValue
)

data class CovidValue(
    val value: Int
)