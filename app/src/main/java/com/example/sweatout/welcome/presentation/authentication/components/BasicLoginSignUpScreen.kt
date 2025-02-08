package com.example.sweatout.welcome.presentation.authentication.components

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.components.MyAppButton
import com.example.sweatout.welcome.presentation.components.noRippleClickable

@Composable
fun BasicLoginAndSignUpScreen(
    modifier: Modifier = Modifier,
    onTextButtonClick: () -> Unit,
    onAuthenticationButtonClick: (String, String) -> Unit,
    @StringRes authenticationButtonText: Int,
    @StringRes textButtonText: Int,
    @StringRes headlineText: Int,
    @StringRes textButtonIntroText: Int,
    isLoginScreen: Boolean
) {
    val email = rememberSaveable { mutableStateOf<String?>("") }
    val password = rememberSaveable { mutableStateOf<String?>("") }
    Column(
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )

        CustomLoginHeadline(textId = headlineText)

        Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            CustomTextField(
                value = email,
                onValueChange = { email.value = it },
                stringResource(R.string.email),
                Icons.Default.Email,
                false,
                KeyboardType.Email,
                imeAction = ImeAction.Next
            )

            Spacer(Modifier.height(24.dp))

            CustomTextField(
                value = password,
                onValueChange = { password.value = it },
                stringResource(R.string.password),
                Icons.Default.Lock,
                true,
                KeyboardType.Password,
                imeAction = ImeAction.Done
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                if (isLoginScreen) {
                    CustomTextButton( R.string.forget_password, onClick =  {Log.e("authentication", "Forget click")})
                }
                else Spacer(Modifier.height(16.dp))
            }

            RememberButton()

            Spacer(Modifier.height(16.dp))

            MyAppButton(
                modifier = Modifier
                        .noRippleClickable {}
                        .fillMaxWidth(fraction = 1f)
                        .height(50.dp),
                onClick = { onAuthenticationButtonClick(email.value!!, password.value!!) },
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                buttonText = stringResource(authenticationButtonText),
                elevation = 4.dp,
            )
        }

        DividerLine()

        LoginOptionRow(
            firstLoginAction = {Log.e("authentication", "Clicked 1")},
            secondLoginAction = {Log.e("authentication", "Clicked 1")},
            thirdLoginAction = {Log.e("authentication", "Clicked 1")},
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp, vertical = 32.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomCompanionText(textId = textButtonIntroText)
            CustomTextButton(
                textId = textButtonText, onClick = {
                    Log.e("authentication", "Signup button error")
                    onTextButtonClick()
                },
                color =
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = .6f)
            )
        }

    }
}

@Composable
fun RememberButton(
    modifier: Modifier = Modifier
) {
    var check by remember { mutableStateOf(false) }
    Row {
        Checkbox(
            modifier = modifier.noRippleClickable { },
            checked = check,
            onCheckedChange = { check = ! check },
            colors = CheckboxColors(
                checkedCheckmarkColor = MaterialTheme.colorScheme.onPrimaryContainer,
                uncheckedCheckmarkColor = MaterialTheme.colorScheme.surface,
                checkedBoxColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedBoxColor = MaterialTheme.colorScheme.surface,
                disabledCheckedBoxColor = MaterialTheme.colorScheme.primaryContainer,
                disabledUncheckedBoxColor = MaterialTheme.colorScheme.surface,
                disabledIndeterminateBoxColor = MaterialTheme.colorScheme.surface,
                checkedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                disabledUncheckedBorderColor = MaterialTheme.colorScheme.onSurface,
                disabledIndeterminateBorderColor = MaterialTheme.colorScheme.onSurface
            )
        )
        CustomTextButton(
            modifier = Modifier,
            onClick = {},
            textId = R.string.remember_me,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun DividerLine(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(thickness = 1.dp, modifier = Modifier.weight(1f))
        CustomCompanionText(textId = R.string.or, modifier = Modifier.padding(horizontal = 4.dp))
        HorizontalDivider(Modifier.weight(1f), thickness = 1.dp)
    }
}