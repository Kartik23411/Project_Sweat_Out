package com.example.sweatout.exercise.data

import com.example.sweatout.R
import com.example.sweatout.exercise.domain.models.Music
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicRepository @Inject constructor() {
    fun getWorkoutPlaylist(): List<Music> {
        return listOf(
            Music(
                id = "1",
                name = "Workout Motivation",
                artist = "Fitness Beats",
                musicResId = R.raw.loseyourself,
                coverResId = R.drawable.apple
            ),
            Music(
                id = "2",
                name = "Energy Boost",
                artist = "Fitness Beats",
                musicResId = R.raw.loseyourself,
                coverResId = R.drawable.apple
            ),
            Music(
                id = "3",
                name = "Power Workout",
                artist = "Fitness Beats",
                musicResId = R.raw.survivor,
                coverResId = R.drawable.apple
            )
        )
    }
}