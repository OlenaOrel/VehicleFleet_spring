insert into vehicle_fleet.user (`first_name`, `last_name`, `origin_first_name`,
                                `origin_last_name`, `email`, `password`, `role`)
values ('Mikola', 'Ivanov', 'Микола', 'Іванов', 'ivanov@gmail.com',
        '$2a$10$pQOGfl0lFp34yH.LVHa5KuIG2Ch8QPFcZSoiJDUKP8dx2qURr2C3u', 'ROLE_ADMIN'), #password: 12345
       ('Oleksandr', 'Ptushkin', 'Олександр', 'Птушкін', 'ptushkin@gmail.com',
        '$10$iUCLJMX3j5fJ68t41hIzUu6lF.eRoAHYoRLOgPBFrfsmhDJvxUTam', 'ROLE_DRIVER'),
       ('Ivan', 'Tkach', 'Іван', 'Ткач', 'tkach@gmail.com',
        '$2a$10$iUCLJMX3j5fJ68t41hIzUu6lF.eRoAHYoRLOgPBFrfsmhDJvxUTam', 'ROLE_DRIVER'),
       ('Maksim', 'Smilenko', 'Максим', 'Сміленко', 'smilenko@gmail.com',
        '$2a$10$iUCLJMX3j5fJ68t41hIzUu6lF.eRoAHYoRLOgPBFrfsmhDJvxUTam', 'ROLE_DRIVER'),
       ('Andriy', 'Orel', 'Андрій', 'Орел', 'orel@gmail.com',
        '$2a$10$iUCLJMX3j5fJ68t41hIzUu6lF.eRoAHYoRLOgPBFrfsmhDJvxUTam', 'ROLE_DRIVER');

insert into vehicle_fleet.bus (`mark`, `license_plate`)
values ('Toyota', 'AA 1234 PP'),
       ('Mercedes-Benz', 'AA 6789 PP'),
       ('Volkswagen', 'AA 0147 PP'),
       ('Fiat', 'AA 6438 PP');

insert into vehicle_fleet.route (`number`, `departure_from_city`, `departure_from_city_uk`,
                                 `arrival_to_city`, `arrival_to_city_uk`)
values ('102', 'Prague', 'Прага', 'Kyiv', 'Київ'),
       ('103', 'Kyiv', 'Київ', 'Prague', 'Прага'),
       ('104', 'Warsaw', 'Варшава', 'Kyiv', 'Київ'),
       ('105', 'Kyiv', 'Київ', 'Warsaw', 'Варшава');

insert into vehicle_fleet.bus_driver (`driver_id`, `bus_id`)
values (2, 2),
       (2, 3),
       (3, 3),
       (3, 4),
       (4, 4),
       (4, 1),
       (5, 2),
       (5, 1);e