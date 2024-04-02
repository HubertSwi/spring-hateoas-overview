DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS continents;

CREATE TABLE IF NOT EXISTS continents (
    id INT PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS countries (
    id INT PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    iso3 VARCHAR(3) NOT NULL,
    numeric_code VARCHAR(3) NOT NULL,
    iso2 VARCHAR(2) NOT NULL,
    phonecode VARCHAR(16) NOT NULL,
    capital VARCHAR(32) DEFAULT NULL,
    currency VARCHAR(32) DEFAULT NULL,
    currency_name VARCHAR(32) DEFAULT NULL,
    continent_id INT,
    subregion VARCHAR(32) DEFAULT NULL
);

INSERT INTO continents VALUES (1,   'Asia');
INSERT INTO continents VALUES (2,   'Oceania');
INSERT INTO continents VALUES (3,   'Africa');
INSERT INTO continents VALUES (4,   'Europe');
INSERT INTO continents VALUES (5,   'Americas');
INSERT INTO continents VALUES (6,   'Polar');

INSERT INTO countries VALUES (1,	'Afghanistan',			'AFG',	'004',	'AF',	'93',		'Kabul',			'AFN',	'Afghan afghani',			1,	'Southern Asia');
INSERT INTO countries VALUES (2,	'Aland Islands',		'ALA',	'248',	'AX',	'+358-18',	'Mariehamn',		'EUR',	'Euro',						4,	'Northern Europe');
INSERT INTO countries VALUES (3,	'Albania',				'ALB',	'008',	'AL',	'355',		'Tirana',			'ALL',	'Albanian lek',				4,	'Southern Europe');
INSERT INTO countries VALUES (4,	'Algeria',				'DZA',	'012',	'DZ',	'213',		'Algiers',			'DZD',	'Algerian dinar',			3,	'Northern Africa');
INSERT INTO countries VALUES (5,	'American Samoa',		'ASM',	'016',	'AS',	'+1-684',	'Pago Pago',		'USD',	'US Dollar',				2,	'Polynesia');
INSERT INTO countries VALUES (6,	'Andorra',				'AND',	'020',	'AD',	'376',		'Andorra la Vella',	'EUR',	'Euro',						4,	'Southern Europe');
INSERT INTO countries VALUES (7,	'Angola',				'AGO',	'024',	'AO',	'244',		'Luanda',			'AOA',	'Angolan kwanza',			3,	'Middle Africa');
INSERT INTO countries VALUES (8,	'Anguilla',				'AIA',	'660',	'AI',	'+1-264',	'The Valley',		'XCD',	'East Caribbean dollar',	5,	'Caribbean');
INSERT INTO countries VALUES (9,	'Antarctica',			'ATA',	'010',	'AQ',	'672',		'',					'AAD',	'Antarctican dollar',		6,	'');
INSERT INTO countries VALUES (10,	'Antigua And Barbuda',	'ATG',	'028',	'AG',	'+1-268',	'St. Johns',		'XCD',	'Eastern Caribbean dollar',	5,	'Caribbean');
INSERT INTO countries VALUES (11,	'Argentina',			'ARG',	'032',	'AR',	'54',		'Buenos Aires',		'ARS',	'Argentine peso',			5,	'South America');
INSERT INTO countries VALUES (12,	'Armenia',				'ARM',	'051',	'AM',	'374',		'Yerevan',			'AMD',	'Armenian dram',			1,	'Western Asia');
INSERT INTO countries VALUES (13,	'Aruba',				'ABW',	'533',	'AW',	'297',		'Oranjestad',		'AWG',	'Aruban florin',			5,	'Caribbean');
INSERT INTO countries VALUES (14,	'Australia',			'AUS',	'036',	'AU',	'61',		'Canberra',			'AUD',	'Australian dollar',		2,	'Australia and New Zealand');
INSERT INTO countries VALUES (15,	'Austria',				'AUT',	'040',	'AT',	'43',		'Vienna',			'EUR',	'Euro',						4,	'Western Europe');
INSERT INTO countries VALUES (16,	'Azerbaijan',			'AZE',	'031',	'AZ',	'994',		'Baku',				'AZN',	'Azerbaijani manat',		1,	'Western Asia');
INSERT INTO countries VALUES (17,	'The Bahamas',			'BHS',	'044',	'BS',	'+1-242',	'Nassau',			'BSD',	'Bahamian dollar',			5,	'Caribbean');
INSERT INTO countries VALUES (18,	'Bahrain',				'BHR',	'048',	'BH',	'973',		'Manama',			'BHD',	'Bahraini dinar',			1,	'Western Asia');
INSERT INTO countries VALUES (19,	'Bangladesh',			'BGD',	'050',	'BD',	'880',		'Dhaka',			'BDT',	'Bangladeshi taka',			1,	'Southern Asia');

ALTER TABLE countries ADD FOREIGN KEY (continent_id) REFERENCES countries(id);
