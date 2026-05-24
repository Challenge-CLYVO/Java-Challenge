INSERT INTO tutor (nome, email,telefone) VALUES ('Carlos Silva', 'carlos@gmail.com','11111111111');
INSERT INTO tutor (nome, email,telefone) VALUES ('Mariana Souza', 'mariana@gmail.com','22222222222');
INSERT INTO tutor (nome, email, telefone) VALUES ('Fernanda Lima', 'fernanda@gmail.com','33333333333');
INSERT INTO tutor (nome, email, telefone) VALUES ('Joao Pedro', 'joao@gmail.com','44444444444');
INSERT INTO tutor (nome, email, telefone) VALUES ('Lucas Oliveira', 'lucas@gmail.com','55555555555');

INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('Thor', 4, 'Labrador', 'Cachorro', 1);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('Bia', 1, 'Siames', 'Gato', 2);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('Rex', 5, 'Pastor Alemao', 'Cachorro', 1);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('Hulk', 5, 'Persa', 'Gato', 3);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('Cap', 5, 'Bulldog', 'Cachorro', 4);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('Mel', 5, 'Pinscher', 'Cachorro', 5);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('Toto', 5, 'Poodle', 'Cachorro', 2);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('max', 5, 'Maine Coon', 'Gato', 3);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('filipo', 5, 'Sphynx', 'Gato', 4);
INSERT INTO pet (nome, idade, raca, especie, id_tutor) VALUES ('napoleao', 5, 'Golden Retriver', 'Cachorro', 5);


INSERT INTO clinica (nome, endereco, telefone) VALUES ('Vet Vida Animal', 'Rua das Flores, 120', '11987654321');
INSERT INTO clinica (nome, endereco, telefone) VALUES ('Pet Care Center', 'Av. Paulista, 1500', '11999998888');
INSERT INTO clinica (nome, endereco, telefone) VALUES ('Clinica Patinhas', 'Rua Azul, 45', '11955554444');
INSERT INTO clinica (nome, endereco, telefone) VALUES ('Mundo Animal Vet', 'Av. Brasil, 700', '11933332222');
INSERT INTO clinica (nome, endereco, telefone) VALUES ('Amigos dos Pets', 'Rua Central, 89', '11911112222');


INSERT INTO vacina (nome, descricao) VALUES ('V10', 'Vacina multipla para caes');
INSERT INTO vacina (nome, descricao) VALUES ('Antirrabica', 'Prevencao contra raiva');
INSERT INTO vacina (nome, descricao) VALUES ('V4', 'Vacina multipla para gatos');

INSERT INTO consulta (data_consulta, descricao, id_pet, id_clinica) VALUES ('2026-05-10', 'Pet apresentou febre', 1, 1);
INSERT INTO consulta (data_consulta, descricao, id_pet, id_clinica) VALUES ('2026-05-12', 'Check-up geral', 2, 1);
INSERT INTO consulta (data_consulta, descricao, id_pet, id_clinica) VALUES ('2026-05-15', 'Coceira na pele', 3, 2);