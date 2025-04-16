package com.example.sweatout.exercise.presentation.workout.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sweatout.exercise.domain.models.Exercise

@Composable
fun ExerciseInfoColumn(
    modifier: Modifier = Modifier,
    exercise: Exercise
) {
    Column(
        Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        // Muscle Involved Row
        Text(
            text = "Muscles Involved:- ",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        val list = exercise.muscleInvolved
        Text(
            text = "• " + list.joinToString(separator = ", "),
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "Instructions:- ",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "• " + exercise.instructions,
            style = MaterialTheme.typography.bodyMedium,
        )

        if (exercise.isEquipmentRequired){
            Text(
                text = "Equipments Name:- ",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 16.dp)
            )
            val list = exercise.equipmentNames
            Text(
                text = "• " + list.joinToString(separator = ", "),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}