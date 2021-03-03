package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun PuppiesScreen(
    puppies: List<PuppiesViewModel.Puppy>,
    onPuppyClicked: (puppyId: String) -> Unit
) {
    LazyColumn {
        item {
            Text(
                text = "Pets for you",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(24.dp)
            )
        }
        items(puppies) { puppy ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(50))
                    .clickable { onPuppyClicked(puppy.id) }
                    .background(color = MaterialTheme.colors.surface),
                elevation = 2.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PuppyAvatar(puppy, size = 96.dp)

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
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PuppiesLightPreview() {
    MyTheme {
        PuppiesScreen(PuppiesViewModel.MOCKS) { }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun PuppiesDarkPreview() {
    MyTheme(darkTheme = true) {
        PuppiesScreen(PuppiesViewModel.MOCKS) { }
    }
}
