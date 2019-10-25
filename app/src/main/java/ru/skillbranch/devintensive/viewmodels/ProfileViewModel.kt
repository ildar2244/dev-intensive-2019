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
    private val repositoryError = MutableLiveData<Boolean>()
    private val isRepoError = MutableLiveData<Boolean>()

    init {
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getProfileData(): LiveData<Profile> = profileData

    fun getTheme(): LiveData<Int> = appTheme

    fun getRepositoryError(): LiveData<Boolean> = repositoryError

    fun getIsRepoError():LiveData<Boolean> = isRepoError

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

    fun onRepositoryChanged(repository: String) {
        repositoryError.value = isValidateRepository(repository)
    }


    fun onRepoEditCompleted(isError: Boolean) {
        isRepoError.value = isError
    }

    private fun isValidateRepository(repoText: String): Boolean {
        val regexStr = "^(https:\\/\\/)?(www\\.)?(github\\.com\\/)(?!(${getRegexExceptions()})" +
                "(?=\\/|\$))[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,38}(\\/)?$"
        val regex = Regex(regexStr)

        return (repoText.isNotEmpty() && !regex.matches(repoText))
    }

    private fun getRegexExceptions(): String {
        val exceptions = arrayOf(
            "enterprise", "features", "topics", "collections", "trending", "events",
            "marketplace", "pricing", "nonprofit", "customer-stories", "security",
            "login", "join"
        )
        return exceptions.joinToString("|")
    }
}