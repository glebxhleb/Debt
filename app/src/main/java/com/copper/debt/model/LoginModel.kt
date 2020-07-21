
package com.copper.debt.model

import com.copper.debt.common.isEmailValid
import com.copper.debt.common.isPasswordValid


data class LoginRequest(var email: String = "",
                        var password: String = "") {

  fun isValid() = isEmailValid(email) && isPasswordValid(password)
}