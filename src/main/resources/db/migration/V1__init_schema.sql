
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE genre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_year INT,
    genre_id INT NOT NULL,
    rating FLOAT,
    total_rate INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    actors TEXT,
    FOREIGN KEY (genre_id) REFERENCES genre(id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    movie_id INT NOT NULL,
    rating INT NOT NULL,
    content TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES movies(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (user_id, movie_id)
);

CREATE TABLE comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    review_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (review_id) REFERENCES reviews(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO users (username, email, password) VALUES
('dang', 'dang@example.com', '1'),
('anna', 'anna@example.com', '1'),
('john', 'john@example.com', '1'),
('maria', 'maria@example.com', '1'),
('peter', 'peter@example.com', '123456');

INSERT INTO genre (name, description) VALUES
('Action', 'Phim hành động, đánh nhau, siêu anh hùng'),
('Comedy', 'Phim hài hước giải trí'),
('Drama', 'Phim tâm lý, cảm xúc'),
('Sci-Fi', 'Phim khoa học viễn tưởng, không gian'),
('Horror', 'Phim kinh dị, giật gân');


INSERT INTO movies (title, description, release_year, genre_id, rating, total_rate, actors) VALUES
('Avengers: Endgame', 'Siêu anh hùng giải cứu vũ trụ', 2019, 1, 4.8, 1200, 'Robert Downey Jr, Chris Evans'),
('John Wick 4', 'Sát thủ huyền thoại', 2023, 1, 4.5, 800, 'Keanu Reeves'),
('Deadpool', 'Hài hành động siêu bựa', 2016, 2, 4.2, 600, 'Ryan Reynolds'),
('The Hangover', 'Nhóm bạn lầy lội ở Las Vegas', 2009, 2, 4.0, 400, 'Bradley Cooper'),
('Interstellar', 'Hành trình xuyên không gian cứu Trái Đất', 2014, 4, 4.9, 1500, 'Matthew McConaughey'),
('Inception', 'Thế giới nằm trong giấc mơ', 2010, 4, 4.8, 1400, 'Leonardo DiCaprio'),
('The Conjuring', 'Gia đình gặp thế lực siêu nhiên', 2013, 5, 4.1, 500, 'Patrick Wilson'),
('Annabelle', 'Búp bê ma ám', 2014, 5, 3.8, 450, 'Annabelle'),
('The Shawshank Redemption', 'Vượt ngục kinh điển, top IMDb', 1994, 3, 4.9, 2000, 'Tim Robbins'),
('Forrest Gump', 'Cuộc đời đặc biệt của Forrest', 1994, 3, 4.8, 1800, 'Tom Hanks');

-- User 1 reviews
INSERT INTO reviews (user_id, movie_id, rating, content) VALUES
(1, 1, 5, 'Phim quá xuất sắc, xem lại vẫn hay!'),
(1, 5, 5, 'Một tuyệt phẩm khoa học viễn tưởng.');

-- User 2 reviews
INSERT INTO reviews (user_id, movie_id, rating, content) VALUES
(2, 2, 4, 'John Wick đánh nhau chất chơi.'),
(2, 3, 4, 'Deadpool bựa nhưng hài.');

-- User 3 reviews
INSERT INTO reviews (user_id, movie_id, rating, content) VALUES
(3, 4, 3, 'Hài nhưng hơi nhảm.'),
(3, 6, 5, 'Inception quá đỉnh.');

-- User 4 reviews
INSERT INTO reviews (user_id, movie_id, rating, content) VALUES
(4, 7, 4, 'Hơi sợ nhưng rất cuốn.'),
(4, 9, 5, 'Shawshank xứng đáng top IMDb.');

-- User 5 reviews
INSERT INTO reviews (user_id, movie_id, rating, content) VALUES
(5, 8, 3, 'Annabelle ổn nhưng không đáng sợ.'),
(5, 10, 5, 'Forrest Gump tuyệt vời!');


INSERT INTO comments (user_id, review_id, content) VALUES
(1, 1, 'Đồng ý luôn!'),
(2, 1, 'Công nhận là hay thật.'),
(3, 2, 'Mình cũng thích Interstellar.'),
(4, 3, 'John Wick bá cháy.'),
(5, 4, 'Deadpool bựa mà hài.'),
(1, 5, 'Haha đúng thật.'),
(2, 6, 'Inception hack não cực mạnh.'),
(3, 7, 'Phim này ám ảnh.'),
(4, 8, 'Cảnh vượt ngục quá hay.'),
(5, 9, 'Annabelle nhìn sợ vãi.'),
(1, 10, 'Forrest Gump luôn đỉnh.'),
(2, 8, 'Shawshank không thể chê.'),
(3, 6, 'Nolan chưa từng làm phim tệ.'),
(4, 4, 'Deadpool là số 1.'),
(5, 3, 'The Hangover đúng hài.'),
(1, 2, 'Phim này xem phải suy nghĩ.'),
(2, 5, 'Cười đau bụng luôn.'),
(3, 9, 'Quá hay, quá xúc động.'),
(4, 7, 'Hồi nhỏ xem sợ tè ra quần.'),
(5, 1, 'Endgame xứng đáng bom tấn.');