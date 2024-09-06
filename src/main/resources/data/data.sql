-- Insert data into Author table
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('George', 'Orwell', '1903-06-25');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Jane', 'Austen', '1775-12-16');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('J.K.', 'Rowling', '1965-07-31');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Mark', 'Twain', '1835-11-30');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('F. Scott', 'Fitzgerald', '1896-09-24');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Ernest', 'Hemingway', '1899-07-21');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Charles', 'Dickens', '1812-02-07');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Leo', 'Tolstoy', '1828-09-09');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Herman', 'Melville', '1819-08-01');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('H.G.', 'Wells', '1866-09-21');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Isaac', 'Asimov', '1920-01-02');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('J.R.R.', 'Tolkien', '1892-01-03');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Agatha', 'Christie', '1890-09-15');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Arthur', 'Conan Doyle', '1859-05-22');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Aldous', 'Huxley', '1894-07-26');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Mary', 'Shelley', '1797-08-30');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Jules', 'Verne', '1828-02-08');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('George', 'R.R. Martin', '1948-09-20');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('C.S.', 'Lewis', '1898-11-29');
INSERT INTO Author (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH) VALUES ('Edgar Allan', 'Poe', '1809-01-19');

-- Insert data into Publisher table
INSERT INTO Publisher (Name) VALUES ('Penguin Books');
INSERT INTO Publisher (Name) VALUES ('HarperCollins');
INSERT INTO Publisher (Name) VALUES ('Bloomsbury');
INSERT INTO Publisher (Name) VALUES ('Random House');
INSERT INTO Publisher (Name) VALUES ('Simon & Schuster');
INSERT INTO Publisher (Name) VALUES ('Macmillan');
INSERT INTO Publisher (Name) VALUES ('Hachette');
INSERT INTO Publisher (Name) VALUES ('Scholastic');
INSERT INTO Publisher (Name) VALUES ('Oxford University Press');
INSERT INTO Publisher (Name) VALUES ('Cambridge University Press');
INSERT INTO Publisher (Name) VALUES ('Wiley');
INSERT INTO Publisher (Name) VALUES ('McGraw-Hill');
INSERT INTO Publisher (Name) VALUES ('Pearson');
INSERT INTO Publisher (Name) VALUES ('Springer');
INSERT INTO Publisher (Name) VALUES ('Elsevier');
INSERT INTO Publisher (Name) VALUES ('Wolters Kluwer');
INSERT INTO Publisher (Name) VALUES ('Cengage');
INSERT INTO Publisher (Name) VALUES ('Brill');
INSERT INTO Publisher (Name) VALUES ('SAGE');
INSERT INTO Publisher (Name) VALUES ('Taylor & Francis');

-- Insert data into Genre table
INSERT INTO Genre (Name) VALUES ('Dystopian');
INSERT INTO Genre (Name) VALUES ('Romance');
INSERT INTO Genre (Name) VALUES ('Fantasy');
INSERT INTO Genre (Name) VALUES ('Science Fiction');
INSERT INTO Genre (Name) VALUES ('Mystery');
INSERT INTO Genre (Name) VALUES ('Thriller');
INSERT INTO Genre (Name) VALUES ('Horror');
INSERT INTO Genre (Name) VALUES ('Historical Fiction');
INSERT INTO Genre (Name) VALUES ('Adventure');
INSERT INTO Genre (Name) VALUES ('Biography');
INSERT INTO Genre (Name) VALUES ('Self-Help');
INSERT INTO Genre (Name) VALUES ('Non-Fiction');
INSERT INTO Genre (Name) VALUES ('Poetry');
INSERT INTO Genre (Name) VALUES ('Drama');
INSERT INTO Genre (Name) VALUES ('Classics');
INSERT INTO Genre (Name) VALUES ('Comics');
INSERT INTO Genre (Name) VALUES ('Graphic Novels');
INSERT INTO Genre (Name) VALUES ('Childrens');
INSERT INTO Genre (Name) VALUES ('Young Adult');
INSERT INTO Genre (Name) VALUES ('Philosophy');

