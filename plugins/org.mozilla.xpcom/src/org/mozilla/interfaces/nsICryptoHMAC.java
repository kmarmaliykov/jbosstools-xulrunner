/*
 * DO NOT EDIT.  THIS FILE IS GENERATED FROM
 * /builds/slave/mozilla-1.9.2-linux-xulrunner/build/netwerk/base/public/nsICryptoHMAC.idl
 */

package org.mozilla.interfaces;

/**
 * nsICryptoHMAC
 * This interface provides HMAC signature algorithms.
 */
public interface nsICryptoHMAC extends nsISupports {

  String NS_ICRYPTOHMAC_IID =
    "{8feb4c7c-1641-4a7b-bc6d-1964e2099497}";

  /**
     * Hashing Algorithms.  These values are to be used by the
     * |init| method to indicate which hashing function to
     * use.  These values map onto the values defined in
     * mozilla/security/nss/lib/softoken/pkcs11t.h and are 
     * switched to CKM_*_HMAC constant.
     */
  short MD2 = 1;

  short MD5 = 2;

  short SHA1 = 3;

  short SHA256 = 4;

  short SHA384 = 5;

  short SHA512 = 6;

  /**
     * Initialize the hashing object. This method may be
     * called multiple times with different algorithm types.
     *
     * @param aAlgorithm the algorithm type to be used.
     *        This value must be one of the above valid
     *        algorithm types.
     *
     * @param aKeyObject
     *        Object holding a key. To create the key object use for instance:
     *        var keyObject = Components.classes["@mozilla.org/security/keyobjectfactory;1"]
     *            .getService(Components.interfaces.nsIKeyObjectFactory)
     *              .keyFromString(Components.interfaces.nsIKeyObject.HMAC, rawKeyData);
     *
     * WARNING: This approach is not FIPS compliant.
     *
     * @throws NS_ERROR_INVALID_ARG if an unsupported algorithm
     *        type is passed.
     *
     * NOTE: This method must be called before any other method 
     *        on this interface is called.
     */
  void init(long aAlgorithm, nsIKeyObject aKeyObject);

  /**
     * @param aData a buffer to calculate the hash over
     *
     * @param aLen the length of the buffer |aData|
     *
     * @throws NS_ERROR_NOT_INITIALIZED if |init| has not been 
     *         called.
     */
  void update(byte[] aData, long aLen);

  /**
     * Calculates and updates a new hash based on a given data stream.
     *
     * @param aStream an input stream to read from.
     *
     * @param aLen how much to read from the given |aStream|.  Passing
     *        PR_UINT32_MAX indicates that all data available will be used 
     *        to update the hash. 
     *
     * @throws NS_ERROR_NOT_INITIALIZED if |init| has not been 
     *         called.
     *
     * @throws NS_ERROR_NOT_AVAILABLE if the requested amount of 
     *         data to be calculated into the hash is not available.
     *
     */
  void updateFromStream(nsIInputStream aStream, long aLen);

  /**
     * Completes this HMAC object and produces the actual HMAC diegest data.
     *
     * @param aASCII if true then the returned value is a base-64 
     *        encoded string.  if false, then the returned value is
     *        binary data.  
     *
     * @return a hash of the data that was read by this object.  This can
     *         be either binary data or base 64 encoded.
     *
     * @throws NS_ERROR_NOT_INITIALIZED if |init| has not been 
     *         called.
     *
     * NOTE: This method may be called any time after |init|
     *       is called.  This call resets the object to its
     *       pre-init state.
     */
  String finish(boolean aASCII);

  /**
     * Reinitialize HMAC context to be reused with the same
     * settings (the key and hash algorithm) but on different 
     * set of data.
     */
  void reset();

}