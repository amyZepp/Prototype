package com.example.prototype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prototype.R
import com.example.prototype.ui.common.LogoHeading
import com.example.prototype.ui.theme.PrototypeTheme

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    PasskeySignInButtonClicked: () -> Unit,
    PasswordSignInButtonClicked: () -> Unit,
    RegisterButtonClicked: () -> Unit,
    ProceedButtonClicked: () -> Unit,
    ) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeading(text = stringResource(R.string.welcome_to))
        Column(
            modifier = Modifier
                .size(300.dp)
                .background(
                    color = Color(0xFFF0EFF0),
                    shape = RoundedCornerShape(size = 40.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = PasskeySignInButtonClicked,
                Modifier.widthIn(min = 250.dp)
            ) { Text(text = "Sign in with a Passkey") }

            Button(
                onClick = PasswordSignInButtonClicked,
                Modifier.widthIn(min = 250.dp)
            ) { Text(text = "Sign in with a Password") }

            Button(
                onClick = RegisterButtonClicked,
                Modifier.widthIn(min = 250.dp)
            ) { Text(text = "Register a New Account") }

            Button(
                onClick = ProceedButtonClicked,
                Modifier.widthIn(min = 250.dp)
            ) { Text(text = "Proceed without Signing In") }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    PrototypeTheme {
        HomeScreen(
            PasskeySignInButtonClicked  = {},
            PasswordSignInButtonClicked = {},
            RegisterButtonClicked = {},
            ProceedButtonClicked = {},
        )
    }
}