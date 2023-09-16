package com.example.prototype.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prototype.R
import com.example.prototype.ui.theme.PrototypeTheme

@Composable
fun TextHeader(
    text: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 5.dp, bottom = 15.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 30.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight(400),
            )
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