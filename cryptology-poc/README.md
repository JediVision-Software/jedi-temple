# cryptology-poc

Cryptology PoC - encoding, encryption, hashing, obfuscation algorithms skeletons, usages, best practices. 
	
## Encoding

Encoding transforms data into another format using a scheme that is **publicly available** so that it can easily be reversed.

Encoding is two-way function. You can encode and decode data without any keys (you only need to now character table).

#### Examples of algorithms:

* ASCII
* Unicode
* URL Encoding
* Base64

## Encryption

Encryption is the process of transforming data using an algorithm (called **cipher**) to make it unreadable to anyone except those who knows the secret **key**.

Encryption is two-way function. You can encrypt and decrypt data (but you need **key** and know **encryption algorithm** which was used to decrypt data correctly).

### Symmetric Encryption
 
In symmetric encryption the same key is used for both encryption and decryption.

In short, to make a symmetric encryption/decryption you should:
- Create a byte arrays from the initial password and initial key
- Create a new **SecretKeySpec** from the key byte array, using some encryption algorithm (for example AES).
- Create a new **Cipher** for the transformation and initialize it in
**encryption mode**, with the specified key. 
- Make the encryption. The result is a new byte array with the encrypted password.
- Initialize the cipher in **decryption mode**, using the same key.
- Make the decryption of the encrypted byte array. The result will be a decrypted byte array.

#### Examples of algorithms:

* AES
* Blowfish

### Asymmetric Encryption
 
Asymmetric encryption is a strong encryption technique which uses a **key pair**.
The key pair consists  of a **public key** and a **private key**. 
Data or message encrypted using the private key can **only** be decrypted using the public key.

In short, to make a asymmetric encryption/decryption you should:
- Generate **KeyPair** with **KeyPairGenerator** 
- Get **public key** and **private key** from key pair
- Create a new **Cipher** for the transformation and initialize it in
**encryption mode**, with the **private** key. 
- Make the encryption. The result is a new byte array with the encrypted password.
- Initialize the cipher in **decryption mode**, using the **public** key.
- Make the decryption of the encrypted byte array. The result will be a decrypted byte array.

#### Examples of algorithms:

* RSA

## Hashing

Hashing is the process of storing the text password as encrypted with some hash one-way function (**Message Digest**) sequence of characters.

**Storing the text password with hashing is most dangerous thing for application security today.**

#### Examples of digests:

* MD5 (is considered broken)
* SHA
    - SHA-1 (160-bit output hash algorithm)
    - SHA-2 (256-bit, 512-bit hash algorithm)
* PBKDF2
* BCrypt
* SCrypt

Even SHA hashed secure passwords are able to be cracked with today’s fast hardwares. 
To beat that, you will need algorithms which can make the brute force attacks slower and minimize the impact. 
Such algorithms are PBKDF2, BCrypt and SCrypt.

## Obfuscation

to be continued...