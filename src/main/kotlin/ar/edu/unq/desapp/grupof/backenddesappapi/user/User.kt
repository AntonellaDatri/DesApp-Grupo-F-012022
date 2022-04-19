package ar.edu.unq.desapp.grupof.backenddesappapi.user

import javax.persistence.*

@Entity
class User(){
   constructor(
       name:String, lastName: String,
       email:String, password:String,
       cvu: String, walletAddress:Int,
       address:String) : this() {
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
        this.cvu = cvu
        this.walletAddress = walletAddress
        this.address = address
    }

    @Id
    @Column(unique = true)
    var walletAddress : Int = 0

    lateinit var name:String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var password: String
    private lateinit var cvu: String
    lateinit var address: String
}