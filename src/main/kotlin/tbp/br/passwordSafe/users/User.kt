package tbp.br.passwordSafe.users

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "tbUser")
class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @NotNull
    var name: String,

    @NotNull
    @Column(unique=true)
    var email: String,

    @NotNull
    var password: String,
)