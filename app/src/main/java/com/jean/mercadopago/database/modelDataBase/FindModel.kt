package com.jean.mercadopago.database.modelDataBase
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "findTable")
data class FindModel(
    @ColumnInfo(name = "findText")
    @SerializedName("findText")
    var findText: String = "",
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "findID")
    @SerializedName("findID")
    val findID: Int,
    @ColumnInfo(name = "findSize")
    @SerializedName("findSize")
    var findSize: String = ""

) : Serializable