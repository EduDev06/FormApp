package com.example.formapp.domain.use_cases

import com.example.formapp.R
import com.example.formapp.data.Result
import com.example.formapp.util.UiText
import javax.inject.Inject

class ValidateLastNameUseCase @Inject constructor() {

    operator fun invoke(lastName: String): Result<Unit> {
        if (lastName.isBlank()) {
            return Result.Error(
                message = UiText.StringResource(R.string.blank_last_name_error)
            )
        }
        return Result.Success()
    }
}