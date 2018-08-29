## Spring Boot Keycloak SSO POC

Keycloak is an open source Identity and Access Management solution targeted towards modern applications and services.
Keycloak offers features such as: 
- Single-Sign-On (SSO);
- OAuth 2.0 support;
- Identity Brokering and Social Login;
- User Federation, 
- Client Adapters;
- Admin Console;
- Account Management Console;
- All features you can see [here](https://www.keycloak.org/docs/4.1/server_admin/#features)

To read more about Keycloak, please visit the [official page](https://www.keycloak.org).

### Step 1: Downloading and Installing Keycloak

In this example we use [Keycloak 4.1.0.Final](https://downloads.jboss.org/keycloak/4.1.0.Final/keycloak-4.1.0.Final.zip) version. You can download latest version [here](https://www.keycloak.org/downloads.html).
```sh
$ unzip keycloak-4.1.0.Final.zip
$ cd keycloak-4.1.0.Final/bin
$ ./standalone.sh or ./standalone.sh --debug (for debug mode)
```
After running ./standalone.sh, Keycloak will be starting its services. Once we see a line containing Keycloak 4.1.0.Final (WildFly Core 3.0.8.Final) started, we’ll know its start-up is complete.

By default Keycloak is running on localhost:8080, if you want to expose Keycloak on a different port you need edit one configuration file: keycloak-4.1.0.Final/standalone/configuration/standalone.xml and change port in this value: port="${jboss.http.port:8080}".

Open a browser and visit [http://localhost:8080](http://localhost:8080). We’ll be redirected to [http://localhost:8080/auth](http://localhost:8080/auth) to create an administrative login:

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/create_admin_user.png?raw=true" alt=""/>
</p>

We can now proceed to the [Administrative Console](http://localhost:8080/auth/admin/master/console/).

### Step 3: Creating a Realm
Navigate your mouse into the upper left upper corner to discover the “Create a Realm” button and click the button.
For this example we are naming it "ForcelateKeycloakSSO"

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/ForcelateKeycloakSSO_realm.png?raw=true" alt=""/>
</p>

Now you need to add your realm in two property files. Edit resources/application.yml in client and rest-api modules and change realm value:
```sh
keycloak:
  ...
  realm: ForcelateKeycloakSSO
```

### Step 3: Creating a Role

Click on the “Roles” link in the navigation and after that, click on the button “Add Role” at the right side of the screen. This will navigate you to the “Add Role” screen. Enter "user" as role name and click on “Save”.

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/create_role.png?raw=true" alt=""/>
</p>

You can edit security roles and all permissions in /configuration/ApplicationSecurity.class in both modules (client and rest-api)

### Step 4: Creating a User
Keycloak provides many ways to authorize and create new users, all information you can read [here](https://www.keycloak.org/docs/4.1/server_admin).
But in this example, I will show you how to create a new user manually.

Now we’ve got a role that can be assigned to users, but there are no users yet. So let’s go the “Users” page and add one.
Press "Add user" and create new user. For example, I created user with username "test"

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/create_user.png?raw=true" alt=""/>
</p>

We can now go to the “Credentials” tab. For example, we’ll be setting the password “test”. Than we need navigate to the “Role Mappings” tab and assign the role “user” to your user ("test").
<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/add_role_for_user.png?raw=true" alt=""/>
</p>

Awesome, user is created.

### Step 5: Creating a Clients
#### Step 5.1: Creating FrontEnd client
After we have created our role we can create the clients for our application. We will need two clients. One for the frontend application and one for the REST backend.
Let’s start by creating the frontend client. Click on the “Clients” link in the navigation and then use the “Create” button to create a new client.

In this example we use "client-app" as client username.

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/create_frontend_client.png?raw=true" alt=""/>
</p>

You will be navigated to the client settings. Use the Access Type public and the following URL and save the client:

- Valid Redirect URIs: 

Don't forget to save these changes. Press the button "Save"

If you want to use other port, you need change client module application.yml property
```sh
server:
  port: 8081
```
Edit application.yml in client module and add your frontend client username and Access Type:
```sh
keycloak:
  ...
  resource: client-app
  public-client: true
```
Now the frontend client is completely configured and can be used.

#### Step 5.2: Creating BackEnd client
Lets create another client for the backend and name it "rest-api-app" and press "Save". You will be navigated to the client settings, you need select Access Type as bearer-only. This time configure the Access Type as bearer-only because the REST backend should only be called when a user has already logged in.

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/create_backend_client.png?raw=true" alt=""/>
</p>

Edit application.yml in rest-api module and add your backend client username and Access Type:
```sh
keycloak:
  ...
  resource: rest-api-app
  bearer-only: true
```
For backend module configuration we also need Keycloak "realm publick key" and "security credential secret".
To get realm publick key, please press Realm Settings, Keys and then press "Public key" button in right side

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/get_public_key.png?raw=true" alt=""/>
</p>

Now add this public key in application.yml in rest-api module:

```sh
keycloak:
  ...
  realm-key: MI.....
```

To get security credential secret you need press Clients than select rest-api-app than press Credentials tab and copy secret

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/get_credentials_secret.png?raw=true" alt=""/>
</p>

Now add this security credential secret in application.yml in rest-api module:
```sh
keycloak:
  ...
  credentials:
      secret: 3dz.....
```
Now the backend client is completely configured and can be used.

### Step 6: Run application
Firstly you need to run rest-api module. Open rest-api module in your console and run:
```sh
mvn clean spring-boot:run
```
Than you need to run client module. Open client module in separate tab in your console and run:
```sh
mvn clean spring-boot:run
```
Now you need run your browser and open [http://localhost:8081](http://localhost:8081). If everything is fine, you should see this page:

<p align="center">
	<img src="https://github.com/forcelate/forcelate-temple-java/blob/master/authentication-and-security/spring-boot-v1-pocs/spring-boot-keycloak-sso-poc/img/main_page.png?raw=true" alt=""/>
</p>