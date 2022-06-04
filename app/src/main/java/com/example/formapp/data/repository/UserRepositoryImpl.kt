package com.example.formapp.data.repository

import com.example.formapp.R
import com.example.formapp.data.Result
import com.example.formapp.data.source.FormApi
import com.example.formapp.domain.model.User
import com.example.formapp.domain.repository.UserRepository
import com.example.formapp.util.UiText
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: FormApi
): UserRepository {
    override suspend fun insertUser(user: User): Result<Unit> {
        val response = try {
            api.insertUser(user)
        } catch (e: HttpException) {
            return Result.Error(UiText.StringResource(R.string.unknown_exception_error))
        } catch (e: IOException) {
            return Result.Error(UiText.StringResource(R.string.io_exception_error))
        }
        return Result.Success(data = response, message = UiText.StringResource(R.string.user_created_successfully))
    }
}