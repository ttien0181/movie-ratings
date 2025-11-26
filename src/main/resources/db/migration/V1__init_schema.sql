-- ===========================
-- 1. USERS
-- ===========================
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ===========================
-- 2. GENRES
-- ===========================
CREATE TABLE genre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
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
        ON DELETE CASCADE ON UPDATE CASCADE
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

INSERT INTO users (username, email, password, role) VALUES
('tu', 'tu@example.com', '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW', 'USER'),
('dang', 'dang@example.com', '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW', 'USER'),
('nam', 'nam@example.com', '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW', 'USER'),
('mai', 'mai@example.com', '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW', 'USER'),
('hung', 'hung@example.com', '$2a$12$nU1q2YF4ctwG4.Ck6POkVuvLIsgzoA1kBRtT2Y2fHW43wTjj.jrUW', 'USER');

INSERT INTO genre (name, description) VALUES
('Action', 'Phim hành động, đánh nhau, siêu anh hùng'),
('Comedy', 'Phim hài hước giải trí'),
('Drama', 'Phim tâm lý, cảm xúc'),
('Sci-Fi', 'Phim khoa học viễn tưởng, không gian'),
('Horror', 'Phim kinh dị, giật gân');

-- ★ THÊM poster_url vào INSERT MOVIES
INSERT INTO movies (id, title, description, release_year, rating, total_rate, actors, poster_url) VALUES
(1, 'Avengers: Endgame', 'Siêu anh hùng giải cứu vũ trụ', 2019, 4.75, 4, 'Robert Downey Jr, Chris Evans', 'https://image.tmdb.org/t/p/original/bR8ISy1O9XQxqiy0fQFw2BX72RQ.jpg'),
(2, 'John Wick 4', 'Sát thủ huyền thoại', 2023, 4.67, 3, 'Keanu Reeves', 'https://i.ebayimg.com/images/g/SRgAAOSwXY9kNpU~/s-l1600.webp'),
(3, 'Deadpool', 'Hài hành động siêu bựa', 2016, 4.00, 3, 'Ryan Reynolds', 'https://m.media-amazon.com/images/I/71SBA4bdx8L._AC_UF894,1000_QL80_.jpg'),
(4, 'The Hangover', 'Nhóm bạn lầy lội ở Las Vegas', 2009, 3.33, 3, 'Bradley Cooper', 'https://m.media-amazon.com/images/M/MV5BNDI2MzBhNzgtOWYyOS00NDM2LWE0OGYtOGQ0M2FjMTI2NTllXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg'),
(5, 'Interstellar', 'Hành trình xuyên không gian cứu Trái Đất', 2014, 4.75, 4, 'Matthew McConaughey', 'https://m.media-amazon.com/images/I/91obuWzA3XL._AC_UF894,1000_QL80_.jpg'),
(6, 'Inception', 'Thế giới nằm trong giấc mơ', 2010, 5.00, 2, 'Leonardo DiCaprio', 'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg'),
(7, 'The Conjuring', 'Gia đình gặp thế lực siêu nhiên', 2013, 3.67, 3, 'Patrick Wilson', 'https://m.media-amazon.com/images/I/81NwnEjW27L._AC_SL1500_.jpg'),
(8, 'Annabelle', 'Búp bê ma ám', 2014, 2.50, 2, 'Annabelle', 'https://m.media-amazon.com/images/M/MV5BNjkyMDU5ZWQtZDhkOC00ZWFjLWIyM2MtZWFhMDUzNjdlNzU2XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg'),
(9, 'The Shawshank Redemption', 'Vượt ngục kinh điển, top IMDb', 1994, 5.00, 2, 'Tim Robbins', 'https://m.media-amazon.com/images/I/911USrdQtPL.jpg'),
(10, 'Forrest Gump', 'Cuộc đời đặc biệt của Forrest', 1994, 5.00, 3, 'Tom Hanks', 'https://m.media-amazon.com/images/I/613ZgTigTpL.jpg');

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
INSERT INTO reviews (user_id, movie_id, rating, content) VALUES
(1, 1, 5, 'Phim quá xuất sắc, xem lại vẫn hay!'),
(1, 5, 5, 'Một tuyệt phẩm khoa học viễn tưởng.'),
(2, 2, 4, 'John Wick đánh nhau chất chơi.'),
(2, 3, 4, 'Deadpool bựa nhưng hài.'),
(3, 4, 3, 'Hài nhưng hơi nhảm.'),
(3, 6, 5, 'Inception quá đỉnh.'),
(4, 7, 4, 'Hơi sợ nhưng rất cuốn.'),
(4, 9, 5, 'Shawshank xứng đáng top IMDb.'),
(5, 8, 3, 'Annabelle ổn nhưng không đáng sợ.'),
(5, 10, 5, 'Forrest Gump tuyệt vời!'),
(1, 2, 5, 'John Wick phần này quá mãn nhãn.'),
(1, 3, 4, 'Deadpool đúng chất bựa, cười muốn xỉu.'),
(1, 4, 4, 'The Hangover đúng kiểu giải trí cuối tuần.'),
(2, 1, 5, 'Xem lại Endgame lần thứ 4 vẫn nổi da gà.'),
(2, 5, 5, 'Interstellar đúng nghĩa masterpiece.'),
(2, 6, 5, 'Inception xem 3 lần mới hiểu hết.'),
(3, 1, 4, 'Giá như Endgame dài thêm nữa.'),
(3, 5, 4, 'Interstellar xem là nghiện luôn.'),
(3, 7, 3, 'Conjuring khiến mình mất ngủ 1 đêm.'),
(4, 2, 5, 'John Wick đánh đẹp như một vũ công.'),
(4, 3, 4, 'Deadpool bản này vui thật sự.'),
(4, 10, 5, 'Forrest Gump mang lại niềm tin vào cuộc sống.'),
(5, 1, 5, 'Endgame là tuổi thơ của mình.'),
(5, 4, 3, 'Hangover hài nhưng không hợp gu.'),
(5, 6, 5, 'Inception luôn khiến mình suy nghĩ.'),
(1, 9, 5, 'Shawshank là phim mình thích nhất mọi thời đại.'),
(2, 8, 2, 'Annabelle hơi gây thất vọng.'),
(3, 10, 5, 'Forrest Gump xem xong muốn sống tốt hơn.'),
(4, 5, 5, 'Interstellar khiến mình khóc luôn.'),
(5, 7, 4, 'Conjuring phần này đáng sợ thật.');

