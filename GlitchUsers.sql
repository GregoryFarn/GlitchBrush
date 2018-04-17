DROP DATABASE IF EXISTS GlitchUsers;

CREATE DATABASE GlitchUsers;
Use GlitchUsers;

CREATE TABLE Users(

	userID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
    
);

INSERT INTO Users (username, password, email)
	VALUES  ('master', 'root', 'qwerty@gmail.com');

CREATE TABLE Images(

	imageID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userID INT(11) NOT NULL,
    imageName VARCHAR(50) NOT NULL,
	FOREIGN KEY fk1(userID) REFERENCES Users(userID)
    
);

INSERT INTO Images (userID, imageName)
	VALUES  (1, 'image1');
    
    
    
