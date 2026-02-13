package io.github.remmerw.borr


// hash is always (32 bit) and it is an Ed25519 public key
data class PeerId(val hash: ByteArray) {

    override fun hashCode(): Int {
        return hash.contentHashCode() // ok, checked, maybe opt
    }

    init {
        require(hash.size == 32) { "hash size must be 32" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PeerId

        return hash.contentEquals(other.hash)
    }
}

// Note a peerId is always a public key (ed25519)
@Suppress("ArrayInDataClass")
data class Keys(val peerId: PeerId, val privateKey: ByteArray)


fun generateKeys(): Keys {
    val keyPair = Ed25519Sign.KeyPair.newKeyPair()
    return Keys(
        PeerId(keyPair.getPublicKey()),
        keyPair.getPrivateKey()
    )
}

fun verify(peerId: PeerId, data: ByteArray, signature: ByteArray) { // move to Asen
    val verifier = Ed25519Verify(peerId.hash)
    verifier.verify(signature, data)
}

fun sign(keys: Keys, data: ByteArray): ByteArray { // move to Asen
    val signer = Ed25519Sign(keys.privateKey)
    return signer.sign(data)
}


fun decode58(input: String): ByteArray {
    return Base58.decode58(input)
}

fun encode58(data: ByteArray): String {
    return Base58.encode58(data)
}
