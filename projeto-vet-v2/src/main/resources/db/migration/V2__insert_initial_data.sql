
-- V2 - DADOS INICIAIS



-- USUARIO

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(1, 'Ana Souza', 'ana@email.com', 'senha1', '11999990001', 'RESPONSAVEL');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(2, 'Carlos Lima', 'carlos@email.com', 'senha2', '11999990002', 'RESPONSAVEL');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(3, 'Marina Alves', 'marina@email.com', 'senha3', '11999990003', 'RESPONSAVEL');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(4, 'Dr. Paulo Mendes', 'paulo@vet.com', 'senha4', '11999990004', 'VETERINARIO');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(5, 'Dra. Juliana Rocha', 'juliana@vet.com', 'senha5', '11999990005', 'VETERINARIO');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(6, 'Fernanda Costa', 'fernanda@email.com', 'senha6', '11999990006', 'RESPONSAVEL');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(7, 'Ricardo Santos', 'ricardo@email.com', 'senha7', '11999990007', 'RESPONSAVEL');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(8, 'Dr. Marcelo Nunes', 'marcelo@vet.com', 'senha8', '11999990008', 'VETERINARIO');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(9, 'Dra. Beatriz Lopes', 'beatriz@vet.com', 'senha9', '11999990009', 'VETERINARIO');

INSERT INTO Usuario
(id_usuario, nome, email, senha, telefone, tipo_usuario)
VALUES
(10, 'Dr. Rafael Martins', 'rafael@vet.com', 'senha10', '11999990010', 'VETERINARIO');



-- CLINICA


INSERT INTO Clinica
(id_clinica, nome, cnpj, telefone, email, endereco)
VALUES
(1, 'Clinica Pet Vida', '11111111000111', '1133330001',
 'contato@petvida.com', 'Rua A, 100');

INSERT INTO Clinica
(id_clinica, nome, cnpj, telefone, email, endereco)
VALUES
(2, 'Vet Center', '22222222000122', '1133330002',
 'contato@vetcenter.com', 'Rua B, 200');

INSERT INTO Clinica
(id_clinica, nome, cnpj, telefone, email, endereco)
VALUES
(3, 'Animal Care', '33333333000133', '1133330003',
 'contato@animalcare.com', 'Rua C, 300');

INSERT INTO Clinica
(id_clinica, nome, cnpj, telefone, email, endereco)
VALUES
(4, 'Saude Pet', '44444444000144', '1133330004',
 'contato@saudepet.com', 'Rua D, 400');

INSERT INTO Clinica
(id_clinica, nome, cnpj, telefone, email, endereco)
VALUES
(5, 'Vet Mais', '55555555000155', '1133330005',
 'contato@vetmais.com', 'Rua E, 500');



-- RESPONSAVEL


INSERT INTO Responsavel
(id_responsavel, cpf, data_nascimento, id_usuario)
VALUES
(1, '11111111111', DATE '1995-03-10', 1);

INSERT INTO Responsavel
(id_responsavel, cpf, data_nascimento, id_usuario)
VALUES
(2, '22222222222', DATE '1990-07-21', 2);

INSERT INTO Responsavel
(id_responsavel, cpf, data_nascimento, id_usuario)
VALUES
(3, '33333333333', DATE '1998-11-05', 3);

INSERT INTO Responsavel
(id_responsavel, cpf, data_nascimento, id_usuario)
VALUES
(4, '44444444444', DATE '1993-02-15', 6);

INSERT INTO Responsavel
(id_responsavel, cpf, data_nascimento, id_usuario)
VALUES
(5, '55555555555', DATE '1988-09-30', 7);



-- VETERINARIO


INSERT INTO Veterinario
(id_veterinario, crv, especialidade, id_usuario, id_clinica)
VALUES
(1, 'CRV1001', 'Clinica Geral', 4, 1);

INSERT INTO Veterinario
(id_veterinario, crv, especialidade, id_usuario, id_clinica)
VALUES
(2, 'CRV1002', 'Dermatologia', 5, 2);

INSERT INTO Veterinario
(id_veterinario, crv, especialidade, id_usuario, id_clinica)
VALUES
(3, 'CRV1003', 'Cardiologia', 8, 3);

INSERT INTO Veterinario
(id_veterinario, crv, especialidade, id_usuario, id_clinica)
VALUES
(4, 'CRV1004', 'Ortopedia', 9, 4);

