package com.example.prototype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.prototype.ui.common.PasskeyInfo
import com.example.prototype.ui.common.TextHeader
import com.example.prototype.ui.theme.PrototypeTheme

private const val TAG = "onSignUpWithPasskeyScreen"

@Composable
fun SignUpWithPasskeyScreen(
    onRegisterButtonClicked: () -> Unit = {},
    onSignUpWithOtherMethodClicked: () -> Unit = {}
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
            TextHeader(text = stringResource(R.string.create_account))
            PasskeyInfo()
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

                Spacer(Modifier.padding(20.dp))

                Button(
                    onClick = { onRegisterButtonClicked() },
                    Modifier.widthIn(min = 280.dp)
                ) {
                    Text(text = stringResource(R.string.sign_up)) }

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = stringResource(R.string.sign_up_instead),
                    modifier = Modifier.clickable(
                        onClick = { onSignUpWithOtherMethodClicked() }))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpWithPasskeyScreenPreview() {
    PrototypeTheme {
        SignUpWithPasskeyScreen(
            onRegisterButtonClicked = {->},
            onSignUpWithOtherMethodClicked = {->}
        )
    }
}