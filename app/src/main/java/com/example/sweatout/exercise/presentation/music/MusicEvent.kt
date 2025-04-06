package com.example.sweatout.exercise.presentation.music

sealed class MusicEvent {
    object PlayPause : MusicEvent()
    object NextTrack: MusicEvent()
    object PreviousTrack: MusicEvent()
//    data class SeekTo(val position: Long): MusicEvent()
//    object InitializePlayer: MusicEvent()
    object ReleasePlayer: MusicEvent()
}
