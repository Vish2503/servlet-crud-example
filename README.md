# Simple CRUD Project in Java with Servlets

This is a basic CRUD (Create, Read, Update, Delete) web application built with Java using Servlet technology. This project serves as an example for implementing CRUD functionality with Servlets and a MySQL database.

### Features
- Login, Logout and New Registration functionality with error handling.
- Create, Read, Update and Delete items from a MySQL database.
- Seperate functionalities for normal users and administrators.

## Prerequisites

To run this application, ensure you have the following installed:
- NetBeans 8.2
- JDK (Java Development Kit) 8
- GlassFish 4.1.1 Server
- MySQL 8 or higher
- MySQL Connector 9.0.0 jar

## Installation and Setup

### Step 1: Clone the Repository 
```bash
git clone https://github.com/your-username/servlet-crud-example.git 
cd servlet-crud-example
```

### Step 2: Configure the Database
1. Create a MySQL database named servlet_crud_example.

2. Run the following SQL commands to create the required table:
```sql
CREATE TABLE user (
	userID int NOT NULL AUTO_INCREMENT,
  	username varchar(255) NOT NULL,
  	password varchar(255) NOT NULL,
  	isAdmin char(1) DEFAULT 'F',
  	PRIMARY KEY (userID),
  	UNIQUE KEY username (username),
  	CONSTRAINT true_or_false CHECK (((isAdmin = 'T') or (isAdmin = 'F')))
);
```
```sql
INSERT INTO user (username, password, isAdmin) VALUES ('admin', 'admin', 'T');
```
```sql
CREATE TABLE student (
	userid int,
	rollno varchar(255),
	name char(255),
	gender char(255),
	age int,
	email varchar(255),
	mobile varchar(255),
	degree varchar(255),
	batch varchar(255),
	section char(2),
	gpa float,
	FOREIGN KEY (userid) REFERENCES user(userid)
);
```

3. Update database credentials in the code:
   - Open UserDao.java and StudentDao.java.
   - Set your MySQL username, password, and database name.

### Step 3: Run and Access the Application.
Run the Java Project using NetBeans and access the web application at `http://localhost:8080/ServletCRUDExample`.

## Screenshots

![login](https://github.com/user-attachments/assets/2c3e7452-1272-4ec6-b8d6-687f96b37410)

![login_wrongpassword](https://github.com/user-attachments/assets/a3d0f1ae-9511-46f7-9bae-4f6b5412a739)

![register](https://github.com/user-attachments/assets/f9e0ced5-c623-429a-bf91-634de50a751f)

![register_usernamealreadyexists](https://github.com/user-attachments/assets/aa9c99b2-aef1-4792-bed6-7c4f7dd8da56)

![home](https://github.com/user-attachments/assets/7a35a40d-fca6-49ff-9e0b-b422b2edcbcb)

![home_edit](https://github.com/user-attachments/assets/eb155260-03b9-4ab2-9b4e-2ce663b50d22)

![home_edit2](https://github.com/user-attachments/assets/5ca123f8-df0e-4043-893b-cbf55c6183f3)

![admin](https://github.com/user-attachments/assets/713d57c9-09b8-46d9-989d-f559ed53a680)

![admin_page3](https://github.com/user-attachments/assets/f029c74d-7a22-49bb-bfe5-7cf98a2830bd)

![admin_edit](https://github.com/user-attachments/assets/93549164-fab3-475f-8796-3335aa710b82)

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
