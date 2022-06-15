package ar.edu.unq.desapp.grupof.backenddesappapi.dto

import java.util.*


class VolumeOperationsDTO {
    lateinit var user : UserDTO
    lateinit var dateTime: Date
    var totalAmountARS : Double = 0.0
    var totalAmountUSD : Double = 0.0
    lateinit var listActives: List<TransferActivesDTO>
    constructor() : super()
    constructor(
        user: UserDTO, listActives : List<TransferActivesDTO>, USDQuote : Double
    ) : super() {
        this.user = user
        this.dateTime = Date()
        this.totalAmountARS = listActives.sumOf{ it.arsAmount }
        this.totalAmountUSD = listActives.sumOf { it.arsAmount * USDQuote}
        this.listActives = listActives
    }


}