INSERT INTO Veterinario
(id_veterinario, crv, especialidade, id_usuario, id_clinica)
VALUES
(5, 'CRV1005', 'Clinica Geral', 10, 5);



-- PET


INSERT INTO Pet
(id_pet, nome, sexo, raca, especie, data_nascimento, id_responsavel)
VALUES
(1, 'Rex', 'MACHO', 'Labrador', 'CACHORRO',
 DATE '2021-03-15', 1);

INSERT INTO Pet
(id_pet, nome, sexo, raca, especie, data_nascimento, id_responsavel)
VALUES
(2, 'Luna', 'FEMEA', 'Siamês', 'GATO',
 DATE '2022-07-10', 2);

INSERT INTO Pet
(id_pet, nome, sexo, raca, especie, data_nascimento, id_responsavel)
VALUES
(3, 'Thor', 'MACHO', 'Golden Retriever', 'CACHORRO',
 DATE '2020-11-05', 3);

INSERT INTO Pet
(id_pet, nome, sexo, raca, especie, data_nascimento, id_responsavel)
VALUES
(4, 'Mel', 'FEMEA', 'SRD', 'CACHORRO',
 DATE '2023-01-20', 4);

INSERT INTO Pet
(id_pet, nome, sexo, raca, especie, data_nascimento, id_responsavel)
VALUES
(5, 'Nina', 'FEMEA', 'Persa', 'GATO',
 DATE '2019-06-12', 5);



-- VACINA


INSERT INTO Vacina
(id_vacina, nome, descricao)
VALUES
(1, 'Antirrabica', 'Vacina para prevencao da raiva');

INSERT INTO Vacina
(id_vacina, nome, descricao)
VALUES
(2, 'V8', 'Vacina multipla para caes');

INSERT INTO Vacina
(id_vacina, nome, descricao)
VALUES
(3, 'V10', 'Vacina multipla para caes');

INSERT INTO Vacina
(id_vacina, nome, descricao)
VALUES
(4, 'Triplice Felina', 'Vacina multipla para gatos');

INSERT INTO Vacina
(id_vacina, nome, descricao)
VALUES
(5, 'Giardia', 'Vacina auxiliar na prevencao da giardiase');



-- APLICACAO VACINA


INSERT INTO Aplicacao_vacina
(id_aplicacao_vacina, data_aplicacao, dose, observacao,
 id_pet, id_vacina, id_veterinario)
VALUES
(1, DATE '2026-04-10', '1 DOSE',
 'Aplicacao sem intercorrencias', 1, 1, 1);

INSERT INTO Aplicacao_vacina
(id_aplicacao_vacina, data_aplicacao, dose, observacao,
 id_pet, id_vacina, id_veterinario)
VALUES
(2, DATE '2026-05-12', 'REFORCO',
 'Reforco anual', 2, 4, 2);

INSERT INTO Aplicacao_vacina
(id_aplicacao_vacina, data_aplicacao, dose, observacao,
 id_pet, id_vacina, id_veterinario)
VALUES
(3, DATE '2026-06-15', '1 DOSE',
 'Normal', 3, 2, 3);

INSERT INTO Aplicacao_vacina
(id_aplicacao_vacina, data_aplicacao, dose, observacao,
 id_pet, id_vacina, id_veterinario)
VALUES
(4, DATE '2026-02-20', 'REFORCO',
 'Registro de vacina aplicada anteriormente', 4, 3, 3);

INSERT INTO Aplicacao_vacina
(id_aplicacao_vacina, data_aplicacao, dose, observacao,
 id_pet, id_vacina, id_veterinario)
VALUES
(5, DATE '2026-03-11', '1 DOSE',
 'Tutor nao informou o profissional', 5, 4, 4);



-- CONSULTA


INSERT INTO Consulta
(id_consulta, data_hora, motivo, observacao, status,
 id_pet, id_veterinario, id_clinica)
VALUES
(1, DATE '2026-08-10', 'Consulta de rotina',
 'Animal em boas condicoes', 'REALIZADA', 1, 1, 1);

INSERT INTO Consulta
(id_consulta, data_hora, motivo, observacao, status,
 id_pet, id_veterinario, id_clinica)
VALUES
(2, DATE '2026-08-12', 'Coceira persistente',
 'Possivel quadro alergico', 'REALIZADA', 2, 2, 2);

