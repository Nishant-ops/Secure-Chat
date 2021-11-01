package com.example.securechat.Cryptography;

import android.util.Base64;
import android.util.Log;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES {
    private final String ALGORITHM="AES";
    private final String PRIVATE_KEY="MIICXQIBAAKBgQCdmVgTU+lXTeP8FyIc0YVCOLf/qqOirwO4Co4UZpdngJXR9XaP2qt4DieL/VponER89wwez4ujpIzI6zKnJd0MbhkjynWk9IJVvxHFWgwMNhUZivmYfZIeJyLE7ms2V2l01dphC5/4WwOEfdlaLgfPa0er5KjxJDu4BqcpoDsxiwIDAQABAoGAIQCnrQR5bMrm3zeI8Tf/C+sMvQFi2wKDaNrs/eCt/AK1XQ5mEaHdHnr4VRxs /lMbFIA3jYww1cfzo299X/tR2qA+cUXDr1BPReC7ZhoicNZAU/aatvEqLVSSM3xE4uZTzseLyfhlGIBF34gpFEvOiFnuGpPSPl6H90Vc3H4wJoECQQDVAQRF/FGBQR9oCVMC1irkUe+MAcSnw4cBnVv60oIQxwm8w+RnbddO9nbqhP/MtRX4D6XJg1e47hKV +h6d2AG7AkEAvWlGUBPBp6j1kzj3pZ6VIDJyURFkI/s80AW+owGzi2C8z+sbo9cVYbzr4wFy1kPMd8ySC6++Q5CwwbQ+9VFqcQJBAKSfW4kngGqwRTCn/DM2CQJbXRNNDotGb+JMlxkcdw+2dQ0iLTXOy9goXjHUxsqUrj0BAvXif/rabRziqaT+nB8CQHE/UFrwdQjZbHrpxKexeVgW0WLHEyiwPlviE2lCyya6YCmwWIZNQut9GUxQwWh6gPXfyjJSkV+0Uvp92maylwECQQCPjG19TN76Kn/FVK6YcQ2ssv5E1TB9hz75bI6l2OmsutAkb6JUoYgBAQjzM80RUu5uHf28glwqUcLnyRiyqpCl";
    private final String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdmVgTU+lXTeP8FyIc0YVCOLf/qqOirwO4Co4UZpdngJXR9XaP2qt4DieL/VponER89wwez4ujpIzI6zKnJd0MbhkjynWk9IJVvxHFWgwMNhUZivmYfZIeJyLE7ms2V2l01dphC5/4WwOEfdlaLgfPa0er5KjxJDu4BqcpoDsxiwIDAQAB";
    String keyString="My ear : listen music";
    private byte[] keyValue=keyString.getBytes();
    String privateKeyString="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALnLhrAS98sJS9nkc2Jktf3fH4Jf\n" +
            "    olJ8f/cytmZkp59OoyXGlMM4HVxbfLMI9ivUs/znqg1RhQlil49ORSbTO68hOHeQu2yePWMCJ1Z6\n" +
            "    ws8sMWyLGzR8wMhL4lIfCGXXx7rTfoJQZnej3IYQfPX1ELbkPlZYAttdG89izoJXa6NfAgMBAAEC\n" +
            "    gYBphp6mTO+uw0XwqR+cCAWR/vOfkf/ZB9uKpKQSviKyifCCGSsPQkTy2weVjIZ9WJ8uCsp8LAv6\n" +
            "    y6O3gkYmih1AHyBrdsxwAlYs/qlqmrjIIFl/OHwMiS6mipoZKp1YdRWK+slyZqxAsWyHhb73tEBO\n" +
            "    nB2mEd73Ra4VgPI/SerGIQJBAPA+dlRJBzFi3zlQVSbscKfQWUmAjqNFweDu/ClcXL8y1Mj7297Z\n" +
            "    XY5c/sWvPTN5C8l6erxsvpx7dGHB32/eNW8CQQDF+ubWwd3db7uhdOc0ttSG1IX6vJ9VwVCP4M12\n" +
            "    6rBTWX9Z72/JmVIVIdLHA6GOmJA7O1h/vwL6Zsvz2mweEdkRAkEA1Xvb8Kf4YVVn4WlWUcFXlngZ\n" +
            "    b6xyk/oAAB6+Kw7tR+Ymmfbc+hmT/lb6+rXvH5dX9xEEkf3cH/fAgsIiCCmgtwJAE5G/a47yLUc0\n" +
            "    FruFdxJzY3hyXdxzK3jWS8J+zLhaAYMhMXSXaif7kxcqzH0xZPTznMc68jWhR7sNgecfNMGy4QJB\n" +
            "    ANUes6SvzvtFf8jQdH8Jc42nDpthdofBzaPCqGhud0vdh35kOf2x7YocCFNCLoc1PYXyBh3CiTWe\n" +
            "    j2YjtI1wMTM=";
    String publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5y4awEvfLCUvZ5HNiZLX93x+CX6JSfH/3MrZm\n" +
            "    ZKefTqMlxpTDOB1cW3yzCPYr1LP856oNUYUJYpePTkUm0zuvITh3kLtsnj1jAidWesLPLDFsixs0\n" +
            "    fMDIS+JSHwhl18e6036CUGZ3o9yGEHz19RC25D5WWALbXRvPYs6CV2ujXwIDAQAB";
    public AES() {
    }

    public String encrypt(String editTextsend) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // AES is currently available in three key sizes: 128, 192 and 256 bits.T
        // the design and strength of all key lengths of the AES algorithm are sufficient to protect classified information up to the SECRET level
        SecretKey secretKey = keyGenerator.generateKey();
        String plainText=editTextsend.toString();

        // 2. get string which needs to be encrypted // our case message
        String text = plainText;//"<your_string_which_needs_to_be_encrypted_here>"
        Log.e("string",text);

        // 3. encrypt string using secret key
        byte[] raw = secretKey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//PKCS5Padding
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        String cipherTextString = Base64.encodeToString(cipher.doFinal(text.getBytes(Charset.forName("UTF-8"))), Base64.DEFAULT);
        Log.e("ciphertext",cipherTextString);

        List<String> keyWithText=new ArrayList<>();
        keyWithText.add(cipherTextString);
        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(Base64.decode(publicKeyString, Base64.DEFAULT));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(publicSpec);

        // 6. encrypt secret key using public key
        Cipher cipher2 = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher2.init(Cipher.ENCRYPT_MODE, publicKey);
        String encryptedSecretKey = Base64.encodeToString(cipher2.doFinal(secretKey.getEncoded()), Base64.DEFAULT);
        Log.e("encryptedSecretKey",encryptedSecretKey);
        System.out.println(encryptedSecretKey);

        System.out.println(cipherTextString);

        encryptedSecretKey+=cipherTextString;
        return encryptedSecretKey;
    }

    public String decrypt(String encrypted) {

        try {
            int n=encrypted.length();
            n--;
           String encryptedSecretKeyString=encrypted.substring(0,174);
           String encryptedTextString=encrypted.substring(175,n);

            PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString, Base64.DEFAULT));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(privateSpec);
           // Log.e("secretKey", privateKey + "");
            // 2. Decrypt encrypted secret key using private key
            Cipher cipher1 = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
            cipher1.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] secretKeyBytes = cipher1.doFinal(Base64.decode(encryptedSecretKeyString, Base64.DEFAULT));
            SecretKey secretKey = new SecretKeySpec(secretKeyBytes, 0, secretKeyBytes.length, "AES");
            //Log.e("secretKey", secretKey + "");
            // 3. Decrypt encrypted text using secret key
            byte[] raw = secretKey.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(Base64.decode(encryptedTextString, Base64.DEFAULT));
            String text = new String(original, Charset.forName("UTF-8"));
            Log.e("text", text);
            // 4. Print the original text sent by client
            System.out.println("text\n" + text + "\n\n");
            return text;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
