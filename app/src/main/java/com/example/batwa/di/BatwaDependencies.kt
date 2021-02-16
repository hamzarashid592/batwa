package com.example.batwa.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.room.CoroutinesRoom
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.batwa.database.BatwaDAO
import com.example.batwa.database.BatwaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


@Module
@InstallIn(SingletonComponent::class)
object BatwaDependencies {

    @Provides
    fun instantiateDB(app : Application) : BatwaDatabase{
        return Room.databaseBuilder(app,BatwaDatabase::class.java,"BatwaDB")
            .build()
    }

    @Provides
    fun instantiateDAO(batwaDatabase: BatwaDatabase) : BatwaDAO{
        return batwaDatabase.getDAO()
    }

    @Provides
    fun provideCoroutineScope() : CoroutineScope{
        return CoroutineScope(SupervisorJob())
    }
}