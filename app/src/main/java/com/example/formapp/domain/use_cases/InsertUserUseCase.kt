package com.example.formapp.domain.use_cases

import com.example.formapp.data.Result
import com.example.formapp.domain.model.User
import com.example.formapp.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: User): Result<Unit> {
        return repository.insertUser(user)
    }
}