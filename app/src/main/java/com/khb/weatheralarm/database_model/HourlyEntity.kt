package com.khb.weatheralarm.database_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hourly")
data class HourlyEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo var dt: Long = 0L,
    @ColumnInfo var temp: Double = 0.0,
    @ColumnInfo var feelstemp: Double = 0.0,
    @ColumnInfo var humidity: Int = 0,
    @ColumnInfo var clouds: Int = 0,
    @ColumnInfo var visibility: Int = 0,
    @ColumnInfo var weatherid: Int = 0,
    @ColumnInfo var main: String = "",
    @ColumnInfo var description: String = "",
    @ColumnInfo var icon: String = ""
)