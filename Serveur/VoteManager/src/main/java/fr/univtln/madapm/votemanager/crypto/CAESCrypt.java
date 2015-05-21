package fr.univtln.madapm.votemanager.crypto;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Classe contenant les méthodes de cryptage et décryptage
 */
public class CAESCrypt {

    private static final String TRANSFORMATION_STRING = "AES";
    private static final String ENCRYPTION_MODE = "CBC";
    private static final String PADDING_STRING = "PKCS5Padding";

    private static final byte[] lByte = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //TODO IVParameter

    /**
     * Fonction d'encryptage de données
     * @param pKey Clef secrète génèrer via CKeyGenerator
     * @param pStrToEncrypt Données à encrypter
     * @return Données cryptées
     */
    public byte[] encrypt(SecretKey pKey, String pStrToEncrypt)
    {
        try
        {
            return encryptCipher(pKey, TRANSFORMATION_STRING+"/"+ENCRYPTION_MODE+"/"+PADDING_STRING, pStrToEncrypt);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Fonction d'appel de décryptage de données
     * @param pKey clef secrète qui a été utilisé pour le cryptage
     * @param pByteToDecrypt Données cryptées
     * @return Données décryptées
     */
    public String decrypt(SecretKey pKey, byte[] pByteToDecrypt)
    {
        try
        {
            return decryptCipher(pKey, TRANSFORMATION_STRING+"/"+ENCRYPTION_MODE+"/"+PADDING_STRING, pByteToDecrypt);
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Fonction interne de cryptage
     * @param pSecretKey Clef secrète à générer
     * @param pTransformation Mode de transformation (DES,AES,...)
     * @param pMessage Données à crypter
     * @return Données cryptées
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    private static byte[] encryptCipher(SecretKey pSecretKey, String pTransformation,
                                        String pMessage) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException {
        IvParameterSpec lIvParameterSpec = new IvParameterSpec(lByte);
        Cipher lDesCipher = Cipher.getInstance(pTransformation);
        lDesCipher.init(Cipher.ENCRYPT_MODE, pSecretKey, lIvParameterSpec);
        byte[] lByteCipherText = lDesCipher.doFinal(pMessage.getBytes());
        System.out.println(Arrays.toString(lByteCipherText));
        return lByteCipherText;
    }

    /**
     * Fonction interne de décryptage
     * @param pSecretKey Clef secrète utilisé pour le cryptage
     * @param pTransformation Mode de transformation (DES,AES,...)
     * @param pMessage Données à décrypter
     * @return Données décryptées
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    private static String decryptCipher(SecretKey pSecretKey, String pTransformation,
                                        byte[] pMessage) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException {
        IvParameterSpec lIvParameterSpec = new IvParameterSpec(lByte);
        Cipher lDesCipher = Cipher.getInstance(pTransformation);
        lDesCipher.init(Cipher.DECRYPT_MODE, pSecretKey, lIvParameterSpec);
        byte[] lByteDecryptedText = lDesCipher.doFinal(pMessage);
        System.out.println(new String(lByteDecryptedText));
        return Arrays.toString(lByteDecryptedText);
    }

}
