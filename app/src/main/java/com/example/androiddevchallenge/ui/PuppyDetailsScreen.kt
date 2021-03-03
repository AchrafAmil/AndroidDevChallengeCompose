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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyDetailsScreen(
    puppy: PuppiesViewModel.Puppy,
    onBackFromDetails: () -> Unit
) {
    ConstraintLayout {
        val (avatar, card) = createRefs()

        Surface(
            elevation = 2.dp,
            modifier = Modifier
                .clip(RoundedCornerShape(15))
                .constrainAs(card) {
                    top.linkTo(parent.top, margin = 96.dp)
                    bottom.linkTo(parent.bottom, margin = 200.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                }
                .fillMaxWidth(0.9f),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 64.dp)
            ) {
                Text(
                    text = puppy.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(24.dp)
                )
                Text(
                    text = puppy.description,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(24.dp)
                )

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .padding(top = 48.dp, bottom = 24.dp)
                        .fillMaxWidth(0.7f)
                ) {
                    Text(text = "ADOPT")
                }
            }
        }

        PuppyAvatar(
            puppy = puppy,
            modifier = Modifier
                .constrainAs(avatar) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            size = 128.dp,
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PuppyDetailsLightPreview() {
    MyTheme {
        PuppyDetailsScreen(PuppiesViewModel.MOCKS.first()) { }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun PuppyDetailsDarkPreview() {
    MyTheme(darkTheme = true) {
        PuppyDetailsScreen(PuppiesViewModel.MOCKS.first()) { }
    }
}
