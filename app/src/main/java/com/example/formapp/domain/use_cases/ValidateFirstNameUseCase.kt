package com.example.formapp.domain.use_cases

import com.example.formapp.R
import com.example.formapp.data.Result
import com.example.formapp.util.UiText
import javax.inject.Inject

class ValidateFirstNameUseCase @Inject constructor() {

    operator fun invoke(firstName: String): Result<Unit> {
        if (firstName.isBlank()) {
            return Result.Error(
                message = UiText.StringResource(R.string.blank_first_name_error)
            )
        }
        return Result.Success()
    }
}