package com.dicoding.mushroom.helper.di

import com.dicoding.mushroom.data.repository.MushroomRepository

object Injection {
    fun provideRepository(): MushroomRepository {
        return MushroomRepository.getInstance()
    }
}