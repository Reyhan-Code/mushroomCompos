package com.dicoding.mushroom.ui.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mushroom.data.model.Mushroom
import com.dicoding.mushroom.data.repository.MushroomRepository
import com.dicoding.mushroom.helper.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MushroomRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Mushroom>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Mushroom>>>
        get() = _uiState

    fun getAllMoviesAnime() {
        viewModelScope.launch {
            repository.getMushroom()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { moviesAnime ->
                    _uiState.value = UiState.Success(moviesAnime)
                }
        }
    }
}