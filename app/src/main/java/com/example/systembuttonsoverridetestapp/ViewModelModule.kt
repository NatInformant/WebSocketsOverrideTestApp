package com.example.systembuttonsoverridetestapp

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel {
            MainViewModel()
        }
    }
}