package ar.edu.unq.desapp.grupof.backenddesappapi.model

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
    val id: Int? = null
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
    @OneToMany(mappedBy = "user",  cascade = [CascadeType.ALL])
    val orders: MutableSet<Order>? = mutableSetOf()
    @OneToMany(mappedBy = "executingUser",  cascade = [CascadeType.ALL])
    val transfer: MutableSet<Transfer>? = mutableSetOf()
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
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
        this.cvu = cvu
        this.walletAddress = walletAddress
        this.address = address
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
        if (!validateName(name)) {throw Exception("El nombre no debe ser vacio y debe tener entre 3 y 30 caracteres")}
        if (!validateLastName(lastName)) {throw Exception("El apellido no debe ser vacio y debe tener entre 3 y 30 caracteres")}
        if (!validateEmail(email)){throw Exception("El mail debe ser un mail valido")}
        if (!validatePassword(password)) {throw Exception("La contaseña no puede estar vacia. Debe tener 1 mayuscula, 1 minuscula, 1 caracter especial y como minimo 6 caracteres")}
        if (!validateAddress(address!!)) {throw Exception("La direccion no puede ser vacia y debe tener entre 10 y 30 caracteres")}
        if (!validateCVU(cvu!!)) {throw Exception("El CVU debe tener 22 caracteres")}
        if (!validateAddressWallet(walletAddress!!)) {throw Exception("La billetera debe tener 8 caracteres")}
    }

    private fun validateName(name: String?): Boolean {
        return  name != null && (name.length in 3..30)
    }

    private fun validateLastName(lastname: String?): Boolean {
        return lastname != null && (lastname.length in 3..30)
    }

    private fun validateEmail(email: String?): Boolean {
        return email != null && (email.contains('@') && (email.endsWith(".com") || email.endsWith(".ar")))
    }

    private fun validatePassword(password:String?): Boolean {
        val upperCase = Regex(".*[A-Z].*")
        val lowerCase = Regex(".*[a-z].*")
        val specialCharacter = Regex(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*")
        val long = Regex(".{6,}")
        return password != null && password.matches(upperCase) && password.matches(lowerCase) && password.matches(specialCharacter) && password.matches(long)
    }

    private fun validateAddress(address: String?): Boolean {
        return address != null && (address.length in 10..30)
    }

    private fun validateCVU(cvu: String?): Boolean {
        return cvu != null && (cvu.length == 22)
    }
    private fun validateAddressWallet(walletAddress: Int?): Boolean {
        return walletAddress != null && (walletAddress.toString().length == 8)
    }

    fun transferMoney(cvu: String){
        return
    }
    fun transferCrypto(walletAddress: Int){
        return
    }
}