INSERT INTO Consulta
(id_consulta, data_hora, motivo, observacao, status,
 id_pet, id_veterinario, id_clinica)
VALUES
(3, DATE '2026-08-15', 'Avaliacao cardiaca',
 'Acompanhamento preventivo', 'REALIZADA', 3, 3, 3);

INSERT INTO Consulta
(id_consulta, data_hora, motivo, observacao, status,
 id_pet, id_veterinario, id_clinica)
VALUES
(4, DATE '2026-09-20', 'Avaliacao ortopedica',
 'Acompanhamento preventivo', 'AGENDADA', 4, 4, 4);

INSERT INTO Consulta
(id_consulta, data_hora, motivo, observacao, status,
 id_pet, id_veterinario, id_clinica)
VALUES
(5, DATE '2026-09-22', 'Consulta de rotina',
 'Avaliacao ortopedica', 'AGENDADA', 5, 5, 5);



-- LEMBRETE


INSERT INTO Lembrete
(id_lembrete, titulo, descricao, data_hora, status, id_pet)
VALUES
(1, 'Reforco de vacina',
 'Verificar data do reforco da vacina',
 DATE '2026-10-10', 'PENDENTE', 1);

INSERT INTO Lembrete
(id_lembrete, titulo, descricao, data_hora, status, id_pet)
VALUES
(2, 'Consulta dermatologica',
 'Retorno para avaliacao',
 DATE '2026-09-18', 'PENDENTE', 2);

INSERT INTO Lembrete
(id_lembrete, titulo, descricao, data_hora, status, id_pet)
VALUES
(3, 'Medicamento',
 'Administrar medicamento prescrito',
 DATE '2026-09-08', 'PENDENTE', 3);

INSERT INTO Lembrete
(id_lembrete, titulo, descricao, data_hora, status, id_pet)
VALUES
(4, 'Consulta ortopedica',
 'Comparecer a clinica',
 DATE '2026-09-20', 'PENDENTE', 4);

INSERT INTO Lembrete
(id_lembrete, titulo, descricao, data_hora, status, id_pet)
VALUES
(5, 'Consulta anual',
 'Realizar avaliacao preventiva',
 DATE '2026-09-22', 'PENDENTE', 5);



-- SENSOR


INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(1, 'TEMPERATURA', 'C', 'ATIVO', 1);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(2, 'ATIVIDADE', 'METROS', 'ATIVO', 1);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(3, 'TEMPERATURA', 'C', 'ATIVO', 2);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(4, 'ATIVIDADE', 'METROS', 'ATIVO', 2);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(5, 'TEMPERATURA', 'C', 'ATIVO', 3);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(6, 'ATIVIDADE', 'METROS', 'ATIVO', 3);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(7, 'TEMPERATURA', 'C', 'ATIVO', 4);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(8, 'ATIVIDADE', 'METROS', 'ATIVO', 4);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(9, 'TEMPERATURA', 'C', 'ATIVO', 5);

INSERT INTO Sensor
(id_sensor, tipo, unidade, status, id_pet)
VALUES
(10, 'ATIVIDADE', 'METROS', 'ATIVO', 5);



-- LEITURA


INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (1, DATE '2026-09-01', 38.50, 1);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (2, DATE '2026-09-02', 38.70, 1);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (3, DATE '2026-09-01', 72.00, 2);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (4, DATE '2026-09-02', 68.00, 2);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (5, DATE '2026-09-01', 38.90, 3);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (6, DATE '2026-09-02', 39.10, 3);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (7, DATE '2026-09-01', 65.00, 4);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (8, DATE '2026-09-02', 61.00, 4);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (9, DATE '2026-09-01', 38.40, 5);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (10, DATE '2026-09-02', 38.60, 5);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (11, DATE '2026-09-01', 80.00, 6);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (12, DATE '2026-09-02', 77.00, 6);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (13, DATE '2026-09-01', 38.80, 7);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (14, DATE '2026-09-02', 39.00, 7);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (15, DATE '2026-09-01', 55.00, 8);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (16, DATE '2026-09-02', 48.00, 8);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (17, DATE '2026-09-01', 38.60, 9);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (18, DATE '2026-09-02', 38.70, 9);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (19, DATE '2026-09-01', 60.00, 10);

INSERT INTO Leitura
(id_leitura, data_registro, valor, id_sensor)
VALUES (20, DATE '2026-09-02', 57.00, 10);