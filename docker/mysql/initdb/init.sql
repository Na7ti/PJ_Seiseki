-- クラステーブルの作成
CREATE TABLE class (
    class_id INT PRIMARY KEY,
    class_name VARCHAR(50) NOT NULL,
    academic_year INT NOT NULL
);

-- 学生テーブルの作成
CREATE TABLE student (
    student_id_number INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    class_id INT,
    mail_address VARCHAR(100) UNIQUE,
    is_active BOOLEAN DEFAULT TRUE,
    admission_year INT NOT NULL,
    FOREIGN KEY (class_id) REFERENCES class(class_id)
);

-- 出席テーブルの作成
CREATE TABLE attendance (
    serial_number INT AUTO_INCREMENT PRIMARY KEY,
    student_id_number INT,
    date DATE NOT NULL,
    attend BOOLEAN,
    time_in TIME,
    time_out TIME,
    semester VARCHAR(10),
    FOREIGN KEY (student_id_number) REFERENCES student(student_id_number)
);

-- クラステーブルにデータを挿入
INSERT INTO class (class_id, class_name, academic_year) VALUES
(1, 'ITエンジニア', 2023),
(2, 'ITイノベーション', 2023),
(3, 'ゲームクリエイター', 2023),
(4, 'AIスペシャリスト', 2023),
(5, 'ITエンジニア', 2024),
(6, 'ITイノベーション', 2024),
(7, 'ゲームクリエイター', 2024),
(8, 'AIスペシャリスト', 2024);

-- 学生テーブルにデータを挿入
INSERT INTO student (student_id_number, name, class_id, mail_address, is_active, admission_year) VALUES
(01970001, '野比 のび太', 5, 'nobi@example.com', TRUE, 2024),
(01970002, '源 静香', 5, 'minamoto@example.com', TRUE, 2024),
(01970003, '剛田 武', 6, 'goda@example.com', TRUE, 2024),
(01970004, '骨川 スネ夫', 7, 'honekawa@example.com', TRUE, 2024),
(01970005, '出木杉 英才', 5, 'dekisugi@example.com', FALSE, 2024),
(02970001, 'ドラえもん', 1, 'doraemon@example.com', TRUE, 2023);

-- 出席テーブルにデータを挿入
INSERT INTO attendance (serial_number, student_id_number, date, attend, time_in, time_out, semester) VALUES
(1, 01970001, '2024-10-07', FALSE, NULL, NULL, '前期'),
(2, 01970002, '2024-10-07', TRUE, '09:00:00', '16:30:00', '前期'),
(3, 01970003, '2024-10-07', FALSE, NULL, NULL, '前期'),
(4, 01970004, '2024-10-07', TRUE, '08:55:00', '16:45:00', '前期'),
(5, 01970001, '2024-10-08', TRUE, '09:05:00', '16:40:00', '前期'),
(6, 01970002, '2024-10-08', TRUE, '08:50:00', '16:35:00', '前期'),
(7, 01970003, '2024-10-08', TRUE, '09:00:00', '16:30:00', '前期'),
(8, 01970004, '2024-10-08', FALSE, NULL, NULL, '前期'),
(9, 01970005, '2024-10-07', TRUE, '08:45:00', '16:50:00', '前期'),
(10, 01970005, '2024-10-08', FALSE, NULL, NULL, '前期'),
(11, 02970001, '2024-10-07', TRUE, '08:30:00', '17:00:00', '前期'),
(12, 02970001, '2024-10-08', TRUE, '08:35:00', '16:55:00', '前期');
