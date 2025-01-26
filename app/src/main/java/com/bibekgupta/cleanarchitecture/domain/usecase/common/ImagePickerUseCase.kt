package com.bibekgupta.cleanarchitecture.domain.usecase.common

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class ImagePickerUseCase @Inject constructor() {

    fun getRealPathFromURI(uri: Uri, context: Context): String? {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            // For Android versions < Q, use the method to get the file path
            val filePath = getFilePathFromUri(uri, context)
            Log.d("ImagePickerUseCase", "File path for pre-Android Q: $filePath")
            filePath
        } else {
            // For Android Q and above, use a safer approach to get the file
            val file = getFileFromUri(uri, context)
            val filePath = file?.absolutePath
            Log.d("ImagePickerUseCase", "File path for Android Q and above: $filePath")
            filePath
        }
    }

    private fun getFilePathFromUri(uri: Uri, context: Context): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (cursor.moveToFirst()) {
                cursor.getString(columnIndex)
            } else null
        }
    }

    private fun getFileFromUri(uri: Uri, context: Context): File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = System.currentTimeMillis().toString() + ".jpg"
            val tempFile = File(context.cacheDir, fileName)
            inputStream?.use { input ->
                FileOutputStream(tempFile).use { output ->
                    input.copyTo(output)
                }
            }
            tempFile
        } catch (e: Exception) {
            Log.e("ImagePickerUseCase", "Error retrieving file from URI: ${e.message}")
            null
        }
    }
}
