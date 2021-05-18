package com.github.csandiego.govtechassessment.hilt

import android.content.Context
import androidx.room.Room
import com.github.csandiego.govtechassessment.dao.NoteDao
import com.github.csandiego.govtechassessment.room.GovTechAssessmentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun database(@ApplicationContext context: Context): GovTechAssessmentDatabase =
        Room.databaseBuilder(context, GovTechAssessmentDatabase::class.java, "GovTechAssessment")
            .build()

    @Singleton
    @Provides
    fun noteDao(database: GovTechAssessmentDatabase): NoteDao = database.noteDao()
}