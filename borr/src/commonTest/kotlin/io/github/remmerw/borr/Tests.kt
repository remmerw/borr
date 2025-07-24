package io.github.remmerw.borr

import kotlin.io.encoding.Base64
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertNotNull

class Tests {

    @Test
    fun ed25519() {
        val keys = Ed25519Sign.KeyPair.newKeyPair()

        // sign a msg
        val msg = "moin moin".encodeToByteArray()
        val signer = Ed25519Sign(keys.getPrivateKey())
        val signature = signer.sign(msg)


        // encode
        val privateKeyAsString = Base64.encode(keys.getPrivateKey())
        assertNotNull(privateKeyAsString)
        val publicKeyAsString = Base64.encode(keys.getPublicKey())
        assertNotNull(publicKeyAsString)


        // decode
        val privateKey = Base64.decode(privateKeyAsString)
        assertNotNull(privateKey)
        val publicKey = Base64.decode(publicKeyAsString)

        // verify with public key
        val verifier = Ed25519Verify(publicKey)
        verifier.verify(signature, msg) // throws exception in case is not verified
    }


    @Test
    fun encodeDecode(){
        val data = Random.nextBytes(32)
        val encoded = encode58(data)
        val cmp = decode58(encoded)
        assertContentEquals(data, cmp)

    }

}