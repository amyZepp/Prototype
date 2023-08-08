package com.example.prototype.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CreatePublicKeyCredentialResponse
import com.example.prototype.R
import com.example.prototype.ui.common.LogoHeading
import com.example.prototype.ui.common.ShrineButton
import com.example.prototype.ui.theme.PrototypeTheme
import com.example.prototype.ui.theme.dark_button
import com.example.prototype.ui.theme.light_button
import com.example.prototype.ui.theme.md_theme_light_primary

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    onSignInRequest: () -> Unit,
    onRegisterRequest: () -> Unit,
    onRegisterResponse: (CreatePublicKeyCredentialResponse) -> Unit,
    proceedButtonClicked: () -> Unit,
    ) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeading()
        Spacer(modifier = Modifier.padding(20.dp))
        ShrineButton(
            color = dark_button,
            onClick = { onSignInRequest() }) {
            Text(text = stringResource(id = R.string.sign_in))
        }

        ShrineButton(
            color = light_button,
            onClick = { onRegisterRequest() }) {
            Text(text = stringResource(id = R.string.sign_up))
        }

        ShrineButton(
            color = md_theme_light_primary,
            onClick = { proceedButtonClicked() }) {
            Text(text = "Proceed without Signing In")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    PrototypeTheme {
        HomeScreen(
            onSignInRequest  = {},
            onRegisterRequest = {},
            onRegisterResponse = {},
            proceedButtonClicked = {},
        )
    }
}