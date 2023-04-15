package com.example.tasky.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users_table")
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val login: String?,
    @ColumnInfo(name = "password") val password: String?,
) {
}