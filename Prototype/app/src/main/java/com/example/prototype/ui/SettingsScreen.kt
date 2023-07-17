package com.example.prototype.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prototype.R
import com.example.prototype.ui.common.LogoHeading
import com.example.prototype.ui.theme.PrototypeTheme

private const val TAG = "SettingsScreen"

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LogoHeading(stringResource(R.string.welcome_to))
        Spacer(modifier = Modifier.padding(10.dp))
        Text(stringResource(R.string.settings))
        Spacer(modifier = Modifier.padding(25.dp))
        Button(onClick = { /* TODO */ }) {
            Text(text = "Setting1")
        }
        Button(onClick = { /* TODO */ }) {
            Text(text = "Setting2")
        }
        Button(onClick = { /* TODO */ }) {
            Text(text = "Setting3")
        }
        Button(onClick = { /* TODO */ }) {
            Text(text = "Setting4")
        }
        Button(onClick = { /* TODO */ }) {
            Text(text = "Setting5")
        }
        Button(onClick = { /* TODO */ }) {
            Text(text = "Setting6")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview(){
    PrototypeTheme {
        SettingsScreen()
    }
}