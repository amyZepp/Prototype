package com.example.prototype.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prototype.R
import com.example.prototype.ui.common.PageHeaderLogo
import com.example.prototype.ui.common.ShrineButton
import com.example.prototype.ui.common.TextHeader
import com.example.prototype.ui.theme.PrototypeTheme

private const val TAG = "MainMenu"

@Composable
fun MainMenuScreen(
    onDoSomethingButtonClicked: () -> Unit,
    onHelpButtonClicked: () -> Unit,
    onSignOutButtonClicked: () -> Unit,
){
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
            TextHeader(text = stringResource(R.string.main_menu))
        }
        Spacer(modifier = Modifier.padding(30.dp))

        ShrineButton(
            onClick = onDoSomethingButtonClicked) {
            Text(text = ("Go to the App")) }

        ShrineButton(
            onClick = onHelpButtonClicked,
        ) { Text(stringResource(R.string.help)) }

        ShrineButton(
            onClick = onSignOutButtonClicked,
        ) { Text(stringResource(R.string.sign_out)) }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuScreenPreview() {
    PrototypeTheme {
        MainMenuScreen(
            onDoSomethingButtonClicked = {},
            onHelpButtonClicked = {},
            onSignOutButtonClicked = {},
        )
    }
}