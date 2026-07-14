# Blog API

A RESTful Blog application backend built with Spring Boot and PostgreSQL.

## Features
- JWT authentication (register/login)
- Blog post CRUD with category filtering and popularity sorting
- Comments on posts
- Like/Unlike system with duplicate prevention
- Data ownership — users can only modify their own posts and comments
- Input validation and custom exception handling

## Tech Stack
- Java 21, Spring Boot 3.5, Spring Security, JWT
- PostgreSQL, JPA/Hibernate
- Docker, Maven

## API Endpoints

### Auth (Public)
- POST /api/auth/register
- POST /api/auth/login

### Posts
- GET /api/posts (public)
- GET /api/posts/{id} (public)
- POST /api/posts (authenticated)
- PUT /api/posts/{id} (authenticated, owner only)
- DELETE /api/posts/{id} (authenticated, owner only)
- GET /api/posts/category/{category} (public)
- GET /api/posts/popular (public)

### Comments
- GET /api/posts/{postId}/comments (public)
- POST /api/posts/{postId}/comments (authenticated)
- DELETE /api/comments/{id} (authenticated, owner only)

### Likes
- POST /api/posts/{postId}/like (authenticated)
- DELETE /api/posts/{postId}/like (authenticated)

## Running Locally
1. Clone the repository
2. Create PostgreSQL database called `blog_db`
3. Set environment variables: DB_URL, DB_USERNAME, DB_PASSWORD, JWT_SECRET
4. Run `mvn spring-boot:run`
