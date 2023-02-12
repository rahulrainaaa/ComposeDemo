package com.example.testcompose3app.di

import android.content.Context
import androidx.room.Room
import com.example.testcompose3app.db.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideYourDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, AppDb::class.java, "app_db_name").build()

    @Singleton
    @Provides
    fun provideYourDao(db: AppDb) = db.getBookDao()
}