-- Insert data into Book table
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('1984', 'https://images-na.ssl-images-amazon.com/images/I/41GDv5AMeNL._SX331_BO1,204,203,200_.jpg', 4.7, '1949-06-08', 1, 1);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('Pride and Prejudice', 'https://images-na.ssl-images-amazon.com/images/I/81A-mvlo+QL.jpg', 4.6, '1813-01-28', 2, 2);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('Harry Potter and the Sorcerers Stone', 'https://images-na.ssl-images-amazon.com/images/I/81YOuOGFCJL.jpg', 4.9, '1997-06-26', 3, 3);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('The Adventures of Tom Sawyer', 'https://images-na.ssl-images-amazon.com/images/I/91HHqVTAJQL.jpg', 4.5, '1876-12-17', 4, 4);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('The Great Gatsby', 'https://images-na.ssl-images-amazon.com/images/I/81af+MCATTL.jpg', 4.4, '1925-04-10', 5, 5);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('The Old Man and the Sea', 'https://images-na.ssl-images-amazon.com/images/I/81t2CVWEsUL.jpg', 4.3, '1952-09-01', 6, 6);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('A Tale of Two Cities', 'https://images-na.ssl-images-amazon.com/images/I/91UoQHRbMPL.jpg', 4.6, '1859-04-30', 7, 7);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('War and Peace', 'https://images-na.ssl-images-amazon.com/images/I/91iG4jbwRHL.jpg', 4.8, '1869-01-01', 8, 8);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('Moby-Dick', 'https://images-na.ssl-images-amazon.com/images/I/81Pr+bp2gLL.jpg', 4.2, '1851-10-18', 9, 9);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('The War of the Worlds', 'https://images-na.ssl-images-amazon.com/images/I/81w1NPNBbAL.jpg', 4.5, '1898-04-01', 10, 10);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('Foundation', 'https://images-na.ssl-images-amazon.com/images/I/91yXOZIxtlL.jpg', 4.4, '1951-05-03', 11, 11);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('The Hobbit', 'https://images-na.ssl-images-amazon.com/images/I/91b0C2YNSrL.jpg', 4.8, '1937-09-21', 12, 12);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('Murder on the Orient Express', 'https://images-na.ssl-images-amazon.com/images/I/71kxa1-0mfL.jpg', 4.6, '1934-01-01', 13, 13);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('The Hound of the Baskervilles', 'https://images-na.ssl-images-amazon.com/images/I/81e8y-9n1ML.jpg', 4.7, '1902-04-01', 14, 14);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('Brave New World', 'https://images-na.ssl-images-amazon.com/images/I/81aYyyhllzL.jpg', 4.3, '1932-08-18', 15, 15);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('Frankenstein', 'https://images-na.ssl-images-amazon.com/images/I/81WcnNQ-TBL.jpg', 4.4, '1818-01-01', 16, 16);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('Twenty Thousand Leagues Under the Sea', 'https://images-na.ssl-images-amazon.com/images/I/91L4ZNYE1rL.jpg', 4.5, '1870-06-20', 17, 17);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('A Game of Thrones', 'https://images-na.ssl-images-amazon.com/images/I/91b0C2YNSrL.jpg', 4.9, '1996-08-06', 18, 18);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('The Lion, the Witch and the Wardrobe', 'https://images-na.ssl-images-amazon.com/images/I/81YoUOjEhLL.jpg', 4.8, '1950-10-16', 19, 19);
INSERT INTO Book (Title, Cover, Rating, RELEASE_DATE, Publisher_id, Author_id) VALUES ('The Raven', 'https://images-na.ssl-images-amazon.com/images/I/71msq8D8ZSL.jpg', 4.3, '1845-01-29', 20, 20);

INSERT INTO Book (Title, Cover, Rating, Release_Date, Publisher_id, Author_id) VALUES ('Harry Potter and the Prisoner of Azkaban', 'https://m.media-amazon.com/images/I/812CcFkEPCL._AC_UF1000,1000_QL80_.jpg', 4.9, '1999-07-08', 3, 3);
INSERT INTO Book (Title, Cover, Rating, Release_Date, Publisher_Id, Author_id) VALUES ('Harry Potter and the Chamber of Secrets', 'https://m.media-amazon.com/images/I/818umIdoruL._AC_UF1000,1000_QL80_.jpg', 4.8, '1998-07-02', 3, 3);




