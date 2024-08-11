package com.appblocker.message

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.appblocker.AppListOuterClass.AppList
import com.appblocker.ui.component.App
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