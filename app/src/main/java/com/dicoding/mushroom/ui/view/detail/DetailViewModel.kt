package com.dicoding.mushroom.ui.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mushroom.data.model.Mushroom
import com.dicoding.mushroom.data.repository.MushroomRepository
import com.dicoding.mushroom.helper.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MushroomRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Mushroom>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Mushroom>>
        get() = _uiState

    fun getMushroomById(mushromId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMushroomById(mushromId))
        }
    }
}