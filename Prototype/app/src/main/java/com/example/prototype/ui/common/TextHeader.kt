package com.example.prototype.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prototype.ui.theme.PrototypeTheme

@Composable
fun TextHeader(
    text: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TextHeaderPreview(){
    PrototypeTheme {
        TextHeader(text = "Heading Text")
    }
}