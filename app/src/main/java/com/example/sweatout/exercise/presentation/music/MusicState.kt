package com.example.sweatout.exercise.presentation.music

import com.example.sweatout.exercise.domain.models.Music

data class MusicState(
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val totalDuration: Long = 0L,
    val currentTrack: Music? = null,
    val playlist: List<Music> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
