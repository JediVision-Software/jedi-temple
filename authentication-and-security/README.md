# authentication-and-security

Authentication & Security - security, authentication, authorization, best practices etc.

- [Theory](#theory)
    - [TLS](#tls)
    - [HTTP](#http)
	- [Authentication vs. Authorization](#authentication-authorization)
- [Methods](#methods)
	- [Basic Auth](#basic-auth)

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

First of all it is **protocol**. Second of all it is **transfer** protocol. HTTP is foundation of data communication in WWW. HTTP provide communication between servers and clients. 

_Must know_: 
- Request types. Main types - `GET`, `POST`, `PUT`, `DELETE`. Differences, pros and cons. Minor - `HEAD`, `OPTION` etc.
- Status codes. 1xx, 2xx, 3xx, 4xx, 5xx. Differences. Standards and specifics: `201` - `Created` or `403` - `Forbidden` or `501` - `Not Implemented`
- MIME types: application/json, application/octet-stream, text/html etc. Differences, pros and cons.

_More_: 
- [HTTP Documentation](http://httpwg.org/specs)
- [HTTP Guide](https://www.w3.org/TR/chips/)

## Authentication vs. Authorization

**Authentication:** process to understand WHO you are. Typically based on credentials: username / password

**Authorization:** process to understand WHAT you are ALLOWED. Typically gaining access to resource based on permissions

# Methods

to be continued...

## Basic Auth

to be continued...

