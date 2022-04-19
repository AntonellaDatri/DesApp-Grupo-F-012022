package ar.edu.unq.desapp.grupof.backenddesappapi.quote

class CryptoAssetQuote {

    fun quoteOf(cryptoAsset: String) {
        var url = "https://api1.binance.com/api/v3/ticker/price?symbol=$cryptoAsset"
        url.length
    }
}