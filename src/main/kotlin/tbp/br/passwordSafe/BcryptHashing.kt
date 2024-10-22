package tbp.br.passwordSafe

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class BcryptHashing {
    companion object {
        private val encoder = BCryptPasswordEncoder()

        fun hashPassword(password: String): String = encoder.encode(password)

        fun verifyPassword(password: String, hashedPassword: String): Boolean =
            encoder.matches(password, hashedPassword)
    }
}