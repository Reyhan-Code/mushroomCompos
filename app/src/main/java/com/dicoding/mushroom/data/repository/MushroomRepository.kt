package com.dicoding.mushroom.data.repository

import com.dicoding.mushroom.data.model.Mushroom
import com.dicoding.mushroom.data.model.MushroomData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MushroomRepository {

    private val mushroomRepository = mutableListOf<Mushroom>()

    init {
        if (mushroomRepository.isEmpty()) {
            MushroomData.mushroomSrc.forEach {
                mushroomRepository.add(Mushroom(it.id, it.name, it.image, it.edible, it.description))
            }
        }
    }

    fun getMushroom( ): Flow<List<Mushroom>> {
        return flowOf(mushroomRepository)
    }

    fun getMushroomById(mushroomId: Long): Mushroom {
        return mushroomRepository.first{
            it.id == mushroomId
        }
    }

    companion object {
        @Volatile
        private var instance: MushroomRepository? = null

        fun getInstance(): MushroomRepository = instance ?: synchronized(this) {
            MushroomRepository().apply { instance = this }
        }
    }
}