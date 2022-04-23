INSERT INTO user(id, first_name, last_name, pesel)
values (1, 'Kamil', 'Nowak', '89121995912'),
       (2, 'Robert', 'Pietron', '86090127256'),
       (3, 'Kasia', 'Kowalska', '93090765218'),
       (4, 'Ewa', 'Maj', '82022221113'),
       (5, 'Klaudia', 'Kowalska', '89020845413'),
       (6, 'Patrycja', 'Majewska', '98121473317'),
       (7, 'Michał', 'Lewandowski', '84052665954'),
       (8, 'Daniel', 'Boruc', '80012929979'),
       (9, 'Paweł', 'Pietroń', '81102388553'),
       (10, 'Paulina', 'Nowak', '96070249838'),
       (11, 'Janusz', 'Kajetanowicz', '88093029795'),
       (12, 'Michał', 'Kobzak', '89032424332'),
       (13, 'Jakub', 'Zelent', '92120396639');

INSERT INTO category(id, name, description)
values (1, 'Telefon', 'Służbowy telefon'),
       (2, 'Laptop', 'Firmowy laptop'),
       (3, 'Komputer', 'Firmowy komputer stacjonarny'),
       (4, 'Fotel', 'Firmowy fotel'),
       (5, 'Biurko', 'Firmowe biurko'),
       (6, 'Mysz', 'Firmowa mysz'),
       (7, 'Klawiatura', 'Firmowa klawiatura'),
       (8, 'Samochód', 'Służbowy samochód'),
       (9, 'Inne', 'Inne');

insert into item(id, name, description, serial_number, category_id)
values (1, 'Xiaomi 12 Pro 12', 'Xiaomi 12 Pro 12/256 GB Purple', 'XMI12P12X256P', 1),
       (2, 'Apple iPhone 13 Pro Max', 'Apple iPhone 13 Pro Max 1 TB Alpine Green', 'APIP13PM1TBAG', 1),
       (3, 'Samsung Galaxy Z Fold3 ', 'Samsung Galaxy Z Fold3 5G 512 GB srebrny', 'SGZF35G512GBS', 1),
       (4, 'Lenovo Legion 5', 'Lenovo Legion 5-17 Ryzen 7/16GB/512', 'LVLEG5R716512', 2),
       (5, 'Acer Nitro 5', 'Acer Nitro 5 R7-5800H/16GB/1TB', 'AN5R758000161', 2),
       (6, 'HP 17', 'HP 17 Ryzen 5-5500/16GB/512', 'HP17R55516512', 2),
       (7, 'ASUS VivoBook D712DA', 'ASUS VivoBook D712DA R3-3250U/8GB/512', 'ASVBD712DAR38', 2),
       (8, 'MSI GF76', 'MSI GF76 i7-11800H/16GB/512 ', 'MSGF76I716512', 2),
       (9, 'Dell Vostro 3888 MT', 'Dell Vostro 3888 MT i5-10400/16GB/480+1TB/Win11P', 'DV3888MTI5161', 3),
       (10, 'ASUS D500TC ExpertCenter', 'ASUS D500TC ExpertCenter i3-10105/8GB/256/Win10P', 'ASD500TCECI38', 3),
       (11, 'Silver Monkey SMG-700', 'Silver Monkey SMG-700', 'SILVMKYSMG700', 4),
       (12, 'Ultradesk GRAND', 'Ultradesk GRAND Czerwone', 'ULTDSKGRANDRD', 5),
       (13, 'Logitech M185', 'Logitech M185 Szara', 'LGTCHM185GREY', 6),
       (14, 'Microsoft 1850', 'Microsoft 1850 Wireless Mobile Mouse Czarny', 'MSFT1850WSMMB', 6),
       (15, 'Dell KB216-B', 'Dell KB216-B QuietKey USB Czarna', 'DLKB216BQKUBK', 7),
       (16, 'Logitech MX', 'Logitech MX Keys Mini Grafitowa', 'LOGMXKMNGRAPH', 7),
       (17, 'BMW X3', 'BMW X3 xDrive30e Hybryda', 'BMWX3XD30EHYB', 8),
       (18, 'BMW Seria 3', 'BMW 330i  Limuzyna Hybryda', 'BMW330ILIMHYB', 8),
       (19, 'Logitech C920', 'Kamerka internetowa Logitech C920 Pro Full HD', 'LGC920PFULLHD', 9),
       (20, 'HP Smart Tank 515', 'Drukarka HP Smart Tank 515 WiFi BLE', 'HPSTT515WFBLE', 9);

insert into assignment(id, start, end, item_id, user_id)
values (1, '2022-04-08 15:25:03', '2022-04-18 14:10:11', 1, 2),
       (2, '2022-01-09 10:45:52', '2022-03-17 11:01:02', 2, 6),
       (3, '2019-03-10 07:32:34', '2020-02-11 12:26:12', 3, 8),
       (4, '2018-11-22 09:15:05', '2019-11-08 07:40:41', 4, 11),
       (5, '2020-04-13 11:35:32', null, 5, 1),
       (6, '2021-01-16 15:31:06', null, 6, 10),
       (7, '2020-08-18 11:56:32', null, 7, 3),
       (8, '2020-11-09 10:13:12', null, 8, 7),
       (9, '2019-02-11 09:15:54', null, 9, 9);