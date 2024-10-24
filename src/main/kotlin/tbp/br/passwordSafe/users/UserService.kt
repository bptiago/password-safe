package tbp.br.passwordSafe.users

import org.springframework.stereotype.Service
import tbp.br.passwordSafe.BcryptHashing
import tbp.br.passwordSafe.errors.BadRequestException
import tbp.br.passwordSafe.errors.NotFoundException

@Service
class UserService (
    private val repository: UserRepository
) {
    fun save(user: User): User {
        if (user.id != null)
            throw IllegalArgumentException("User already exists with ID")

        if (repository.findByEmail(user.email) != null)
            throw BadRequestException("User already exists with EMAIL")

        val hashedPassword = BcryptHashing.hashPassword(user.password)
        user.password = hashedPassword
        return repository.save(user)
    }

    fun login(email: String, password: String): User {
        val user = repository.findByEmail(email) ?: throw NotFoundException("User not found")
        val valid = BcryptHashing.verifyPassword(password, user.password)
        if (!valid)
            throw BadRequestException("Invalid Password")
        return user
    }

    fun list() : List<User> = repository.findAll()
}