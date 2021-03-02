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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Inside
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.PuppiesViewModel
import com.example.androiddevchallenge.ui.PuppiesViewModel.Companion.MOCKS
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val viewModel: PuppiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val state = viewModel.content.observeAsState()
                MyApp(state)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(state: State<PuppiesViewModel.UiModel?>) {
    Surface(
        elevation = 0.dp,
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            when (val currentState = state.value) {
                is PuppiesViewModel.UiModel.Puppies -> {
                    item {
                        Text(
                            text = "Pets for you",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .padding(24.dp)
                        )
                    }
                    items(currentState.puppies) { puppy ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(50))
                                .clickable { }
                                .background(color = MaterialTheme.colors.surface),
                            elevation = 2.dp
                        ) {
                            Row(
                                verticalAlignment = CenterVertically,
                            ) {
                                Surface(
                                    color = puppy.backgroundColor,
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(96.dp),
                                ) {
                                    Image(
                                        painter = painterResource(id = puppy.image),
                                        contentDescription = "puppy's picture",
                                        contentScale = Inside,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .fillMaxWidth()
                                            .fillMaxHeight(),
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .weight(4f)
                                        .fillMaxHeight()
                                ) {
                                    Text(
                                        text = puppy.name,
                                        style = MaterialTheme.typography.h5,
                                        modifier = Modifier
                                            .padding(
                                                start = 16.dp,
                                                top = 8.dp,
                                                bottom = 8.dp,
                                                end = 16.dp
                                            )
                                    )

                                    Text(
                                        text = puppy.description,
                                        style = MaterialTheme.typography.caption,
                                        modifier = Modifier
                                            .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                                    )
                                }


                                Text(
                                    text = puppy.gender,
                                    style = MaterialTheme.typography.h5,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 16.dp, end = 36.dp)
                                        .fillMaxHeight()
                                )
                            }
                        }
                    }
                }
                null -> {

                }
            }
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
