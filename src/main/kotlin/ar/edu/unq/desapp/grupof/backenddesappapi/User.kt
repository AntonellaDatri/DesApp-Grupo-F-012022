package ar.edu.unq.desapp.grupof.backenddesappapi

/*import javax.persistence.**/

/*@Entity*/
class User(){
   /* constructor(name:String, lastName: String,
                email:String, password:String,
                cvu:Int, walletAddress:Int,
                address:String) : this() {
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
        this.cvu = cvu
        this.walletAddress = walletAddress
        this.address = address
    }

   *//* @Id*//*
    var id: Long? = null

    @Column(unique = true)
    var walletAddress : Int = 0
    lateinit var name:String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var password: String
    var cvu: Int = 0
    lateinit var address: String
*/
}