package io.jamshid.navoiyterminology.di

import androidx.room.Room
import io.jamshid.navoiyterminology.data.local.database.TermDatabase
import io.jamshid.navoiyterminology.domain.use_cases.*
import io.jamshid.navoiyterminology.ui.main.home.HomeViewModel
import io.jamshid.navoiyterminology.ui.main.info.InfoViewModel
import io.jamshid.navoiyterminology.ui.main.saved.SavedWordsViewModel
import io.jamshid.navoiyterminology.utils.Constants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(get(), TermDatabase::class.java, Constants.DB_NAME).build()
    }

    single {
        val database = get<TermDatabase>()
        database.termDao()
    }
}

val useCaseModule = module {
    single { InsertTerm(get()) }
    single { UpdateTerm(get()) }
    single { GetAllTerm(get()) }
    single { GetTermByStatus(get()) }
    single {
        UseCases(
            insertTerm = get(),
            updateTerm = get(),
            allTerm = get(),
            getTermByStatus = get()
        )
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(),get()) }
    viewModel { InfoViewModel(get()) }
    viewModel { SavedWordsViewModel(get()) }
}
