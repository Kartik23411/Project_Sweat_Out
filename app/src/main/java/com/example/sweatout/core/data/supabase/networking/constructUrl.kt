package com.example.sweatout.core.data.supabase.networking

import com.example.sweatout.core.data.supabase.SupabaseConstants

fun constructUrl(endPoint: String): String {
    return if (endPoint.startsWith("/")) {
        "${SupabaseConstants.SUPABASE_URL}/rest/v1$endPoint"
    }
    else
        "${SupabaseConstants.SUPABASE_URL}/rest/v1/$endPoint"
}