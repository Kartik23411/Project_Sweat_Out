package com.example.sweatout.exercise.presentation.music

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.sweatout.exercise.data.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(MusicState())
    val state: StateFlow<MusicState> = _state.asStateFlow()

    private var player: ExoPlayer? = null
    private var currentTrackIndex = 0

    init {
        initializePlayer()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(context).build()
        val playlist = musicRepository.getWorkoutPlaylist()
        if (playlist.isNotEmpty()) {
            loadTrack(0)
        }
    }

    fun onEvent(event: MusicEvent) {
        when (event) {
            is MusicEvent.PlayPause -> togglePlayPause()
            is MusicEvent.NextTrack -> playNextTrack()
            is MusicEvent.PreviousTrack -> playPreviousTrack()
            is MusicEvent.ReleasePlayer -> releasePlayer()
        }
    }

    private fun togglePlayPause() {
        player?.let {
            if (it.isPlaying) {
                it.pause()
            } else {
                it.play()
            }
            _state.value = _state.value.copy(isPlaying = it.isPlaying)
        }
    }

    private fun playNextTrack() {
        val playlist = musicRepository.getWorkoutPlaylist()
        val nextIndex = (currentTrackIndex + 1) % playlist.size
        loadTrack(nextIndex)
    }

    private fun playPreviousTrack() {
        val playlist = musicRepository.getWorkoutPlaylist()
        val prevIndex = (currentTrackIndex - 1 + playlist.size) % playlist.size
        loadTrack(prevIndex)
    }

    private fun loadTrack(index: Int) {
        val playlist = musicRepository.getWorkoutPlaylist()
        val track = playlist[index]
        currentTrackIndex = index

        player?.let { exoPlayer ->
            val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${track.musicResId}")
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()

            _state.value = _state.value.copy(isPlaying = true)
        }
    }

     fun releasePlayer() {
        player?.release()
        player = null
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }
}