package ar.edu.unq.desapp.grupof.backenddesappapi.model

import ar.edu.unq.desapp.grupof.backenddesappapi.dto.UserRequestDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.exceptions.InvalidDataUser
import org.jetbrains.annotations.NotNull
import javax.persistence.*


@Entity
@Table(name= "users",
    uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("walletAddress")),
        UniqueConstraint(columnNames = arrayOf("cvu")),
        UniqueConstraint(columnNames = arrayOf("email"))]
)
class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @Column(nullable = false, unique = true)
    var walletAddress : Int? = null
    @NotNull
    @Column
    lateinit var name:String
    @NotNull
    @Column
    lateinit var lastName: String
    @Column(nullable = false, unique = true)
    lateinit var email: String
    @NotNull
    @Column
    lateinit var password: String
    @Column(nullable = false, unique = true)
    lateinit var cvu: String
    @NotNull
    @Column
    lateinit var address: String
    @OneToMany(mappedBy = "user",  cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val orders: MutableSet<Order> = mutableSetOf()
    @OneToMany(mappedBy = "executingUser",  cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val transfer: MutableSet<Transfer> = mutableSetOf()
    @Column
    var points: Int = 0
    @Column
    var amountOperations: Int = 0

    constructor() : super()
    constructor(
        name:String, lastName: String,
        email:String, password:String,
        cvu: String, walletAddress: Int,
        address: String
    ) : super() {
        validateData(name, lastName, email, address, password, cvu, walletAddress)
    }

    fun validateData(
        name: String?,
        lastName: String?,
        email: String?,
        address: String?,
        password: String?,
        cvu: String?,
        walletAddress: Int?
    ) {
        if (!validateName(name)) {throw InvalidDataUser("El nombre no debe ser vacio y debe tener entre 3 y 30 caracteres")} else { this.name = name!! }
        if (!validateLastName(lastName)) {throw InvalidDataUser("El apellido no debe ser vacio y debe tener entre 3 y 30 caracteres")} else { this.lastName = lastName!!}
        if (!validateEmail(email)){throw InvalidDataUser("El mail debe ser un mail valido")} else { this.email = email!! }
        if (!validatePassword(password)) {throw InvalidDataUser("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres") }else { this.password = password!! }
        if (!validateAddress(address!!)) {throw InvalidDataUser("La direccion no puede ser vacia y debe tener entre 10 y 30 caracteres")}else { this.address = address }
        if (!validateCVU(cvu!!)) {throw InvalidDataUser("El CVU debe tener 22 caracteres")} else { this.cvu = cvu }
        if (!validateAddressWallet(walletAddress!!)) {throw InvalidDataUser("La billetera debe tener 8 caracteres")}else {    this.walletAddress = walletAddress}
    }

    private fun validateName(name: String?): Boolean {
        return (name != null) && (name.length in (3..30))
    }

    private fun validateLastName(lastname: String?): Boolean {
        return (lastname != null) && (lastname.length in (3..30))
    }

    private fun validateEmail(email: String?): Boolean {
        return (email != null) && (email.contains('@') && (email.endsWith(".com") || email.endsWith(".ar")))
    }

    private fun validatePassword(password:String?): Boolean {
        val upperCase = Regex(".*[A-Z].*")
        val lowerCase = Regex(".*[a-z].*")
        val specialCharacter = Regex(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*")
        val long = Regex(".{6,}")
        return (password != null) && password.matches(upperCase) && password.matches(lowerCase) && password.matches(
            specialCharacter
        ) && password.matches(long)
    }

    private fun validateAddress(address: String?): Boolean {
        return (address != null) && (address.length in (10..30))
    }

    private fun validateCVU(cvu: String?): Boolean {
        return (cvu != null) && (cvu.length == 22)
    }
    private fun validateAddressWallet(walletAddress: Int?): Boolean {
        return (walletAddress != null) && (walletAddress.toString().length == 8)
    }

    fun transferMoney(cvu: String): String {
        return cvu
    }
    fun transferCrypto(walletAddress: Int): Int {
        return walletAddress
    }

    companion object {
        fun fromModel(user : UserRequestDTO): User {
            return User(
                user.name,
                user.lastName,
                user.email,
                user.password,
                user.cvu,
                user.walletAddress,
                user.address
            )
        }
    }
}