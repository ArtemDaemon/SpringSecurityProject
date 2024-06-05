## Getting Started

### Installation

#### SQL Shell
1. Create PostgreSQL database
```
create database postgres;
```
2. Connect to created database
```
\c postgres
```
3. Create tables books, users and user_roles
```
CREATE TABLE IF NOT EXISTS public.books
(
    id serial NOT NULL,
    title character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    genre character varying(255),
    pages integer,
    price integer,
    CONSTRAINT books_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.users
(
    id serial NOT NULL,
    username character varying(255),
    password character varying(64),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.user_roles
(
    id serial NOT NULL,
    user_id integer,
    access_level integer DEFAULT 0,
    CONSTRAINT user_roles_pkey PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS public.user_roles
    ADD CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
CREATE INDEX IF NOT EXISTS fki_user_id
    ON public.user_roles(user_id);
```

#### Git repository
4. Clone the repository to your local machine:
```
git clone https://github.com/ArtemDaemon/SpringSecurityProject
```
5. Navigate to the project directory:
```
cd {project-directory}
```

#### application.properties
6. Set jdbc url, username and password to the postgreSQL database
```
spring.datasource.url=jdbc:postgresql://{url}:{port}/{database_name}
spring.datasource.username={username}
spring.datasource.password={password}
```

#### CLI
7. Build the project using Maven
```
mvn clean package
```
8. Run jar
```
java -jar ./target/SpringSecurityProject-jar-with-dependencies.jar
```

### Usage

http://localhost:8080/

Home page<br>
![изображение](https://github.com/ArtemDaemon/SpringSecurityProject/assets/77564185/d8ef5de1-1e9e-45cf-a11c-a1ce9f63762d)

SignUp page<br>
![изображение](https://github.com/ArtemDaemon/SpringSecurityProject/assets/77564185/85a126a6-df94-413a-a550-829173abb648)


LogIn page<br>
![изображение](https://github.com/ArtemDaemon/SpringSecurityProject/assets/77564185/ee41155b-33ec-4c8d-af04-90bae2ccb41d)

Books page<br>
![изображение](https://github.com/ArtemDaemon/SpringSecurityProject/assets/77564185/aa4fc30d-17cd-4a43-a4df-fd36fa2d85cb)

Add/Edit book page<br>
![изображение](https://github.com/ArtemDaemon/SpringSecurityProject/assets/77564185/d4864656-07a8-4079-8efe-66defed3efe9)


## Contributing

If you'd like to contribute to this project, please follow these guidelines:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

Give credit to any individuals or organizations that helped or inspired you during the development of this project.

