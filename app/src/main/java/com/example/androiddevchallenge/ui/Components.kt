package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.PuppiesViewModel.Companion.MOCKS
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun PuppyAvatar(
    puppy: PuppiesViewModel.Puppy,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = puppy.image),
        contentDescription = "puppy's picture",
        contentScale = ContentScale.Inside,
        modifier = Modifier
            .clip(CircleShape)
            .background(color = puppy.backgroundColor)
            .size(size)
            .then(modifier),
    )
}

@Preview("Avatar", widthDp = 96, heightDp = 96)
@Composable
fun AvatarPreview() {
    MyTheme {
        PuppyAvatar(puppy = MOCKS.first(), size = 96.dp)
    }
}