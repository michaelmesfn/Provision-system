INSERT INTO contracts (contract_id, begin_date, end_date, intermediary_id, amount, intermediary_share) VALUES (1,'2023-08-01', '2029-08-30', 1 , 1200 , 10);
INSERT INTO contracts (contract_id, begin_date, end_date, intermediary_id, amount, intermediary_share) VALUES (2,'2023-08-02', '2029-09-30', 2 , 1000 , 15);
INSERT INTO contracts (contract_id, begin_date, end_date, intermediary_id, amount, intermediary_share) VALUES (3,'2023-08-03', '2029-10-30', 2 , 100 , 5);
INSERT INTO contracts (contract_id, begin_date, end_date, intermediary_id, amount, intermediary_share) VALUES (4,'2023-08-04', '2029-11-30', 3 , 2500, 20);
INSERT INTO contracts (contract_id, begin_date, end_date, intermediary_id, amount, intermediary_share) VALUES (5,'2023-08-05', '2029-12-30', 3 , 15000, 10);

INSERT INTO intermediaries (intermediary_id, sum_monthly_provisions) VALUES (1, 120);
INSERT INTO intermediaries (intermediary_id, sum_monthly_provisions) VALUES (2, 155);
INSERT INTO intermediaries (intermediary_id, sum_monthly_provisions) VALUES (3, 2000);
INSERT INTO intermediaries (intermediary_id, sum_monthly_provisions) VALUES (4, 0);