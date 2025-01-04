package com.example.sweatout.welcome.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.ui.theme.SweatOutTheme
import com.example.sweatout.welcome.domain.User
import com.example.sweatout.welcome.presentation.components.CircularSelectingBox
import com.example.sweatout.welcome.presentation.components.MyAppButton
import com.example.sweatout.welcome.presentation.components.noRippleClickable

@Composable
fun GenderSelectionScreen(
    modifier: Modifier = Modifier,
    onCancelClick:()->Unit,
    onProceedClick:() -> Unit
) {
    var isMaleSelected by remember { mutableStateOf(false) }
    var isFemaleSelected by remember { mutableStateOf(true) }
    val user: User
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Headline text
        Text(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
            text = stringResource(R.string.gender_selection_screen_headline),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSurface
        )
        // about text
        Text(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
            text = stringResource(R.string.gender_selection_screen_about_text),
            style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .7f)
        )
        // Gender Option Buttons column
        Column(
            modifier = Modifier.weight(.9f).padding(vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceAround
        ){
            CircularSelectingBox(
                icon = painterResource(R.drawable.baseline_male_24),
                text = "Male",
                isSelected = isMaleSelected,
                unselectedColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                selectedColor = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(200.dp),
                onClick = {
                    if (!isMaleSelected) {
                    isMaleSelected = true
                    isFemaleSelected = false // Deselect Female
                }
                }
            )

            CircularSelectingBox(
                icon = painterResource(R.drawable.baseline_female_24),
                text = "Female",
                isSelected = isFemaleSelected,
                unselectedColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                selectedColor = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(200.dp),
                onClick = {if (!isFemaleSelected) {
                    isFemaleSelected = true
                    isMaleSelected = false // Deselect Male
                }}
            )
        }
        // Proceed and cancel Button Row
        Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyAppButton(
                onClick = { onCancelClick() }, /*TODO() Add the clicking functionality*/
                buttonText = "Back",
                buttonColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .noRippleClickable { onCancelClick() }
            )
            Spacer(Modifier.width(24.dp))
            MyAppButton(
                onClick = {
                    if (isMaleSelected){
//                        user.gender = "Male"
                        onProceedClick()
                    }else if (isFemaleSelected){
//                        user.gender = "Female"
                        onProceedClick()
                    }
                    else{
                        Toast.makeText(context, "Please select anyone option", Toast.LENGTH_SHORT).show()
                    }
                },
                buttonText = "Proceed",
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .noRippleClickable { onProceedClick() }
            )
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
private fun GenderSelectionScreenPreview() {
    SweatOutTheme {
        GenderSelectionScreen(
            modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            {},{}
        )
    }
}