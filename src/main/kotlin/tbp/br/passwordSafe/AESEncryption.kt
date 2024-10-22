package tbp.br.passwordSafe

import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.Base64
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec

class AESEncryption {

    companion object {
        private const val KEY_ALGORITHM = "AES"
        private const val ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding"

        fun generateKey(n: Int = 128) : SecretKey {
            val keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM)
            keyGenerator.init(n)
            val key = keyGenerator.generateKey();
            return key
        }

        // IV is a random or pseudo-random value that is used in conjunction with a secret
        // key to ensure that identical plaintexts encrypt to different ciphertexts.
        fun generateIv() : IvParameterSpec {
            val bytes = ByteArray(16)
            SecureRandom().nextBytes(bytes)
            return IvParameterSpec(bytes)
        }

        @Throws(
            NoSuchPaddingException::class, NoSuchAlgorithmException::class,
            InvalidAlgorithmParameterException::class, InvalidKeyException::class,
            BadPaddingException::class, IllegalBlockSizeException::class
        )
        fun encrypt(str: String, secretKey: SecretKey, iv: IvParameterSpec) : String {
            val cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
            val cipherText = cipher.doFinal(str.toByteArray())
            return Base64.getEncoder().encodeToString(cipherText)
        }

        @Throws(
            NoSuchPaddingException::class, NoSuchAlgorithmException::class,
            InvalidAlgorithmParameterException::class, InvalidKeyException::class,
            BadPaddingException::class, IllegalBlockSizeException::class
        )
        fun decrypt(encryptedString: String, secretKey: SecretKey, iv: IvParameterSpec) : String {
            val cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
            val cipherText = cipher.doFinal(Base64.getDecoder().decode(encryptedString))
            return String(cipherText)
        }
    }
}