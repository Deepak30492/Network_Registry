
CREATE TABLE subscribers (subscriber_id INT PRIMARY KEY, country VARCHAR(255), city VARCHAR(255), domain VARCHAR(255), unique_key_id VARCHAR(255),
 pub_key_id VARCHAR(255), signing_public_key VARCHAR(255), encr_public_key VARCHAR(255), valid_from VARCHAR(255), valid_to VARCHAR(255), status VARCHAR(255), 
created VARCHAR(255), updated VARCHAR(255), radius VARCHAR(255), type VARCHAR(255), url VARCHAR(255));

INSERT INTO subscribers(subscriber_id, country, city, status, type, url) VALUES(1,'india','pune','SUBSCRIBED','BPP','http://localhost:3232');

delete from subscribers where subscriber_id=1

SELECT * FROM subscribers;