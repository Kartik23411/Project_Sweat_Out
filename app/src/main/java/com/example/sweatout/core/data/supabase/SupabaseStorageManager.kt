package com.example.sweatout.core.data.supabase

import android.content.Context
import android.net.Uri
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class SupabaseStorageManager(
    private val context: Context,
    private val supabaseUrl: String,
    private val supabaseKey: String
) {
    private val client = HttpClient(CIO)

    suspend fun uploadProfileImage(username: String, imageUri: Uri): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream: InputStream = context.contentResolver.openInputStream(imageUri)
                        ?: return@withContext Result.failure(Exception("Unable to open the image"))
                val imageBytes = inputStream.readBytes()
                inputStream.close()

                val fileName = "${username}_profile.jpg"

                val uploadUrl =
                        "$supabaseUrl/storage/v1/object/${SupabaseConstants.PROFILE_PICTURES_BUCKET}/$fileName"

                val response: HttpResponse = client.put(uploadUrl) {
                    header("apikey", supabaseKey)
                    header("Authorization", "Bearer $supabaseKey")
                    header("Content-Type", "image/jpeg")
                    setBody(imageBytes)
                }

                if (response.status == HttpStatusCode.OK || response.status == HttpStatusCode.Created) {
                    Result.success(uploadUrl)
                }
                else {
                    val errorBody = response.bodyAsText()
                    Result.failure(Exception("Upload failed with status ${response.status}: $errorBody"))
                }
            }
            catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}