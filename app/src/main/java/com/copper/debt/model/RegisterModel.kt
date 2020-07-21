
package com.copper.debt.model

import com.copper.debt.common.arePasswordsSame
import com.copper.debt.common.isEmailValid
import com.copper.debt.common.isUsernameValid


data class RegisterRequest(var name: String = "",
                           var email: String = "",
                           var password: String = "",
                           var repeatPassword: String = "") {

  fun isValid(): Boolean = isUsernameValid(name)
      && isEmailValid(email)
      && arePasswordsSame(password, repeatPassword)
}