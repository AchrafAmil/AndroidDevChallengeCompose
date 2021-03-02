package com.example.androiddevchallenge.ui

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.R
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

class PuppiesViewModel : ViewModel() {
    private val _content = MutableStateFlow<UiModel>(UiModel.Puppies(MOCKS))
    val content: LiveData<UiModel> = _content.asLiveData(viewModelScope.coroutineContext)

    sealed class UiModel {
        data class Puppies(val puppies: List<Puppy>) : UiModel()
    }

    data class Puppy(
        val id: String = UUID.randomUUID().toString(),
        val name: String = petsNames.random(),
        @DrawableRes val image: Int = avatars.random(),
        val backgroundColor: Color = avatarColors.random(),
        val description: String = petsDescs.random(),
        val gender: String = genders.random(),
    )

    companion object {
        val MOCKS = (1..10).toList().map { combinePuppy(it) }

        private fun combinePuppy(index: Int): Puppy {
            return Puppy(
                name = petsNames[index % petsNames.size],
                image = avatars[index % avatars.size],
                backgroundColor = avatarColors[index % avatarColors.size],
                description = petsDescs[index % petsDescs.size],
                gender = genders[index % genders.size],
            )
        }
    }
}


private val petsNames = listOf(
    "Hope",
    "Joy",
    "Rocky",
    "Praline",
    "Kaki",
    "Lili",
    "Rex",
)

private val petsDescs = listOf(
    "Very adorable",
    "Will fill your life with love and good vibes",
    "Needs your love",
    "The most faithful creature",
    "This puppy can save a life, adopt gracefully",
    "Isn't it cute ?",
)


private val avatarColors = listOf(
    Color(0xFFFFC107),
    Color(0xFF00BCD4),
    Color(0xFF9E9E9E),
    Color(0xFFFFCCBC),
)

private val avatars = listOf(
    R.drawable.a,
    R.drawable.b,
    R.drawable.c,
    R.drawable.d,
    R.drawable.e,
    R.drawable.f,
)

private val genders = listOf(
    "♂",
    "♀"
)
