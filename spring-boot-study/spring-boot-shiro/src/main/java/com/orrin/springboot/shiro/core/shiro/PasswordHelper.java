package com.orrin.springboot.shiro.core.shiro;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <p>User: Zhang aolin
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */

public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();


    private String algorithmName = "md5";

    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public PasswordAndSalt encryptPassword(String userId ,String password) {

       String salt = randomNumberGenerator.nextBytes().toHex();

        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(userId + salt),
                hashIterations).toHex();

        return new PasswordAndSalt(newPassword,salt);
    }

    public PasswordAndSalt encryptPassword(String userId ,String password,String salt) {

        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(userId + salt),
                hashIterations).toHex();

        return new PasswordAndSalt(newPassword,salt);
    }

    public class PasswordAndSalt{
        private String password;
        private String salt;

        public PasswordAndSalt(String password, String salt) {
            this.password = password;
            this.salt = salt;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        @Override
        public String toString() {
            return "PasswordAndSalt{" +
                    "password='" + password + '\'' +
                    ", salt='" + salt + '\'' +
                    '}';
        }
    }

    public static int passwordStrength(String password){
        String strongRegex = "^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$";
        String mediumRegex = "^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$";
        String enoughRegex = "(?=.{8,}).*";

        if(password.matches(strongRegex)){
            return 2;
        }else if(password.matches(mediumRegex)){
            return 1;
        }else if(password.matches(enoughRegex)){
            return 0;
        }

        return 0;
    }

}
