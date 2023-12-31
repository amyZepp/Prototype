package com.example.prototype.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.prototype.R
import com.example.prototype.ui.common.LogoHeading
import com.example.prototype.ui.theme.PrototypeTheme

private const val TAG = "SignOutScreen"

@Composable
fun SignOutScreen() {

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LogoHeading()
        Text(text = stringResource(R.string.signed_out_title))
    }
}
@Preview(showBackground = true)
@Composable
fun SignOutScreenPreview() {
    val navController = rememberNavController()
    PrototypeTheme() {
        SignOutScreen()
    }
}