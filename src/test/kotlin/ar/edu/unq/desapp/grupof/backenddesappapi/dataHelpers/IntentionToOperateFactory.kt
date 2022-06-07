package ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperate
import ar.edu.unq.desapp.grupof.backenddesappapi.model.OrderDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Operations

class IntentionToOperateFactory() {
    fun anyIntention(cryptoActive: String = "BNBUSDT",
                     amount: Double  = 100.0,
                     walletAddress: Int = 11111111,
                     operation: Operations = Operations.SELL): IntentionToOperate {

        return IntentionToOperate(cryptoActive,amount, walletAddress, operation)
    }

    fun anyIntentionDTO(cryptoActive: String = "BNBUSDT",
                     amount: Double  = 100.0,
                     walletAddress: Int = 11111111,
                     operation: Operations = Operations.SELL): OrderDTO {

        return OrderDTO(cryptoActive,amount, walletAddress, operation)
    }
}