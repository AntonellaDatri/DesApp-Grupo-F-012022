package ar.edu.unq.desapp.grupof.backenddesappapi.dataHelpers

import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperate
import ar.edu.unq.desapp.grupof.backenddesappapi.model.IntentionToOperateDTO
import ar.edu.unq.desapp.grupof.backenddesappapi.model.Operations

class IntentionToOperateFactory() {
    fun anyIntention(cryptoActive: String = "BNBUSDT",
                     amount: Double  = 100.0,
                     walletAddress: Int = 11111111,
                     operation: Operations = Operations.SELL): IntentionToOperate {
        var user = UserFactory().anyUser()

        return IntentionToOperate(cryptoActive,amount, user, operation)
    }

    fun anyIntentionDTO(cryptoActive: String = "BNBUSDT",
                     amount: Double  = 100.0,
                     walletAddress: Int = 11111111,
                     operation: Operations = Operations.SELL): IntentionToOperateDTO {

        return IntentionToOperateDTO(cryptoActive,amount, walletAddress, operation)
    }
}