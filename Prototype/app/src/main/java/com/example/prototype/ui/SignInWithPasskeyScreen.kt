package com.example.prototype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prototype.R
import com.example.prototype.ui.common.PageHeaderLogo
import com.example.prototype.ui.common.PasskeyInfo
import com.example.prototype.ui.common.TextHeader
import com.example.prototype.ui.theme.PrototypeTheme

private const val TAG = "SignInScreen"

@Composable
fun SignInWithPasskeyScreen(
    onSignInButtonClicked: () -> Unit,
) {
    PageHeaderLogo()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
        ) {
            TextHeader(text = stringResource(R.string.sign_in))
            PasskeyInfo()
        }
        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFFF0EFF0),
                    shape = RoundedCornerShape(size = 25.dp)
                )
                .height(200.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = "",
                label = { Text("Username") },
                onValueChange = { },
                shape = RoundedCornerShape(15.dp),
            )
            Spacer(Modifier.padding(20.dp))
            Button(
                onClick = onSignInButtonClicked,
                Modifier.widthIn(min = 280.dp)
            ) { Text(text = stringResource(R.string.sign_in)) }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignInWithPasskeyScreenPreview(){
    PrototypeTheme {
        SignInWithPasskeyScreen(
            onSignInButtonClicked = {},
        )
    }
}