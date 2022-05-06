package io.jamshid.navoiyterminology.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.jamshid.navoiyterminology.data.local.dao.TermDao
import io.jamshid.navoiyterminology.data.local.entities.Term
import io.jamshid.navoiyterminology.utils.Constants

@Database(entities = [Term::class], version = Constants.DB_VERSION, exportSchema = false)

abstract class TermDatabase : RoomDatabase() {

    abstract fun termDao(): TermDao

}