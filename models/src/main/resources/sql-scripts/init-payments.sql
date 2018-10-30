

INSERT INTO bikeshare_user(first_name, last_name, password, is_renting, funds) VALUES ('Mark', 'McGill', 's84j3n4', false, 65.34);
INSERT INTO bikeshare_user(first_name, last_name, password, is_renting, funds) VALUES ('Jenny', 'Parker', 't0pk3k', true, 0.00);


INSERT INTO payment(from_user_id, to_user_id, date, ride_id, amount) VALUES (1, 2, NOW(), 1, 3.20);