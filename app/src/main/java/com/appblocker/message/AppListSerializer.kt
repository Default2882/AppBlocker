package com.appblocker.message

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.appblocker.AppListOuterClass.AppList
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object AppListSerializer : Serializer<AppList> {
    override val defaultValue: AppList
        get() = AppList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppList {
        try {
            return AppList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: AppList, output: OutputStream) = t.writeTo(output)
}

val Context.appListDataStore by dataStore(
    fileName = "appList.pb",
    serializer = AppListSerializer
)