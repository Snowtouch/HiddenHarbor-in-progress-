package com.snowtouch.hiddenharbor.viewmodel

import androidx.lifecycle.ViewModel
import com.snowtouch.hiddenharbor.data.model.User
import kotlinx.coroutines.flow.StateFlow

class GroupScreenViewModel(
    private val userState: UserState,
) : ViewModel() {
    val user: StateFlow<User> = userState.user
}