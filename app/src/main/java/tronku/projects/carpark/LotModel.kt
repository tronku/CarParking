package tronku.projects.carpark

import com.google.gson.annotations.SerializedName

data class LotModel(
    val id: Int,
    val status: Int,

    @SerializedName("carnumber")
    val carNo: String,

    @SerializedName("username")
    val owner: String
)