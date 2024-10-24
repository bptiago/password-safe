package tbp.br.passwordSafe.users

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tbp.br.passwordSafe.users.requests.CreateUserRequest


@RestController
@RequestMapping("/users")
class UserController (
    private val service: UserService
) {

    // Environment variable (128 bits)
    @Value("\${ENCRYPTION_KEY}")
    lateinit var key: String

    @GetMapping
    fun list() =
        service.list()
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun save(@Valid @RequestBody  user: CreateUserRequest) =
        service.save(user.toUser())
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
}