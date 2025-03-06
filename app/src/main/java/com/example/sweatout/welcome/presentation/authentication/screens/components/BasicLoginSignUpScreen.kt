package com.example.sweatout.welcome.presentation.authentication.screens.components

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.ThreeBounceAnimation
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel

@Composable
fun BasicLoginAndSignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel,
    onTextButtonClick: () -> Unit,
    onAuthenticationButtonClick: (String, String) -> Unit,
    onAuthenticationSucceed:()->Unit,
    @StringRes authenticationButtonText: Int,
    @StringRes textButtonText: Int,
    @StringRes headlineText: Int,
    @StringRes textButtonIntroText: Int,
    isLoginScreen: Boolean
) {
    // todo in the viewmodel add the delay before executing the request so that the animation can be displayed
    val email = rememberSaveable { mutableStateOf<String?>("") }
    val password = rememberSaveable { mutableStateOf<String?>("") }
    val authState by viewModel.authResult.collectAsState()
    var isLoading = authState.isLoading
    val context = LocalContext.current

    LaunchedEffect(authState.isSuccess) {
        if (authState.isSuccess)
            onAuthenticationSucceed()
    }
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
                    CustomTextButton(
                        R.string.forget_password,
                        onClick = { Log.e("authentication", "Forget click") })
                }
                else Spacer(Modifier.height(16.dp))
            }

            RememberButton()

            Spacer(Modifier.height(16.dp))

            MyButton(
                modifier = Modifier
                        .noRippleClickable {}
                        .fillMaxWidth(fraction = 1f)
                        .height(50.dp),
                onClick = {
                    onAuthenticationButtonClick(email.value!!, password.value!!)
                },
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                buttonText = stringResource(authenticationButtonText),
                elevation = 4.dp,
                isLoading = isLoading
            )
        }

        DividerLine()

        LoginOptionRow(
            firstLoginAction = { Log.e("authentication", "Clicked 1") },
            secondLoginAction = { Log.e("authentication", "Clicked 1") },
            thirdLoginAction = { Log.e("authentication", "Clicked 1") },
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

@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonColor: Color = MaterialTheme.colorScheme.primaryContainer,
    buttonText: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    roundRadius: Int = 50,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    style: TextStyle = MaterialTheme.typography.labelLarge,
    isLoading:Boolean
) {
    Button(
        onClick = { onClick() },
        border = border,
        elevation = ButtonDefaults.buttonElevation(elevation),
        colors = ButtonDefaults.buttonColors(buttonColor),
        shape = RoundedCornerShape(percent = roundRadius),
        modifier = modifier
    ) {
        if (isLoading) {
            ThreeBounceAnimation()
        }
        else {
            Text(
                text = buttonText,
                color = textColor,
                style = style
            )
        }
    }
    }
