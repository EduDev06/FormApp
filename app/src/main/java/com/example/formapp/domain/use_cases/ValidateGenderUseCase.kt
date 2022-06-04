package com.example.formapp.domain.use_cases

import com.example.formapp.R
import com.example.formapp.data.Result
import com.example.formapp.util.UiText
import javax.inject.Inject

class ValidateGenderUseCase @Inject constructor() {

    operator fun invoke(gender: String?): Result<Unit> {
        if (gender == null) {
            return Result.Error(
                message = UiText.StringResource(R.string.blank_gender_error)
            )
        }
        return Result.Success()
    }
}