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

#### Examples of algorithms:

* AES
* Blowfish
* DES
* RC2

### Asymmetric Encryption
 
Asymmetric encryption is a strong encryption technique which uses a **key pair**.
The key pair consists  of a **public key** and a **private key**. 
Data or message encrypted using the private key can **only** be decrypted using the public key.

#### Examples of algorithms:

* RSA
* Diffie-Hellman
* ECC
* ElGamal

## Hashing

Hashing is the process of storing the text password as encrypted with some hash one-way function (**Message Digest**) sequence of characters.

**Storing the text password with hashing is most dangerous thing for application security today.**

#### Examples of digests:

* MD5 (is considered broken)
* SHA
    - SHA-1 (160-bit output hash algorithm)
    - SHA-2 (256-bit, 384-bit, 512-bit hash algorithms)
* BCrypt
* SCrypt

Even SHA hashed secure passwords are able to be cracked with today’s fast hardwares. 
To beat that, you will need algorithms which can make the brute force attacks slower and minimize the impact. 
Such algorithms are BCrypt and SCrypt.

## Obfuscation

to be continued...