package com.example.sweatout.welcome.presentation.login_signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.login_signup.components.BasicLoginAndSignUpScreen

@Composable
fun LoginOptionsScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = viewModel(),
    onSignUpClick:()->Unit
) {
    BasicLoginAndSignUpScreen(
        modifier = modifier,
        onTextButtonClick = {onSignUpClick()},
        onAuthenticationButtonClick = {viewModel.signInUserByEmail()},
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
    viewModel: WelcomeModuleViewModel = viewModel(),
    onSignInClick:()->Unit
) {
    BasicLoginAndSignUpScreen(
        modifier = modifier,
        onTextButtonClick = {onSignInClick()},
        onAuthenticationButtonClick = {viewModel.signUpUserByEmail()},
        textButtonText = R.string.signin,
        textButtonIntroText = R.string.login_text_intro,
        authenticationButtonText = R.string.signup,
        headlineText = R.string.signup_headline,
        isLoginScreen = false
    )
}