package com.mahmoud.bashir.newsapp.roomdb

import androidx.room.TypeConverter
import com.mahmoud.bashir.newsapp.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source{
        return Source(name ,name)
    }
}