-- Insert data into BOOK_GENRES table
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (1, 1); -- 1984 is Dystopian
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (2, 2); -- Pride and Prejudice is Romance
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (3, 3); -- Harry Potter is Fantasy
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (9, 4); -- Tom Sawyer is Adventure
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (8, 5); -- Gatsby is Historical Fiction
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (12, 6); -- Old Man and the Sea is Non-Fiction
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (8, 7); -- Tale of Two Cities is Historical Fiction
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (8, 8); -- War and Peace is Historical Fiction
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (9, 9); -- Moby-Dick is Adventure
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (4, 10); -- War of the Worlds is Science Fiction
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (4, 11); -- Foundation is Science Fiction
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (3, 12); -- Hobbit is Fantasy
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (5, 13); -- Orient Express is Mystery
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (5, 14); -- Hound of the Baskervilles is Mystery
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (1, 15); -- Brave New World is Dystopian
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (7, 16); -- Frankenstein is Horror
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (9, 17); -- 20000 Leagues is Adventure
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (3, 18); -- Game of Thrones is Fantasy
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (3, 19); -- Lion, Witch, and Wardrobe is Fantasy
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (7, 20); -- The Raven is Horror
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (3, 21); -- Harry Potter is Fantasy
INSERT INTO BOOK_GENRES (Genres_id, Books_id) VALUES (3, 22); -- Harry Potter is Fantasy

-- Insert data into MY_USER table
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('John', 'Doe', 'jdoe', 'password123', 'jdoe@example.com', 0, 10);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Alice', 'Smith', 'asmith', 'password123', 'asmith@example.com', 0, 15);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Bob', 'Johnson', 'bjohnson', 'password123', 'bjohnson@example.com', 1, 20);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Charlie', 'Brown', 'cbrown', 'password123', 'cbrown@example.com', 0, 5);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('David', 'Davis', 'ddavis', 'password123', 'ddavis@example.com', 0, 7);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Eva', 'Green', 'egreen', 'password123', 'egreen@example.com', 1, 22);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Frank', 'White', 'fwhite', 'password123', 'fwhite@example.com', 0, 3);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Grace', 'Black', 'gblack', 'password123', 'gblack@example.com', 0, 50);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Hank', 'Blue', 'hblue', 'password123', 'hblue@example.com', 0, 10);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Ivy', 'Purple', 'ipurple', 'password123', 'ipurple@example.com', 1, 5);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Jack', 'Red', 'jred', 'password123', 'jred@example.com', 0, 14);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Kelly', 'Yellow', 'kyellow', 'password123', 'kyellow@example.com', 0, 100);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Liam', 'Orange', 'lorange', 'password123', 'lorange@example.com', 1, 3);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Mia', 'Pink', 'mpink', 'password123', 'mpink@example.com', 0, 20);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Noah', 'Gray', 'ngray', 'password123', 'ngray@example.com', 0, 15);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Olivia', 'Brown', 'obrown', 'password123', 'obrown@example.com', 1, 5);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Paul', 'Green', 'pgreen', 'password123', 'pgreen@example.com', 0, 12);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Quinn', 'White', 'qwhite', 'password123', 'qwhite@example.com', 1, 6);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Ryan', 'Black', 'rblack', 'password123', 'rblack@example.com', 0, 5);
INSERT INTO MY_USER (FIRST_NAME, LAST_NAME, Username, Password, Email, iS_ADMIN, Goal) VALUES ('Sophia', 'Blue', 'sblue', 'password123', 'sblue@example.com', 0, 10);

-- Insert data into User_Book table
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (1, 1, 1, 0, 5, '2024-06-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (2, 2, 1, 0, 4, '2022-02-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (3, 3, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (4, 4, 1, 0, 3, '2022-03-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (5, 5, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (6, 6, 1, 0, 4, '2022-04-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (7, 7, 1, 0, 3, '2024-05-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (8, 8, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (8, 9, 1, 0, 5, '2022-06-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (9, 10, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (10, 11, 1, 0, 4, '2022-07-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (11, 12, 1, 0, 5, '2023-08-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (12, 13, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (13, 14, 1, 0, 4, '2022-09-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (14, 15, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (15, 16, 1, 0, 5, '2023-10-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (16, 17, 1, 0, 4, '2024-02-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (17, 18, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (18, 19, 1, 0, 5, '2022-12-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (19, 20, 1, 0, 4, '2023-01-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (20, 1, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (1, 2, 1, 0, 5, '2023-02-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (2, 3, 1, 0, 4, '2023-03-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (3, 4, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (4, 5, 1, 0, 3, '2023-04-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (5, 6, 0, 1, NULL, NULL);
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (6, 7, 1, 0, 4, '2024-05-01');
INSERT INTO User_Book (USER_ID, Book_id, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) VALUES (7, 8, 1, 0, 5, '2023-06-01');