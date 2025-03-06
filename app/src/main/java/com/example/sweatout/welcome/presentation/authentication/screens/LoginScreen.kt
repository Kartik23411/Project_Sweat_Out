package com.example.sweatout.welcome.presentation.authentication.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.authentication.screens.components.BasicLoginAndSignUpScreen

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = hiltViewModel(),
    onSignUpClick: () -> Unit,
    onAuthenticationSucceed: () -> Unit
) {
    BasicLoginAndSignUpScreen(
        modifier = modifier,
        viewModel = viewModel,
        onTextButtonClick = { onSignUpClick() },
        onAuthenticationSucceed = {onAuthenticationSucceed()},
        onAuthenticationButtonClick = { email, password ->
            viewModel.signInUserByEmail(email, password)
        },
        textButtonText = R.string.signup,
        textButtonIntroText = R.string.signup_text_intro,
        authenticationButtonText = R.string.signin,
        headlineText = R.string.login_headline,
        isLoginScreen = true
    )
}

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = hiltViewModel(),
    onSignInClick: () -> Unit,
    onAuthenticationSucceed: () -> Unit
) {
    BasicLoginAndSignUpScreen(
        modifier = modifier,
        onTextButtonClick = { onSignInClick() },
        viewModel = viewModel,
        onAuthenticationSucceed = {onAuthenticationSucceed()},
        onAuthenticationButtonClick = { email, password ->
            viewModel.signUpUserByEmail(email, password)
        },
        textButtonText = R.string.signin,
        textButtonIntroText = R.string.login_text_intro,
        authenticationButtonText = R.string.signup,
        headlineText = R.string.signup_headline,
        isLoginScreen = false
    )
}