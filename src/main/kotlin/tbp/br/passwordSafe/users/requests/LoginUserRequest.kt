package tbp.br.passwordSafe.users.requests

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class LoginUserRequest(
    @field:NotNull
    @field:Email
    val email: String?,

    @field:NotNull
    @field:Size(min=4)
    val password: String?
)