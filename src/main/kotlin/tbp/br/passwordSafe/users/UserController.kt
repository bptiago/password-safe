package tbp.br.passwordSafe.users

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController {

    // Environment variable (128 bits)
    @Value("\${ENCRYPTION_KEY}")
    lateinit var key: String

    @GetMapping
    fun test() {
        println(key)
    }
}