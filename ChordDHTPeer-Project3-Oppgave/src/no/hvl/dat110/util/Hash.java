package no.hvl.dat110.util;

import java.io.UnsupportedEncodingException;

/**
 * project 3
 * @author tdoy
 *
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	
	public static BigInteger hashOf(String entity) {		
		
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // we use MD5 with 128 bits digest
			
			byte[] entityByte = md.digest(entity.getBytes()); // compute the hash of the input 'entity'
			md.update(entityByte);
//			byte[] digest = md.digest();
			
			String hex = toHex(entityByte); // convert the hash into hex format
			
			hashint = new BigInteger(hex, 16); // convert the hex into BigInteger
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hashint; // return the BigInteger
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5
		
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				
				//byte[] digest = md.digest(); // <- Overflødig, for øyeblikket
				
				int length = md.getDigestLength(); // get the digest length
				
				int digestBits = length*8; // compute the number of bits = digest length * 8
				
				BigInteger addSize = new BigInteger("2").pow(digestBits); // compute the address size = 2 ^ number of bits
				
				return addSize; // return the address size
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			return null;
			
			
		
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		try {
			digestlen = MessageDigest.getInstance("MD5").getDigestLength();
	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	
		return digestlen*8; // find the digest length
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
