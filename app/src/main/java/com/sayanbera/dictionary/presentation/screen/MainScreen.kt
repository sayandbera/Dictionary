package com.sayanbera.dictionary.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sayanbera.dictionary.presentation.MainEvent
import com.sayanbera.dictionary.presentation.MainViewModel
import com.sayanbera.dictionary.presentation.screen.components.ShimmerEffectLoading
import com.sayanbera.dictionary.presentation.screen.components.WordDefinitionContent

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val mainState = viewModel.mainState.collectAsStateWithLifecycle().value
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            OutlinedTextField(
                value = mainState.inputWord,
                onValueChange = {
                    viewModel.onEvent(
                        MainEvent.OnInputWordChange(inputWord = it)
                    )
                },
                placeholder = {
                    Text(
                        text = "Enter a word...",
                        color = MaterialTheme.colorScheme.onSurface.copy(0.7f)
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(visible = mainState.inputWord.isNotEmpty()) {
                        IconButton(
                            modifier = Modifier.padding(end = 8.dp),
                            onClick = {
                                keyboardController?.hide()
                                viewModel.onEvent(
                                    MainEvent.OnSearchClick(context)
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon"
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary.copy(0.6f),
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(0.6f),
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions {
                    keyboardController?.hide()
                    if (mainState.inputWord.isNotEmpty()) {
                        viewModel.onEvent(
                            MainEvent.OnSearchClick(context)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 6.dp)
            )
        }
    ) { paddingValues ->
        mainState.wordItem?.let { wordItem ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 26.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = wordItem.word,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = wordItem.phonetic,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                }

                // Definition section
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(0.3f))
                ) {
                    if (mainState.isLoading) {
                        Column {
                            repeat(4) {
                                ShimmerEffectLoading()
                            }
                        }
                    } else {
                        WordDefinitionContent(wordItem = wordItem)
                    }
                }
            }
        }
    }
}








