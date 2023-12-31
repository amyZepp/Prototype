package com.example.prototype.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prototype.ui.theme.PrototypeTheme
import com.example.prototype.ui.theme.dark_button
import com.example.prototype.ui.theme.light_button

@Composable
fun ShrineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = dark_button,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = ButtonShape,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        shape = shape,
        color = color,
        border = border,
        modifier = modifier
            .clip(shape)
            .padding(bottom = 10.dp)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        Row(
            Modifier
                .defaultMinSize(
                    minWidth = 300.dp,
                    minHeight = ButtonDefaults.MinHeight
                )
                .indication(interactionSource, rememberRipple())
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

private val ButtonShape = CutCornerShape(10)

@Preview()
@Composable
private fun ButtonPreview() {
    PrototypeTheme {
        ShrineButton(
            color = light_button,
            onClick = {}) {
            Text(text = "Demo")
        }
    }
}
