<div>
    <div>
        <img src="https://img.shields.io/maven-central/v/io.github.remmerw/borr" alt="Kotlin Maven Version" />
        <img src="https://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android" alt="Badge Android" />
        <!--img src="https://img.shields.io/badge/Platform-iOS%20%2F%20macOS-lightgrey.svg?logo=apple" alt="Badge iOS" /-->
        <img src="https://img.shields.io/badge/Platform-JVM-8A2BE2.svg?logo=openjdk" alt="Badge JVM" />
    </div>
</div>

## Borr
Ed25519 crypto library based on https://github.com/tink-crypto/tink-java

## Integration

```
    
kotlin {
    sourceSets {
        commonMain.dependencies {
            ...
            implementation("io.github.remmerw:borr:0.0.3")
        }
        ...
    }
}
    
```

## API

```
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
```






