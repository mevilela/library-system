# Library System API

This is the RESTful API for a library management system. It provides endpoints to manage various entities such as accounts, books, authors, publishers, libraries, racks, book items, lending, and reservations.

## Technologies Used
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT Authentication

## Endpoints

### Account Management
- **GET** /api/account: Retrieve all accounts.
- **GET** /api/account/{id}: Retrieve an account by ID.
- **POST** /api/account/create: Create a new account.
- **PUT** /api/account/{id}: Update an account by ID.
- **DELETE** /api/account/{id}: Delete an account by ID.

### Authentication
- **POST** /api/auth/login: Authenticate a user and generate JWT token for authorization.

### Book Management
- **GET** /api/book: Retrieve all books.
- **GET** /api/book/id/{id}: Retrieve a book by ID.
- **GET** /api/book/isbn/{isbn}: Retrieve a book by ISBN.
- **GET** /api/book/subject/{subject}: Retrieve a book by subject.
- **POST** /api/book: Create a new book.
- **PUT** /api/book/{id}: Update a book by ID.
- **DELETE** /api/book/{id}: Delete a book by ID.

### Author Management
- **GET** /api/author: Retrieve all authors.
- **GET** /api/author/{id}: Retrieve an author by ID.
- **POST** /api/author: Create a new author.
- **PUT** /api/author/{id}: Update an author by ID.
- **DELETE** /api/author/{id}: Delete an author by ID.

### Publisher Management
- **GET** /api/publisher: Retrieve all publishers.
- **GET** /api/publisher/{id}: Retrieve a publisher by ID.
- **POST** /api/publisher: Create a new publisher.
- **PUT** /api/publisher/{id}: Update a publisher by ID.
- **DELETE** /api/publisher/{id}: Delete a publisher by ID.

### Library Management
- **GET** /api/library: Retrieve all libraries.
- **GET** /api/library/{id}: Retrieve a library by ID.
- **POST** /api/library: Create a new library.
- **PUT** /api/library/{id}: Update a library by ID.
- **DELETE** /api/library/{id}: Delete a library by ID.

### Rack Management
- **GET** /api/rack: Retrieve all racks.
- **GET** /api/rack/{id}: Retrieve a rack by ID.
- **POST** /api/rack: Create a new rack.
- **PUT** /api/rack/{id}: Update a rack by ID.
- **DELETE** /api/rack/{id}: Delete a rack by ID.

### Book Item Management
- **GET** /api/book-item: Retrieve all book items.
- **GET** /api/book-item/id/{id}: Retrieve a book item by ID.
- **GET** /api/book-item/barcode/{barcode}: Retrieve a book item by barcode.
- **GET** /api/book-item/publication-date/{date}: Retrieve a book item by publication date.
- **POST** /api/book-item: Create a new book item.
- **PUT** /api/book-item/{id}: Update a book item by ID.
- **DELETE** /api/book-item/{id}: Delete a book item by ID.

### Lending Management
- **POST** /api/lending/loan: Request to lend a book.
- **POST** /api/lending/return: Return a borrowed book.

### Reservation Management
- **POST** /api/reservation: Create a book reservation.

## Authentication
This API uses JWT authentication. To authenticate, send a POST request to /api/auth/login with valid credentials. Upon successful authentication, you will receive a JWT token which should be included in the headers of subsequent requests for authorization.

## Error Handling
The API returns appropriate HTTP status codes and error messages for invalid requests or errors.

## Authorization
Authorization is required for certain endpoints, such as creating, updating, or deleting resources. Only users with specific roles (e.g., LIBRARIAN) have access to these endpoints.

## Swagger Documentation
You can access the Swagger API documentation by visiting [`http://localhost:8080/swagger-ui/index.html#/`](http://localhost:8080/swagger-ui/index.html#/) in your browser after running the application.

## Usage
Before using the API, make sure to set up the necessary configurations and dependencies.
Refer to the provided endpoint documentation for details on each endpoint's functionality and required parameters.
