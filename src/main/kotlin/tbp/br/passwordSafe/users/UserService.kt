package tbp.br.passwordSafe.users

import org.springframework.stereotype.Service
import tbp.br.passwordSafe.BcryptHashing
import tbp.br.passwordSafe.errors.BadRequestException

@Service
class UserService (
    private val repository: UserRepository
) {
    fun save(user: User): User {
        if (user.id != null)
            throw IllegalArgumentException("User already exists with ID = ${user.id}")

        if (repository.findByEmail(user.email) != null)
            throw BadRequestException("User already exists with EMAIL = ${user.email}")

        val hashedPassword = BcryptHashing.hashPassword(user.password)
        user.password = hashedPassword
        return repository.save(user)
    }

    fun list() : List<User> = repository.findAll()
}