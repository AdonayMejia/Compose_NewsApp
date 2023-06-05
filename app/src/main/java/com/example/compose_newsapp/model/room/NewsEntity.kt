package com.example.compose_newsapp.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.compose_newsapp.model.datamodel.Fields
import kotlinx.serialization.SerialName

@Entity(tableName = "FavoriteNews")
class NewsEntity (
    @PrimaryKey
    val itemId: String,

    @ColumnInfo("webTitle") @SerialName("webTitle")
    val webTitle: String,

    @ColumnInfo("webUrl") @SerialName("webUrl")
    val webUrl: String,

    @ColumnInfo("thumbnail") @SerialName("thumbnail")
    val fields: String?

    )