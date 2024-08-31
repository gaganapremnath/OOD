# Reuse Book System

## Overview
The Reuse Book System is designed to facilitate the buying and selling of books between students. Once a book is sold, it can be repurchased from the student at a discounted rate based on the year of publication and the number of times the book has been sold. The system keeps track of book prices, student transactions, and allows for the resale of books at discounted rates.

### Features
- **Book Registration**: A new book can be bought and registered in the system, tracking the price and student from whom it was bought.
- **Book Sale**: Books can be sold to students at the purchase price.
- **Book Resale**: Books can be repurchased from students at a discounted rate, based on the last sold price.
- **Book Search**: Users can search for books in the system. An empty search key will trigger a bad request error.

### System Design
The system is designed with several database tables, each serving a specific function in tracking books, prices, transactions, and student data.

#### Database Tables

- **Book Table**:
  - Fields: `id` (unique), `title`, `isbn`, `edition`, `is_available`.
  - Purpose: Tracks each book's details and availability status.

- **Author Table**:
  - Fields: `id` (unique), `author_name`, `book_id`.
  - Purpose: Tracks authors associated with each book. A book can have multiple authors.

- **Student Table**:
  - Fields: `suid` (unique), `name`.
  - Purpose: Stores information about students who buy or sell books.

- **Price Table**:
  - Fields: `id` (unique), `book_id`, `price`.
  - Purpose: Records the pricing history for each book.

- **Type Table**:
  - Fields: `id` (unique), `name`.
  - Purpose: Acts as a constant table, defining the transaction types (e.g., "buy", "sell").

- **Transaction Table**:
  - Fields: `id` (unique), `book_id`, `price_id`, `student_id`, `type_id`.
  - Purpose: Tracks each transaction, including the book's price, the student involved, and the type of transaction (buy or sell).

### Workflow

1. **Book Registration**:
   - When a new book is bought, it is registered in the system with an entry in the `Book` table.
   - The initial price is recorded in the `Price` table, and a transaction is logged in the `Transaction` table with the type set to "buy".
   - An associated author(s) is linked through the `Author` table.

2. **Book Sale**:
   - The book can be sold to a student at the registered purchase price.
   - The `is_available` field in the `Book` table is updated to `0`.
   - A transaction is logged in the `Transaction` table with the type set to "sell".

3. **Book Resale**:
   - If the same book is resold by a student, the `is_available` field is set back to `1`.
   - The system retrieves the latest price from the `Price` table, calculates the new discounted price, and records it in the `Price` table.
   - A new transaction is logged in the `Transaction` table.

4. **Book Search**:
   - Users can search for a book by its title, ISBN, or other attributes.
   - If an empty search key is provided, the system will return a bad request error to prevent unnecessary processing.

### Technical Stack
- **Backend**: Java, Spring Boot
- **Database**: SQL (MySQL)
- **APIs**: RESTful APIs
- **Tools**: Docker for deployment, Postman and Swagger for API testing

### Installation & Setup
1. **Clone the Repository**:
   git clone https://github.com/your-username/reuse-book-system.git
   cd reuse-book-system

### Backend Setup:
Ensure you have Java and Spring Boot installed.
Configure your SQL database (PostgreSQL/MySQL) and update the application.properties file with your database credentials.
Start the backend server:
./mvnw spring-boot:run

### Docker Setup (Optional):
Ensure Docker is installed.
Build and run the Docker containers:
docker-compose up --build

### Usage
**Search for a Book:** Use the search functionality in the frontend to find a book by title, ISBN, etc.
**Buy a Book:** Register a new book purchase through the frontend, linking it to a student.
**Sell a Book:** Sell a book to a student, updating the is_available status and logging the transaction.
**Resell a Book:** Handle the resale of a book, ensuring that the price is updated based on previous sales.

### Testing
**API Testing:** Use Postman or Swagger to test the RESTful APIs.
**Unit & Integration Testing:** Implement tests using JUnit for the backend logic.

### Future Enhancements
**Advanced Search:** Implement search filters based on authors, price ranges, and availability status.
**Discount Calculation:** Introduce more sophisticated discounting algorithms based on the book’s condition, age, and popularity.
**Notifications:** Add a notification system to alert students when a book they’re interested in becomes available.
