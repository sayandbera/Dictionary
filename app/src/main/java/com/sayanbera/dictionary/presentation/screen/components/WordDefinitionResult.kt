package com.sayanbera.dictionary.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayanbera.dictionary.domain.model.WordItem

@Composable
fun WordDefinitionContent(wordItem: WordItem) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 32.dp)
    ) {
        items(wordItem.meanings) { meaning ->
            val meaningIndex = wordItem.meanings.indexOf(meaning)
            val brush = Brush.horizontalGradient(
                listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.primary.copy(0.3f),
                    Color.Transparent
                )
            )
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${meaningIndex + 1}.",
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(brush = brush),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "  ${meaning.partOfSpeech}",
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (meaning.definitions.first().definition.isNotEmpty()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Definition:",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.width(100.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = meaning.definitions.first().definition,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    }
                }

                if (meaning.definitions.first().example.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        Text(
                            text = "Example:",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.width(100.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = meaning.definitions.first().example,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

