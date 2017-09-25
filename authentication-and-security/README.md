# authentication-and-security

Authentication & Security - security, authentication, authorization, best practices

- [Theory](#theory)
    - [TLS](#tls)
    - [HTTP](#http)
    - [HTTP 1.0 vs. HTTP 2.0](#http-10-vs-http-20)
    - [HTTPS](#https)
	- [Authentication vs. Authorization](#authentication-authorization)
	- [Session](#session)
	- [Stateless HTTP + Cookie](#stateless-http--cookie)
- [Methods](#methods)
	- [Basic Auth](#basic-auth)
	- [Digest Auth](#digest-auth)
	- [Session-Based Auth](#session-based-auth)
	- [JWT-Based Auth](#jwt-based-auth)
	- [OAuth-Based Auth](#oauth-based-auth)
- [Best Practices](#best-practices)

# Theory

Definition are as simple as it was in school. 

> Dog - an animal that ...

> Circle - figure/shape that ...

OR

> REST - software architecture approach that ...

Lets start! 

- Eavesdropping - secretly listening. Wide definition in networking: network layer attack that focuses on **capturing** small packets from the network transmitted by other computers
- Tampering - interfere to cause damage or make unauthorized alterations. Wide definition in networking: network layer attack that focuses that focuses on **changing** small packets from the network transmitted by other computers

## [TLS](https://en.wikipedia.org/wiki/Transport_Layer_Security)

First of all it is **protocol**. Second of all it is **cryptographic** protocol. "SSL" is referred is to **TLS (Transport Layer Security)** or its predecessor **Secure Sockets Layer (SSL)**. TLS provide data encryption you send over network. Designed to prevent eavesdropping and data tampering.

SSL v2.0 was deprecated in 2011. SSL v3.0 was deprecated in June 2015. Considered as **insecure** protocols. 

HTTPS done mostly with TLS v1.2

TLS v1.3 is working draft as of July 2016.

_More_:
- [SSL RFC](https://tools.ietf.org/html/rfc6101)
- [TLS RFC](https://tools.ietf.org/html/rfc5246)

## [HTTP](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol)

First of all it is **protocol**. Second of all it is **transfer** protocol. HTTP is foundation of data communication in World Wide Web. HTTP provide communication between servers and clients. 

_Must know_: 
- Request types. Main types - `GET`, `POST`, `PUT`, `DELETE`. Differences, pros and cons. Minor - `HEAD`, `OPTION` etc.
- Status codes. 1xx, 2xx, 3xx, 4xx, 5xx. Differences. Standards and specifics: `201` - `Created` or `403` - `Forbidden` or `501` - `Not Implemented`
- MIME types: application/json, application/octet-stream, text/html etc. Differences, pros and cons.

_More_: 
- [HTTP Documentation](http://httpwg.org/specs)
- [HTTP Guide](https://www.w3.org/TR/chips/)

## HTTP 1.0 vs. HTTP 2.0

_Must know_: 
- HTTP/2 is binary, instead of textual
- HTTP/2 is fully multiplexed: multiple requests in parallel over a single TCP connection
- HTTP/2 uses header compression
- HTTP/2 uses ALPN (?) extention: faster encrypted connections
- HTTP/2 reduces RTT (round trip times): loading faster without any optimization
- HTTP/2 allows servers to "push" resources: saving extra round trip
- Google Chrome and Mozilla Firefox require TLS (SSL) usage to implement HTTP/2

_More_:
- [HTTP/2 RFC](http://httpwg.org/specs/rfc7540.html)

## [HTTPS](https://en.wikipedia.org/wiki/HTTPS)

HTTPS = HTTP + TLS (SSL). Originally designed for payment system and transaction. HTTPS encrypts data that is sent with your HTTP request/response. Importation in authentication prospective. Ensures protection from eavesdropping. 

## Authentication vs. Authorization

**Authentication:** process to understand WHO you are. Typically based on credentials: username / password

**Authorization:** process to understand WHAT you are ALLOWED. Typically gaining access to resource based on permissions

## [Session](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#HTTP_session)

> HTTP Session - sequence of network request-response transactions

Usually application / server identify you by some session identifier. 

In Java world - [JSESSIONID](https://en.wikipedia.org/wiki/Session_ID)

In PHP world - [PHPSESSIONID](https://en.wikipedia.org/wiki/Session_ID)

In C# world - [ASPSESSIONID](https://en.wikipedia.org/wiki/Session_ID)

If you manually delete this **session** identifier on next request you will be automatically logout-ed to your `home` page

_More_:
- [Session in Computer Science](https://en.wikipedia.org/wiki/Session_(computer_science))
- [Session Management - Cheat Sheet](https://www.owasp.org/index.php/Session_Management_Cheat_Sheet)

## Stateless HTTP + [Cookie](https://en.wikipedia.org/wiki/HTTP_cookie)

> HTTP - is stateless

> Cookie - is stateful

Cookie - (key, value) pair is stored in browser. Browser store cookies in **file system.**

HTTP authentication is required on each page, so application / server can identify you as user. Simple way to understand is any `token`-based authentication. 

Cookie on other hand can store (key, value) pair in browser, so there will be no reason to authenticate yourself on each page. Simple way to understand is [JSESSIONID](https://en.wikipedia.org/wiki/Session_ID).

# Methods

Different authentication methods / approaches / technologies

## [Basic Auth](https://en.wikipedia.org/wiki/Basic_access_authentication)

Basic Auth (HTTP Basic Authentication) - method for an HTTP user agent to provide a user name and password when making a request. Solution claims to be simple and supported in browsers.

"Authenticated" requests must contain `Authorization` header. 

`Authorization: Basic YWRtaW46YWRtaW4=` is Base64 encoded credentials (username: admin, password: admin)

_Must know_: 
- Credentials are Base64 encoded. So it can be easily decoded. **IMPORTANT: Not encrypted**
- HTTPS is required when BasicAuth is used as authentication method
- Password is sent with each request: **IMPORTANT: Giant attack possibility**

_Source (Java, Spring-based app):_

https://github.com/jedivision-software/jdv-temple-java/tree/master/authentication-and-security/spring-boot-basic-authentication-poc

## [Digest Auth](https://en.wikipedia.org/wiki/Digest_access_authentication)

Under development...

## Session-Based Auth

Session-Based Authentication is tightly tied up with [Session_ID](https://en.wikipedia.org/wiki/Session_ID). 

Key understand lies in mapping between **user's login / authentication** and associated **cookie** (generally SessionID). Lets jump to Java world to simplify thought.

In Java world: application is deployed to Apache Tomcat. Apache Tomcat generate JSESSIONID and store mapping between JSESSIONID and user (username: admin, password: admin). Apache Tomcat stores this mapping in memory. 
In more complex cases you can store this _session mapping_ to database like MySQL, Mongodb or to any (key, value) storage like Redis.

## [JWT-Based Auth](https://en.wikipedia.org/wiki/JSON_Web_Token)

Under development...

## [OAuth-Based Auth](https://en.wikipedia.org/wiki/OAuth)

Under development...

# Best Practices

- Use HTTPS
- Don't store password as plain text in database / storage / file system
- Don't implement _in-house_ hashing / encryption algorithms. Please use well-know algorithms 
- Don't implement _in-house_ authentication methods. Please use well-know methods 
- Use security even in initial stages of application development
