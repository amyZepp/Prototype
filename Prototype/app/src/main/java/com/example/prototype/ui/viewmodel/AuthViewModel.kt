package com.example.prototype.ui.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val TAG = "AuthViewModel"

data class AppState(
    val justOpened: Boolean = true,
    val isSignedIn: Boolean = false,
    val currentEmail: String = "",
    val displayName: String = "",
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val loginErrorMessage: String = "",
    val wasJustCancelled: Boolean = false,
    val isFirstTextboxLaunch: Boolean = true,
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    var appState by mutableStateOf(AppState())

    private companion object {
        const val RC_SIGN_IN = 123
    }

    private val _isSignedIn = MutableLiveData<Boolean>()

    val isSignedIn: LiveData<Boolean> get() = _isSignedIn

    private val credentialManager by lazy {
        CredentialManager.create(application)
    }
    private val signedInPasswordCredential = MutableStateFlow<PasswordCredential?>(null)

    fun signUpWithPasskey(){}
    fun signInWithPasskey(){}

    fun signInWithSavedCredential(activity: Activity) {
        viewModelScope.launch {
            try {
                val passwordCredential = getCredential(activity) ?: return@launch

                val signInSuccess = true
                // sign in logic using the returned password credential

                if (signInSuccess) {
                    //Indicate to the UI that we're now signed in.
                    signedInPasswordCredential.value = passwordCredential
                }
            }
            catch (e: Exception) {
                Log.e(TAG, "signInWithSavedCredential error", e)
            }
        }
    }

    private suspend fun getCredential(context: Context): PasswordCredential? {
        try {
            val getCredRequest = GetCredentialRequest(
                listOf(GetPasswordOption())
            )

            //pick saved credential
            val credentialResponse = credentialManager.getCredential(
                request = getCredRequest,
                context = context,
            )

            //Return credential
            return credentialResponse.credential as? PasswordCredential
        }

        catch (e: GetCredentialCancellationException) {
            return null
        }
        catch (e: NoCredentialException) {
            return null
        }
        catch (e: GetCredentialException) {
            Log.e(TAG, "GetCredentialException", e)
            throw e
        }
    }

    private suspend fun saveCredential(context: Context, username: String, password: String) {
        try {
            credentialManager.createCredential(
                request = CreatePasswordRequest(username, password),
                context = context,
            )
            Log.v(TAG, "createCredential success")

            signedInPasswordCredential.value = PasswordCredential(username, password)
        }
        catch (e: CreateCredentialCancellationException) {
            Log.v(TAG, "Cancelled")
        }
        catch (e: CreateCredentialException) {
            Log.v(TAG, "CreateCredentialException error", e)
        }
    }

    fun signIn(activity: Activity) {
        Log.d("TAG", "Sign-In")
        // Simulating a delay of 3 seconds using coroutines
        viewModelScope.launch {
            // Pause for 3 seconds
            delay(3000)
            // Update the isSignedIn value after the delay
            _isSignedIn.value = true
        }
    }

    fun signOut() {
        signedInPasswordCredential.value = null
    }
}