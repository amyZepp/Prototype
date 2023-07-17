package com.example.prototype

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.prototype.data.DataProvider
import com.example.prototype.ui.AccountRecoveryScreen
import com.example.prototype.ui.HelpScreen
import com.example.prototype.ui.HomeScreen
import com.example.prototype.ui.MainMenuScreen
import com.example.prototype.ui.PlaceholderScreen
import com.example.prototype.ui.SettingsScreen
import com.example.prototype.ui.SignInWithPasskeyScreen
import com.example.prototype.ui.SignInWithPasswordScreen
import com.example.prototype.ui.SignOutScreen
import com.example.prototype.ui.SignUpWithPasskeyScreen
import com.example.prototype.ui.SignUpWithPasswordScreen
import com.example.prototype.ui.viewmodel.AuthViewModel

private const val TAG = "ApplicationScreen"

enum class ApplicationScreen(@StringRes val title: Int){
    AccountRecovery(title = R.string.account_recovery),
    Help(title = R.string.help),
    Home(title = R.string.home),
    MainMenu(title = R.string.main_menu),
    Placeholder(title = R.string.todo),
    Settings(title = R.string.settings),
    SignInWithPasskey(title = R.string.sign_in),
    SignInWithPassword(title = R.string.sign_in),
    SignOut(title = R.string.sign_out),
    SignUpWithPasskey(title = R.string.sign_up),
    SignUpWithPassword(title = R.string.sign_up),
}

@Composable
fun PrototypeAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun PrototypeApp(
    authViewModel: AuthViewModel,
) {
    val activity = LocalContext.current as Activity
    // Create NavController
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = ApplicationScreen.valueOf(
        backStackEntry?.destination?.route ?: ApplicationScreen.SignInWithPasskey.name
    )
    Scaffold(
        topBar = {
            PrototypeAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val showScreen: String = if (DataProvider.isSignedIn()) {
            ApplicationScreen.MainMenu.name
        } else {
            ApplicationScreen.Home.name
        }
        NavHost(
            navController = navController,
            startDestination = showScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ApplicationScreen.Help.name) {
                HelpScreen()
            }
            composable(route = ApplicationScreen.AccountRecovery.name) {
                AccountRecoveryScreen(
                    onAccountRecoveryButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                )
            }
            composable(route = ApplicationScreen.Home.name) {
                HomeScreen(
                    PasskeySignInButtonClicked = {
                        navController.navigate(ApplicationScreen.SignInWithPasskey.name)
                    },
                    RegisterButtonClicked = {
                        navController.navigate(ApplicationScreen.SignUpWithPasskey.name)
                    },
                    ProceedButtonClicked = {
                        navController.navigate(ApplicationScreen.MainMenu.name)
                    },
                )
            }
            composable(route = ApplicationScreen.MainMenu.name) {
                MainMenuScreen(
                    onDoSomethingButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onHelpButtonClicked = {
                        navController.navigate(ApplicationScreen.Help.name)
                    },
                    onSignOutButtonClicked = {
                        navController.navigate(ApplicationScreen.SignOut.name)
                    },
                )
            }
            composable(route = ApplicationScreen.Placeholder.name) {
                PlaceholderScreen()
            }
            composable(route = ApplicationScreen.Settings.name) {
                SettingsScreen()
            }
            composable(route = ApplicationScreen.SignInWithPasskey.name) {
                SignInWithPasskeyScreen(
                    onSignInButtonClicked = {
                        navController.navigate(ApplicationScreen.MainMenu.name)
                    },
                    onTroubleClicked = {
                        navController.navigate(ApplicationScreen.AccountRecovery.name)
                    }
                )
            }
            composable(route = ApplicationScreen.SignInWithPassword.name) {
                SignInWithPasswordScreen(
                    onSignInButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onSignUpButtonClicked = {
                        navController.navigate(ApplicationScreen.SignUpWithPasskey.name)
                    },
                )
            }
            composable(route = ApplicationScreen.SignOut.name) {
                SignOutScreen()
            }
            composable(route = ApplicationScreen.SignUpWithPasskey.name) {
                SignUpWithPasskeyScreen(
                    onRegisterButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onSignUpWithOtherMethodClicked = {
                        navController.navigate(ApplicationScreen.SignUpWithPassword.name)
                    },
                )
            }
            composable(route = ApplicationScreen.SignUpWithPassword.name) {
                SignUpWithPasswordScreen(
                    onSignUpWithPasswordButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                )
            }
        }
    }
}