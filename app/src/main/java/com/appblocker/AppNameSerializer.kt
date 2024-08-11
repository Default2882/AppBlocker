package com.appblocker

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import com.appblocker.AppNameOuterClass.AppName
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object AppNameSerializer : Serializer<AppName> {
    override val defaultValue: AppName = AppName.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppName {
        try {
            return AppName.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: AppName, output: OutputStream) = t.writeTo(output)
}