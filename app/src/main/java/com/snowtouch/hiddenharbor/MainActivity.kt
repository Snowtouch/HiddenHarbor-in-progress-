package com.snowtouch.hiddenharbor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.snowtouch.hiddenharbor.data.repository.AccountServiceImpl
import com.snowtouch.hiddenharbor.data.repository.RealtimeDatabaseServiceImpl
import com.snowtouch.hiddenharbor.ui.components.NavigationComponent
import com.snowtouch.hiddenharbor.ui.components.SnackbarGlobalDelegate
import com.snowtouch.hiddenharbor.ui.theme.HiddenHarborTheme
import com.snowtouch.hiddenharbor.viewmodel.AccountScreenViewModel
import com.snowtouch.hiddenharbor.viewmodel.UserState
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    private lateinit var accountScreenViewModel: AccountScreenViewModel
    private lateinit var realtimeDatabaseServiceImpl: RealtimeDatabaseServiceImpl
    private val snackbarGlobalDelegate: SnackbarGlobalDelegate by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        accountScreenViewModel = AccountScreenViewModel(get(), AccountServiceImpl(get(), Dispatchers.IO), get())
        Log.d("Activity","onCreate")

        super.onCreate(savedInstanceState)
        setContent {
            HiddenHarborTheme {
                KoinAndroidContext {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        NavigationComponent(
                            navController = navController,
                            accountScreenViewModel = accountScreenViewModel
                        )
                    }
                }
            }
        }
    }

    public override fun onStart() {
        Log.d("Activity","onStart")
        super.onStart()
        val firebaseAuth: FirebaseAuth = get()
        val firebaseDatabase: FirebaseDatabase = get()
        if (firebaseAuth.currentUser != null) {
            UserState.setUserLoggedIn(true)
        } else {UserState.setUserLoggedIn(false)}
    }

    override fun onPause() {
        super.onPause()
        Log.d("Activity", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Activity","onResume")
        accountScreenViewModel.toggleCurrentUserDataListener(true, snackbarGlobalDelegate)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Activity","onStop")
        accountScreenViewModel.toggleCurrentUserDataListener(false, snackbarGlobalDelegate)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Activity","onDestroy")
    }
}