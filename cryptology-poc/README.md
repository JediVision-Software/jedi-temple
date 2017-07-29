# cryptology-poc

Cryptology PoC - encoding, encryption, hashing, obfuscation algorithms skeletons, usages, best practices. 
	
### Encoding

The purpose of encoding is to transform data so that it can be properly (and safely) consumed by a different type of system, 
e.g. binary data being sent over email, or viewing special characters on a web page. 
The goal is not to keep information secret, but rather to ensure that it’s able to be properly consumed.

**Encoding transforms data into another format using a scheme that is publicly available so that it can easily be reversed. 
It does not require a key as the only thing required to decode it is the algorithm that was used to encode it.**

#### Examples of algorithms:

* ASCII
* Unicode
* URL Encoding
* Base64

### Encryption

The purpose of encryption is to transform data in order to keep it secret from others, 
e.g. sending someone a secret letter that only they should be able to read, or securely sending a password over the Internet. 
Rather than focusing on usability, the goal is to ensure the data cannot be consumed by anyone other than the intended recipient(s).

**Encryption transforms data into another format in such a way that only specific individual(s) can reverse the transformation. 
It uses a key, which is kept secret, in conjunction with the plaintext and the algorithm, in order to perform the encryption operation. 
As such, the ciphertext, algorithm, and key are all required to return to the plaintext.**

#### Examples of algorithms:

* AES
* Blowfish
* RSA

### Hashing

to be continued...

### Obfuscation

to be continued...