package com.example.prototype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.prototype.ui.common.TextHeader
import com.example.prototype.ui.theme.PrototypeTheme

private const val TAG = "SignUpScreen"

@Composable
fun SignUpWithPasswordScreen(
    onSignUpWithPasswordButtonClicked: () -> Unit = {},
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageHeaderLogo()
        TextHeader(text = stringResource(R.string.create_account))

        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFFF0EFF0),
                    shape = RoundedCornerShape(size = 25.dp)
                )
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = "",
                label = { Text(stringResource(R.string.email_label)) },
                onValueChange = { },
                shape = RoundedCornerShape(15.dp),
            )
            OutlinedTextField(
                value = "",
                label = { Text(stringResource(R.string.password)) },
                onValueChange = { },
                shape = RoundedCornerShape(15.dp),
            )

            Spacer(Modifier.padding(20.dp))

            Button(
                onClick = onSignUpWithPasswordButtonClicked,
                Modifier.widthIn(min = 280.dp)
            ) {
                Text(text = stringResource(R.string.sign_up))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpWithPasswordScreenPreview() {
    PrototypeTheme {
        SignUpWithPasswordScreen(
            onSignUpWithPasswordButtonClicked = {},
        )
    }
}