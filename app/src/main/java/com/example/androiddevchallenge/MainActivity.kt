/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.PuppiesScreen
import com.example.androiddevchallenge.ui.PuppiesViewModel
import com.example.androiddevchallenge.ui.PuppiesViewModel.Companion.MOCKS
import com.example.androiddevchallenge.ui.PuppyDetailsScreen
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val viewModel: PuppiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val state = viewModel.content.observeAsState()
                MyApp(
                    state,
                    onPuppyClicked = viewModel::onPuppyClicked,
                    onBackFromDetails = viewModel::onPuppyDetailsBackClicked,
                )
            }
        }
    }

    override fun onBackPressed() {
        if (!viewModel.onBackPressed()) {
            super.onBackPressed()
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(
    state: State<PuppiesViewModel.UiModel?>,
    onPuppyClicked: (puppyId: String) -> Unit = { },
    onBackFromDetails: () -> Unit = { },
) {
    Surface(
        elevation = 0.dp,
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Crossfade(targetState = state.value) { currentState ->
            when (currentState) {
                is PuppiesViewModel.UiModel.Puppies -> PuppiesScreen(
                    currentState.puppies,
                    onPuppyClicked
                )
                is PuppiesViewModel.UiModel.PuppyDetails -> PuppyDetailsScreen(
                    currentState.puppy,
                    onBackFromDetails
                )
                null -> Unit /* no-op */
            }.exhaustive
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(mutableStateOf(PuppiesViewModel.UiModel.Puppies(MOCKS)))
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(mutableStateOf(PuppiesViewModel.UiModel.Puppies(MOCKS)))
    }
}

val Unit.exhaustive: Unit get() = this
