-- ===========================
-- 1. USERS
-- ===========================
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    banned BOOLEAN NOT NULL DEFAULT FALSE
);

-- ===========================
-- 2. GENRES
-- ===========================
CREATE TABLE genre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    thumbnail_url VARCHAR(500)
);

-- ===========================
-- 3. MOVIES  (ĐÃ SỬA)
-- ===========================
CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_year INT,
    rating FLOAT,
    total_rate INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    actors TEXT,
    poster_url VARCHAR(500)  -- ★ THÊM MỚI DO BẠN THÊM TRONG ENTITY
);

-- ===========================
-- 4. MOVIE_GENRES (bảng N-N)
-- ===========================
CREATE TABLE movie_genres (
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,

    PRIMARY KEY (movie_id, genre_id),

    FOREIGN KEY (movie_id) REFERENCES movies(id)
        ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (genre_id) REFERENCES genre(id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

-- ===========================
-- 5. REVIEWS
-- ===========================
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

-- ===========================
-- 6. COMMENTS
-- ===========================
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

-- ===========================
-- 7. VERIFICATION_CODES
-- ===========================
CREATE TABLE verification_codes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    type ENUM('REGISTRATION', 'PASSWORD_RESET') NOT NULL,
    expiry_time DATETIME NOT NULL,
    used BOOLEAN NOT NULL DEFAULT FALSE
);

-- ===========================
-- SAMPLE DATA
-- ===========================

INSERT INTO users (username, email, password, role, created_at, banned) VALUES
('tu', 'tu@example.com',
 '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW',
 'ADMIN', '2024-10-27 10:00:00', FALSE),

('dang', 'dang@example.com',
 '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW',
 'USER', '2024-11-03 14:20:00', FALSE),

('nam', 'nam@example.com',
 '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW',
 'USER', '2024-11-10 09:45:00', FALSE),

('mai', 'mai@example.com',
 '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW',
 'USER', '2024-11-18 12:10:00', FALSE),

('hung', 'hung@example.com',
 '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW',
 'USER', '2024-11-27 17:30:00', FALSE);

INSERT INTO genre (name, description, thumbnail_url) VALUES
('Action', 'Phim hành động, đánh nhau, siêu anh hùng', 'https://www.nyfa.edu/wp-content/uploads/2022/11/action-movie.jpg'),
('Comedy', 'Phim hài hước giải trí', 'https://www.blackpoolgrand.co.uk/app/uploads/2021/09/Book-of-Mormon-Best-Stage-Comedy-Plays.jpg'),
('Drama', 'Phim tâm lý, cảm xúc', 'https://dumloongdigital.com/public/medies/Aug_2023/1691009967.64cac3af9bb81.jpg'),
('Sci-Fi', 'Phim khoa học viễn tưởng, không gian', 'https://nofilmschool.com/media-library/zz3340101d.jpg?id=34083566&width=1245&height=700&coordinates=67%2C0%2C68%2C0'),
('Horror', 'Phim kinh dị, giật gân', 'https://gwtoday.gwu.edu/sites/g/files/zaxdzs5401/files/2023-10/howtostillsurviveahorrormovie-conceptssermontaglines-1920x1080_3_1_0.jpeg');

-- ★ THÊM poster_url vào INSERT MOVIES
INSERT INTO movies (id, title, description, release_year, rating, total_rate, actors, poster_url, created_at) VALUES
(1,'Avengers: Endgame','Siêu anh hùng giải cứu vũ trụ',2019,4.75,4,'Robert Downey Jr, Chris Evans',
'https://image.tmdb.org/t/p/original/bR8ISy1O9XQxqiy0fQFw2BX72RQ.jpg','2020-02-10 10:00:00'),

(2,'John Wick 4','Sát thủ huyền thoại',2023,4.67,3,'Keanu Reeves',
'https://i.ebayimg.com/images/g/SRgAAOSwXY9kNpU~/s-l1600.webp','2023-08-15 09:30:00'),

(3,'Deadpool','Hài hành động siêu bựa',2016,4.00,3,'Ryan Reynolds',
'https://m.media-amazon.com/images/I/71SBA4bdx8L._AC_UF894,1000_QL80_.jpg','2017-03-20 14:15:00'),

(4,'The Hangover','Nhóm bạn lầy lội ở Las Vegas',2009,3.33,3,'Bradley Cooper',
'https://m.media-amazon.com/images/M/MV5BNDI2MzBhNzgtOWYyOS00NDM2LWE0OGYtOGQ0M2FjMTI2NTllXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg','2010-01-10 08:00:00'),

(5,'Interstellar','Hành trình xuyên không gian',2014,4.75,4,'Matthew McConaughey',
'https://m.media-amazon.com/images/I/91obuWzA3XL._AC_UF894,1000_QL80_.jpg','2015-04-05 16:40:00'),

(6,'Inception','Thế giới trong giấc mơ',2010,5.00,2,'Leonardo DiCaprio',
'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg','2011-02-17 11:20:00'),

(7,'The Conjuring','Gia đình gặp thế lực siêu nhiên',2013,3.67,3,'Patrick Wilson',
'https://m.media-amazon.com/images/I/81NwnEjW27L._AC_SL1500_.jpg','2014-06-09 20:00:00'),

(8,'Annabelle','Búp bê ma ám',2014,2.50,2,'Annabelle',
'https://m.media-amazon.com/images/M/MV5BNjkyMDU5ZWQtZDhkOC00ZWFjLWIyM2MtZWFhMDUzNjdlNzU2XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg','2015-02-22 09:00:00'),

(9,'The Shawshank Redemption','Vượt ngục kinh điển',1994,5.00,2,'Tim Robbins',
'https://m.media-amazon.com/images/I/911USrdQtPL.jpg','1995-03-11 13:10:00'),

(10,'Forrest Gump','Cuộc đời đặc biệt của Forrest',1994,5.00,3,'Tom Hanks',
'https://m.media-amazon.com/images/I/613ZgTigTpL.jpg','1995-01-21 12:00:00');


INSERT INTO movie_genres (movie_id, genre_id) VALUES
(1, 1), (1, 3), (1, 4),      -- Endgame: Action, Drama, Sci-Fi
(2, 1), (2, 3),              -- John Wick: Action, Drama
(3, 1), (3, 2),              -- Deadpool: Action, Comedy
(4, 2), (4, 3),              -- Hangover: Comedy, Drama
(5, 3), (5, 4),              -- Interstellar: Drama, Sci-Fi
(6, 3), (6, 4),              -- Inception: Drama, Sci-Fi
(7, 5), (7, 3),              -- Conjuring: Horror, Drama
(8, 5),                      -- Annabelle: Horror
(9, 3),                      -- Shawshank: Drama
(10, 3);                     -- Forrest Gump: Drama


-- REVIEWS
INSERT INTO reviews (user_id, movie_id, rating, content, created_at) VALUES
(1,1,5,'Phim quá xuất sắc, xem lại vẫn hay!','2021-05-10 10:20:00'),
(1,5,5,'Một tuyệt phẩm khoa học viễn tưởng.','2021-05-11 14:10:00'),
(2,2,4,'John Wick đánh nhau chất chơi.','2023-09-01 09:00:00'),
(2,3,4,'Deadpool bựa nhưng hài.','2021-07-22 11:11:00'),
(3,4,3,'Hài nhưng hơi nhảm.','2021-03-18 08:40:00'),
(3,6,5,'Inception quá đỉnh.','2022-02-10 13:50:00'),
(4,7,4,'Hơi sợ nhưng rất cuốn.','2021-06-17 19:00:00'),
(4,9,5,'Shawshank xứng đáng top IMDb.','2022-09-21 17:20:00'),
(5,8,3,'Annabelle ổn nhưng không đáng sợ.','2022-04-02 12:10:00'),
(5,10,5,'Forrest Gump tuyệt vời!','2021-12-25 20:00:00'),
(1,2,5,'John Wick phần này quá mãn nhãn.','2023-09-03 10:33:00'),
(1,3,4,'Deadpool đúng chất bựa, cười muốn xỉu.','2021-08-13 15:20:00'),
(1,4,4,'The Hangover đúng kiểu giải trí cuối tuần.','2021-04-01 09:55:00'),
(2,1,5,'Xem lại Endgame lần thứ 4 vẫn nổi da gà.','2022-03-18 14:00:00'),
(2,5,5,'Interstellar đúng nghĩa masterpiece.','2021-12-28 08:10:00'),
(2,6,5,'Inception xem 3 lần mới hiểu hết.','2022-03-22 11:50:00'),
(3,1,4,'Giá như Endgame dài thêm nữa.','2021-05-20 15:33:00'),
(3,5,4,'Interstellar xem là nghiện luôn.','2021-05-23 16:00:00'),
(3,7,3,'Conjuring khiến mình mất ngủ 1 đêm.','2022-08-01 21:20:00'),
(4,2,5,'John Wick đánh đẹp như một vũ công.','2023-09-05 07:40:00'),
(4,3,4,'Deadpool bản này vui thật sự.','2021-09-11 10:44:00'),
(4,10,5,'Forrest Gump mang lại niềm tin vào cuộc sống.','2022-10-10 12:00:00'),
(5,1,5,'Endgame là tuổi thơ của mình.','2021-06-01 13:30:00'),
(5,4,3,'Hangover hài nhưng không hợp gu.','2021-04-03 08:10:00'),
(5,6,5,'Inception luôn khiến mình suy nghĩ.','2022-04-12 17:50:00'),
(1,9,5,'Shawshank là phim mình thích nhất mọi thời đại.','2022-11-11 19:40:00'),
(2,8,2,'Annabelle hơi gây thất vọng.','2022-05-01 10:10:00'),
(3,10,5,'Forrest Gump xem xong muốn sống tốt hơn.','2023-01-01 18:20:00'),
(4,5,5,'Interstellar khiến mình khóc luôn.','2021-12-29 09:00:00'),
(5,7,4,'Conjuring phần này đáng sợ thật.','2022-08-04 13:10:00');


-- COMMENTS
INSERT INTO comments (user_id, review_id, content, created_at) VALUES
(1, 1, 'Đồng ý luôn!', '2021-05-10 12:40:00'),
(2, 1, 'Công nhận là hay thật.', '2021-05-10 15:10:00'),

(3, 2, 'Mình cũng thích Interstellar.', '2021-05-12 10:30:00'),

(4, 3, 'John Wick bá cháy.', '2023-09-01 11:20:00'),

(5, 4, 'Deadpool bựa mà hài.', '2021-07-22 15:40:00'),

(1, 5, 'Haha đúng thật.', '2021-03-18 10:10:00'),

(2, 6, 'Inception hack não cực mạnh.', '2022-02-10 17:30:00'),

(3, 7, 'Phim này ám ảnh.', '2021-06-18 01:20:00'),

(4, 8, 'Cảnh vượt ngục quá hay.', '2022-09-22 08:30:00'),

(5, 9, 'Annabelle nhìn sợ vãi.', '2022-04-03 09:20:00'),

(1, 10, 'Forrest Gump luôn đỉnh.', '2021-12-26 10:00:00'),

(2, 8, 'Shawshank không thể chê.', '2022-09-22 10:40:00'),

(3, 6, 'Nolan chưa từng làm phim tệ.', '2022-02-11 10:00:00'),

(4, 4, 'Deadpool là số 1.', '2021-07-23 09:20:00'),

(5, 3, 'The Hangover đúng hài.', '2023-09-01 13:00:00'),

(1, 2, 'Phim này xem phải suy nghĩ.', '2021-05-12 08:00:00'),

(2, 5, 'Cười đau bụng luôn.', '2021-03-18 13:20:00'),

(3, 9, 'Quá hay, quá xúc động.', '2022-04-03 14:10:00'),

(4, 7, 'Hồi nhỏ xem sợ tè ra quần.', '2021-06-18 05:00:00'),

(5, 1, 'Endgame xứng đáng bom tấn.', '2021-05-11 08:50:00'),

(1, 11, 'Chuẩn luôn bạn ơi!', '2023-09-04 09:20:00'),
(2, 12, 'Mình cũng thấy vậy.', '2021-08-14 12:00:00'),
(3, 13, 'Đánh giá hợp lý.', '2021-04-02 08:40:00'),
(4, 14, 'Endgame bất tử!', '2022-03-19 10:20:00'),
(5, 15, 'Inception luôn hay.', '2021-12-29 10:00:00'),

(1, 16, 'Shawshank quá kinh điển.', '2022-03-23 16:00:00'),
(2, 17, 'Annabelle phần này dở thiệt.', '2021-05-21 08:20:00'),
(3, 18, 'Forrest Gump đúng kiểu truyền cảm hứng.', '2021-05-24 09:30:00'),
(4, 19, 'Interstellar xem là mê!', '2022-08-02 18:40:00'),
(5, 20, 'Conjuring đáng sợ vãi.', '2023-09-06 08:20:00'),

(1, 11, 'Tôi vote 10/10.', '2023-09-04 12:40:00'),
(2, 12, 'Chuẩn gu của mình.', '2021-08-14 17:00:00'),
(3, 13, 'Mình xem ít nhất 3 lần.', '2021-04-02 12:00:00'),
(4, 14, 'Endgame sống mãi trong lòng fan.', '2022-03-19 13:30:00'),
(5, 15, 'Inception chưa bao giờ làm mình thất vọng.', '2021-12-29 14:00:00'),

(1, 16, 'Shawshank luôn top 1.', '2022-03-24 10:40:00'),
(2, 17, 'Annabelle chỉ được cái trailer.', '2021-05-21 11:10:00'),
(3, 18, 'Forrest Gump quá cảm động.', '2021-05-24 11:40:00'),
(4, 19, 'Interstellar khiến mình nổi da gà.', '2022-08-02 22:30:00'),
(5, 20, 'Conjuring xem ban đêm không dám ngủ.', '2023-09-06 12:30:00'),

(1, 21, 'Nội dung thuyết phục ghê.', '2021-09-12 14:20:00'),
(2, 22, 'John Wick phần này xuất sắc.', '2022-10-11 09:00:00'),
(3, 23, 'Deadpool hài nhất luôn.', '2021-06-02 10:20:00'),
(4, 24, 'Hangover là phim tôi yêu thích.', '2021-04-04 15:20:00'),
(5, 25, 'Inception đỉnh như mọi khi.', '2022-04-13 09:10:00'),

(1, 26, 'Shawshank quá cảm xúc.', '2022-11-12 10:40:00'),
(2, 27, 'Annabelle hơi lố.', '2022-05-02 12:20:00'),
(3, 28, 'Forrest là idol của tôi.', '2023-01-02 12:00:00'),
(4, 29, 'Interstellar mãi đỉnh!', '2021-12-30 13:40:00'),
(5, 30, 'Conjuring làm tôi mất ngủ 2 đêm.', '2022-08-05 11:40:00'),

(1, 21, 'Bạn nói đúng thật.', '2021-09-12 16:40:00'),
(2, 22, 'John Wick đánh quá đẹp.', '2022-10-11 12:40:00'),
(3, 23, 'Deadpool tôi xem 5 lần rồi.', '2021-06-02 12:00:00'),
(4, 24, 'Hangover là phim giải trí tuyệt vời.', '2021-04-04 17:10:00'),
(5, 25, 'Inception khó hiểu nhưng đáng xem.', '2022-04-13 12:10:00'),

(1, 26, 'Shawshank luôn top 1.', '2022-11-12 14:10:00'),
(2, 27, 'Annabelle phần này hơi chán.', '2022-05-02 13:30:00'),
(3, 28, 'Forrest Gump là phim không thể bỏ qua.', '2023-01-02 15:10:00'),
(4, 29, 'Interstellar xứng đáng 10 điểm.', '2021-12-30 15:40:00'),
(5, 30, 'Conjuring ám ảnh ghê.', '2022-08-05 14:00:00');
