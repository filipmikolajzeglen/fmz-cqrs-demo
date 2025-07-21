INSERT INTO cqrsdemo.user_a (id, name, email) VALUES (1, 'Jan Kowalski', 'jan.kowalski@example.com');
INSERT INTO cqrsdemo.user_a (id, name, email) VALUES (2, 'Anna Nowak', 'anna.nowak@example.com');
INSERT INTO cqrsdemo.user_a (id, name, email) VALUES (3, 'Piotr Zieliński', 'piotr.zielinski@example.com');
INSERT INTO cqrsdemo.user_a (id, name, email) VALUES (4, 'Piotr Zieliński', 'piotr.zielinski2@example.com');

INSERT INTO cqrsdemo.user_b (id, name, email) VALUES (1, 'Jan Kowalski', 'jan.kowalski@example.com');
INSERT INTO cqrsdemo.user_b (id, name, email) VALUES (2, 'Anna Nowak', 'anna.nowak@example.com');
INSERT INTO cqrsdemo.user_b (id, name, email) VALUES (3, 'Piotr Zieliński', 'piotr.zielinski@example.com');
INSERT INTO cqrsdemo.user_b (id, name, email) VALUES (4, 'Piotr Zieliński', 'piotr.zielinski2@example.com');

INSERT INTO cqrsdemo.user_c (id, name, email) VALUES (1, 'Jan Kowalski', 'jan.kowalski@example.com');
INSERT INTO cqrsdemo.user_c (id, name, email) VALUES (2, 'Anna Nowak', 'anna.nowak@example.com');
INSERT INTO cqrsdemo.user_c (id, name, email) VALUES (3, 'Piotr Zieliński', 'piotr.zielinski@example.com');
INSERT INTO cqrsdemo.user_c (id, name, email) VALUES (4, 'Piotr Zieliński', 'piotr.zielinski2@example.com');

ALTER SEQUENCE cqrsdemo.user_a_seq RESTART WITH 5;
ALTER SEQUENCE cqrsdemo.user_b_seq RESTART WITH 5;
ALTER SEQUENCE cqrsdemo.user_c_seq RESTART WITH 5;