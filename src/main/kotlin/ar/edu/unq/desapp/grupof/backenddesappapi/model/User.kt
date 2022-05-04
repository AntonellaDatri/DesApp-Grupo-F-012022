package ar.edu.unq.desapp.grupof.backenddesappapi.model

import javax.persistence.*

@Entity
@Table(name= "users",
    uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("walletAddress")),
        UniqueConstraint(columnNames = arrayOf("cvu")),
        UniqueConstraint(columnNames = arrayOf("email"))]
)
class User{
    @Id
    var walletAddress : Int? = null
    @Column
    var name:String? = null
    @Column
    var lastName: String? = null
    @Column
    var email: String? = null
    @Column
    var password: String? = null
    @Column
    var cvu: String? = null
    @Column
    var address: String? = null

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
}