# cryptology-poc

Cryptology PoC - encoding, encryption, hashing, obfuscation algorithms skeletons, usages, best practices
	
## Encoding

Encoding transforms data into another format using a scheme that is **publicly available** so that it can easily be reversed.

Encoding is two-way function. You can encode and decode data without any keys (you only need to now character table).

#### Examples of schemes:

* ASCII (contains 0 - 127 character positions)
* Unicode family - most popular is UTF-8 (contains 0 - 255 character positions)

#### Usages:

* Base64 - is encoding and decoding technique used to convert binary data to an ASCII text format, and vice versa.
* URL Encoding - is the practice of translating unprintable or special characters in URLs to a form understandable by web browsers and servers.

## Encryption

Encryption is the process of transforming data using an algorithm (called **cipher**) to make it unreadable to anyone except those who knows the secret **key**.

Encryption is two-way function. You can encrypt and decrypt data (but you need **key** and know **encryption algorithm** which was used to decrypt data correctly).

### Symmetric Encryption
 
In symmetric encryption the same key is used for both encryption and decryption.

#### Examples of algorithms:

* AES (used by U.S. for securing sensitive but unclassified material)
* RC2 (used in some versions of Outlook Express by Microsoft)
* Blowfish (used in OpenSSH, PuTTY, Linux in the mainline kernel, starting with v2.5.47)
* DES (considered highly insecure, can be decrypted by brute force within a single day by EFF "Deep Crack" machines)

### Asymmetric Encryption
 
Asymmetric encryption is a strong encryption technique which uses a **key pair**.
The key pair consists  of a **public key** and a **private key**. 
Data or message encrypted using the private key can **only** be decrypted using the public key.

#### Examples of algorithms:

* RSA (used in SSH, OpenPGP, S/MIME, and SSL/TLS protocols)
* ECC (support for ECC included in products of Motorola and Siemens)
* ElGamal (is used in the free GNU Privacy Guard software, recent versions of PGP)

## Hashing

Hashing is the process of storing the text password as encrypted sequence of characters, with some hash one-way function.

#### Examples of digests:

* MD5 (is considered broken)
* SHA (is considered will be broken in nearest future)
    - SHA-1 (160-bit output hash)
    - SHA-2 (256-bit, 384-bit, 512-bit hash algorithms. The most reliable is 512-bit hash algorithm)
* BCrypt 
* SCrypt

Even SHA hashed secure passwords are able to be cracked with today’s fast hardwares. 
To avoid this situation, you may use algorithms which can make the brute force attacks slower and minimize the impact.
For example BCrypt and SCrypt algorithms.

## Obfuscation

Obfuscation make source code hard to understand for people which do not wrote this code.
Obfuscation process provide encrypting some or all of the code, renaming useful class and variable names.
A tool called an obfuscator can convert source code into a program that works the same way, 
but is much harder to read and understand.

#### Obfuscation techniques

* **Layout obfuscations** modify the layout structure of the program by two basic methods: 
    - renaming identifiers 
    - removing debugging information
    
    Most layout obfuscations cannot be undone because they use one-way functions such as changing identifiers by random symbols 
    and removing comments, unused methods, and debugging information. Though layout obfuscations cannot prevent reverse engineers to understand 
    the program by observing the obfuscated code, they at least consume the cost of reverse engineering. 
    Layout obfuscations are the most well studied and widely used in code obfuscation.

* **Control obfuscations** change the control flow of the program. The trick is simple: for a method A() obfuscator creates 
an additional method A_bug() and an "if" selector, *if (PREDICATE) then A_bug(); else A();*. 
The PREDICATE is designed on-the-fly in that way so it is always false (but it's made so it's hard to conclude that fact), 
and the A() method is always selected instead of a buggy copy A_bug().

* **Data obfuscations** break the data structures used in the program and encrypt literals. 
This method includes modifying inheritance relations, restructuring arrays, etc. 
Data obfuscations thoroughly change the data structure of a program. They make the obfuscated codes so complicated 
that it is impossible to recreate the original source code.

#### Obfuscators examples:

* ProGuard
* JODE
* JavaGuard
* RetroGuard
* yGuard