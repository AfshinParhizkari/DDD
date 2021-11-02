DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      session_user varchar NOT NULL,
                      productfk Int(11) NOT NULL,
                      quantity smallint(6) NOT NULL,
                      price DECIMAL (10,2) NOT NULL
);