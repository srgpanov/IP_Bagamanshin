package com.srgpanov.ip_bagamanshin.di

import com.srgpanov.ip_bagamanshin.di.remote.RemoteModule
import com.srgpanov.ip_bagamanshin.di.view_models.ViewModelsModule
import com.srgpanov.ip_bagamanshin.screens.authorization.AuthFragment
import com.srgpanov.ip_bagamanshin.screens.splash.SplashFragment
import com.srgpanov.ip_bagamanshin.screens.web_screen.WebFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class, ViewModelsModule::class])
interface AppComponent {
    fun injectSplashFragment(fragment: SplashFragment)
    fun injectAuthFragment(fragment: AuthFragment)
    fun injectWebFragment(fragment: WebFragment)
}