-- COMMENTS
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
(5, 1, 'Endgame xứng đáng bom tấn.'),
(1, 11, 'Chuẩn luôn bạn ơi!'),
(2, 12, 'Mình cũng thấy vậy.'),
(3, 13, 'Đánh giá hợp lý.'),
(4, 14, 'Endgame bất tử!'),
(5, 15, 'Inception luôn hay.'),
(1, 16, 'Shawshank quá kinh điển.'),
(2, 17, 'Annabelle phần này dở thiệt.'),
(3, 18, 'Forrest Gump đúng kiểu truyền cảm hứng.'),
(4, 19, 'Interstellar xem là mê!'),
(5, 20, 'Conjuring đáng sợ vãi.'),
(1, 11, 'Tôi vote 10/10.'),
(2, 12, 'Chuẩn gu của mình.'),
(3, 13, 'Mình xem ít nhất 3 lần.'),
(4, 14, 'Endgame sống mãi trong lòng fan.'),
(5, 15, 'Inception chưa bao giờ làm mình thất vọng.'),
(1, 16, 'Shawshank đúng top 1 luôn.'),
(2, 17, 'Annabelle chỉ được cái trailer.'),
(3, 18, 'Forrest Gump quá cảm động.'),
(4, 19, 'Interstellar khiến mình nổi da gà.'),
(5, 20, 'Conjuring xem ban đêm không dám ngủ.'),
(1, 21, 'Nội dung thuyết phục ghê.'),
(2, 22, 'John Wick phần này xuất sắc.'),
(3, 23, 'Deadpool hài nhất luôn.'),
(4, 24, 'Hangover là phim tôi yêu thích.'),
(5, 25, 'Inception đỉnh như mọi khi.'),
(1, 26, 'Shawshank quá cảm xúc.'),
(2, 27, 'Annabelle hơi lố.'),
(3, 28, 'Forrest là idol của tôi.'),
(4, 29, 'Interstellar mãi đỉnh!'),
(5, 30, 'Conjuring làm tôi mất ngủ 2 đêm.'),
(1, 21, 'Bạn nói đúng thật.'),
(2, 22, 'John Wick đánh quá đẹp.'),
(3, 23, 'Deadpool tôi xem 5 lần rồi.'),
(4, 24, 'Hangover là phim giải trí tuyệt vời.'),
(5, 25, 'Inception khó hiểu nhưng đáng xem.'),
(1, 26, 'Shawshank luôn top 1.'),
(2, 27, 'Annabelle phần này hơi chán.'),
(3, 28, 'Forrest Gump là phim không thể bỏ qua.'),
(4, 29, 'Interstellar xứng đáng 10 điểm.'),
(5, 30, 'Conjuring ám ảnh ghê.');
