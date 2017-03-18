package com.epam.zubar.hr.utils;

import com.epam.zubar.hr.exception.HRProjectDAOException;

public abstract class PasswordHasher {
    /**
     *  Define the BCrypt workload to use when generating password hashes.
     *  10-31 is a valid value.
     */
    private static final int WORKLOAD = 16;

    private static final String PREFIX = "$2a$";

    /**
     * This method can be used to generate a string representing an account password
     * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
     * hash string of length=60
     * This automatically handles secure 128-bit salt generation and storage within the hash.
     */
    public static String generateSecuredPassHash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(WORKLOAD));
    }

    /**
     * This method can be used to verify a computed hash from a password plaintext (e.g. during a login
     * request) with that of a stored hash from a database. The password hash from the database
     * must be passed as the second variable.
     * @throws HRProjectDAOException
     */
    public static boolean checkPassword(String password, String stored_hash) throws HRProjectDAOException {
        boolean password_verified = false;

        if(stored_hash == null || !stored_hash.startsWith(PREFIX)){
            throw new HRProjectDAOException("Invalid hash provided for comparison");
        }
        password_verified = BCrypt.checkpw(password, stored_hash);

        return(password_verified);
    }

}

