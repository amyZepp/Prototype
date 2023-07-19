package com.example.prototype.ui.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
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
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val TAG = "AuthViewModel"

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val credentialManager by lazy {
        CredentialManager.create(application)
    }
    private val signedInPasswordCredential = MutableStateFlow<PasswordCredential?>(null)

    fun signInOrSignUpWithEnteredCredential(activity: Activity, username: String, password: String) {
        viewModelScope.launch {
            val signInSuccess = true
            // sign in or sign up logic here

            if (signInSuccess) {
                signedInPasswordCredential.value = PasswordCredential(username, password)

                saveCredential(activity, username, password)
            }
        }
    }

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

    fun signOut() {
        signedInPasswordCredential.value = null
    }
}