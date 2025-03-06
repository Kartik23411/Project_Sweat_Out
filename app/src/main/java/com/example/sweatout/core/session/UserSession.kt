package com.example.sweatout.core.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.sweatout.welcome.domain.models.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val gson = Gson()
    private val USER_KEY = stringPreferencesKey("current_user")

    val currentUserFlow: Flow<User?> = dataStore.data.map {preferences->
        preferences[USER_KEY].let {json->
            gson.fromJson(json, User::class.java)
        }
    }

    suspend fun saveCurrentUser(user: User){
        dataStore.edit { preferences->
            preferences[USER_KEY] = gson.toJson(user)
        }
    }

    suspend fun clearData(){
        dataStore.edit { preferences->
            preferences.remove(USER_KEY)
        }
    }
}