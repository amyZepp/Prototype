package com.example.prototype.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prototype.R
import com.example.prototype.ui.theme.PrototypeTheme

@Composable
fun PasskeyInfo() {
    Row(
        Modifier.fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.passkey_info),
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = stringResource(R.string.learn_more),
                style = MaterialTheme.typography.bodyLarge)
        }
        Image(
            painter = painterResource(R.drawable.passkey_logo),
            contentDescription = "passkey logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(175.dp)
        )
    }
}


@Preview (showBackground = true)
@Composable
fun PasskeyInfoPreview(){
    PrototypeTheme {
        PasskeyInfo()
    }
}