-- Insertar datos en la tabla doctor
INSERT INTO doctor (mail, pass, name)
VALUES 
    ('sofiahernandez@example.com', '1234', 'Sofia Hernandez'),
    ('alejandrosilva@example.com', '1234', 'Alejandro Silva'),
    ('gabrielaramos@example.com', '1234', 'Gabriela Ramos');

-- Insertar datos en la tabla patient
INSERT INTO patient (mail, name)
VALUES
    ('juanperez@example.com', 'Juan Pérez'),
    ('mariarodriguez@example.com', 'María Rodríguez'),
    ('carlosgarcia@example.com', 'Carlos García'),
    ('lauralopez@example.com', 'Laura López'),
    ('andresmartinez@example.com', 'Andrés Martínez');

-- Insertar datos en la tabla medicine
INSERT INTO medicine (id, name, tmax, tmin)
VALUES 
    (1, 'Paracetamol', 1.0, 0.5),
    (2, 'Ibuprofeno', 2.0, 1.0),
    (3, 'Amoxicilina', 1.5, 0.75),
    (4, 'Omeprazol', 0.5, 0.25),
    (5, 'Atorvastatina', 2.5, 1.0);

-- Insertar datos en la tabla xip
INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date)
VALUES
    (1, 'alejandrosilva@example.com', 1, 'juanperez@example.com', '2024-05-29'),
    (2, 'sofiahernandez@example.com', 2, 'mariarodriguez@example.com', '2024-05-29'),
    (3, 'alejandrosilva@example.com', 3, 'carlosgarcia@example.com', '2024-05-30'),
    (4, 'sofiahernandez@example.com', 4, 'lauralopez@example.com', '2024-05-30'),
    (5, 'gabrielaramos@example.com', 5, 'andresmartinez@example.com', '2024-05-31');
