package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferenceRepository

/** Created by qq_3000 on 22.10.2019. */
class ProfileViewModel : ViewModel() {
    private val repository: PreferenceRepository = PreferenceRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()

    init {
        profileData.value = repository.getProfile()
        Log.d("DEV-2019", "init-1: ${appTheme.value}")
        appTheme.value = repository.getAppTheme()
        Log.d("DEV-2019", "init-2: ${appTheme.value}")
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getProfileData(): LiveData<Profile> = profileData

    fun getTheme(): LiveData<Int> = appTheme

    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            Log.d("DEV-2019", "switchTheme-IF: ${appTheme.value}")
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            Log.d("DEV-2019", "switchTheme-ELSE: ${appTheme.value}")
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        Log.d("DEV-2019", "switchTheme: ${appTheme.value}")
        repository.saveAppTheme(appTheme.value!!)
    }
}