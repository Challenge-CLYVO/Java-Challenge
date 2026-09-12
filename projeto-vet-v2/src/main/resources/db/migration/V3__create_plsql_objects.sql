
-- V3 - OBJETOS PL/SQL




-- FUNCTION 1 - CONSULTA EM JSON


CREATE OR REPLACE FUNCTION fn_consulta_json(
    p_id_consulta IN NUMBER
) RETURN VARCHAR2
IS
    v_json VARCHAR2(4000);

    v_pet Pet.nome%TYPE;
    v_veterinario Usuario.nome%TYPE;
    v_clinica Clinica.nome%TYPE;
    v_data Consulta.data_hora%TYPE;
    v_motivo Consulta.motivo%TYPE;
    v_status Consulta.status%TYPE;

    e_id_invalido EXCEPTION;

BEGIN

    IF p_id_consulta IS NULL OR p_id_consulta <= 0 THEN
        RAISE e_id_invalido;
    END IF;


    SELECT
        p.nome,
        u.nome,
        c.nome,
        co.data_hora,
        co.motivo,
        co.status

    INTO
        v_pet,
        v_veterinario,
        v_clinica,
        v_data,
        v_motivo,
        v_status

    FROM Consulta co

    JOIN Pet p
        ON co.id_pet = p.id_pet

    JOIN Veterinario v
        ON co.id_veterinario = v.id_veterinario

    JOIN Usuario u
        ON v.id_usuario = u.id_usuario

    JOIN Clinica c
        ON co.id_clinica = c.id_clinica

    WHERE co.id_consulta = p_id_consulta;


    v_json :=
        '{' ||
        '"id_consulta":' || p_id_consulta || ',' ||
        '"pet":"' || v_pet || '",' ||
        '"veterinario":"' || v_veterinario || '",' ||
        '"clinica":"' || v_clinica || '",' ||
        '"data":"' || TO_CHAR(v_data, 'YYYY-MM-DD') || '",' ||
        '"motivo":"' || v_motivo || '",' ||
        '"status":"' || v_status || '"' ||
        '}';


    RETURN v_json;


EXCEPTION

    WHEN NO_DATA_FOUND THEN

        DBMS_OUTPUT.PUT_LINE('Consulta nao encontrada.');

        RETURN '{"erro":"Consulta nao encontrada"}';


    WHEN e_id_invalido THEN

        DBMS_OUTPUT.PUT_LINE('ID da consulta invalido.');

        RETURN '{"erro":"ID da consulta invalido"}';


    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);

        RETURN '{"erro":"' ||
               REPLACE(SQLERRM, '"', '''') ||
               '"}';

END;
/



-- FUNCTION 2 - VALIDACAO DE CPF


CREATE OR REPLACE FUNCTION fn_validar_cpf(
    p_cpf IN VARCHAR2
) RETURN VARCHAR2
IS

    v_soma NUMBER := 0;
    v_resto NUMBER;
    v_digito NUMBER;
    v_digito1 NUMBER;
    v_digito2 NUMBER;

    i NUMBER;

    e_cpf_nao_informado EXCEPTION;
    e_tamanho_invalido EXCEPTION;

BEGIN

    IF p_cpf IS NULL THEN
        RAISE e_cpf_nao_informado;
    END IF;


    IF LENGTH(p_cpf) != 11 THEN
        RAISE e_tamanho_invalido;
    END IF;


    -- PRIMEIRO DIGITO

    v_soma := 0;
    i := 1;


    WHILE i <= 9 LOOP

        v_digito := TO_NUMBER(
            SUBSTR(p_cpf, i, 1)
        );

        v_soma :=
            v_soma +
            v_digito * (11 - i);

        i := i + 1;

    END LOOP;


    v_resto := MOD(v_soma, 11);


    IF v_resto < 2 THEN

        v_digito1 := 0;

    ELSE

        v_digito1 := 11 - v_resto;

    END IF;


    -- SEGUNDO DIGITO

    v_soma := 0;
    i := 1;


    WHILE i <= 10 LOOP

        v_digito :=
            TO_NUMBER(
                SUBSTR(p_cpf, i, 1)
            );

        v_soma :=
            v_soma +
            v_digito * (12 - i);

        i := i + 1;

    END LOOP;


    v_resto := MOD(v_soma, 11);


    IF v_resto < 2 THEN

        v_digito2 := 0;

    ELSE

        v_digito2 := 11 - v_resto;

    END IF;


    IF
        v_digito1 =
        TO_NUMBER(SUBSTR(p_cpf, 10, 1))

        AND

        v_digito2 =
        TO_NUMBER(SUBSTR(p_cpf, 11, 1))

    THEN

        DBMS_OUTPUT.PUT_LINE('CPF valido.');

        RETURN 'CPF VALIDO';

    ELSE

        DBMS_OUTPUT.PUT_LINE('CPF invalido.');

        RETURN 'CPF INVALIDO';

    END IF;


EXCEPTION

    WHEN e_cpf_nao_informado THEN

        DBMS_OUTPUT.PUT_LINE(
            'CPF nao informado.'
        );

        RETURN 'CPF INVALIDO';


    WHEN e_tamanho_invalido THEN

        DBMS_OUTPUT.PUT_LINE(
            'CPF deve possuir 11 numeros.'
        );

        RETURN 'CPF INVALIDO';


    WHEN VALUE_ERROR THEN

        DBMS_OUTPUT.PUT_LINE(
            'CPF invalido. Informe apenas numeros.'
        );

        RETURN 'CPF INVALIDO';


    WHEN INVALID_NUMBER THEN

        DBMS_OUTPUT.PUT_LINE(
            'Erro na conversao numerica do CPF.'
        );

        RETURN 'CPF INVALIDO';


    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE(
            'Erro inesperado: ' || SQLERRM
        );

        RETURN 'ERRO';

END;
/



-- PROCEDURE 1 - CONSULTAS DO PET EM JSON


CREATE OR REPLACE PROCEDURE pr_consulta_pet_json(
    p_id_pet IN NUMBER
)
IS

    v_quantidade NUMBER;
    v_consultas NUMBER;

    e_id_invalido EXCEPTION;
    e_pet_nao_encontrado EXCEPTION;
    e_sem_consultas EXCEPTION;

BEGIN

    IF p_id_pet IS NULL OR p_id_pet <= 0 THEN
        RAISE e_id_invalido;
    END IF;


    SELECT COUNT(*)
    INTO v_quantidade
    FROM Pet
    WHERE id_pet = p_id_pet;


    IF v_quantidade = 0 THEN
        RAISE e_pet_nao_encontrado;
    END IF;


    SELECT COUNT(*)
    INTO v_consultas
    FROM Consulta
    WHERE id_pet = p_id_pet;


    IF v_consultas = 0 THEN
        RAISE e_sem_consultas;
    END IF;


    FOR r IN (

        SELECT
            p.nome,
            c.id_consulta

        FROM Pet p

        JOIN Consulta c
            ON p.id_pet = c.id_pet

        WHERE p.id_pet = p_id_pet

        ORDER BY c.data_hora

    )
    LOOP

        DBMS_OUTPUT.PUT_LINE(
            fn_consulta_json(
                r.id_consulta
            )
        );

    END LOOP;


EXCEPTION

    WHEN e_id_invalido THEN

        INSERT INTO Log_erro (
            nome_procedure,
            usuario,
            codigo_erro,
            mensagem_erro
        )
        VALUES (
            'PR_CONSULTA_PET_JSON',
            USER,
            -20001,
            'ID do pet invalido'
        );

        DBMS_OUTPUT.PUT_LINE(
            'ID do pet invalido.'
        );


    WHEN e_pet_nao_encontrado THEN

        INSERT INTO Log_erro (
            nome_procedure,
            usuario,
            codigo_erro,
            mensagem_erro
        )
        VALUES (
            'PR_CONSULTA_PET_JSON',
            USER,
            -20002,
            'Pet nao encontrado.'
        );

        DBMS_OUTPUT.PUT_LINE(
            'Pet nao encontrado.'
        );


    WHEN e_sem_consultas THEN

        INSERT INTO Log_erro (
            nome_procedure,
            usuario,
            codigo_erro,
            mensagem_erro
        )
        VALUES (
            'PR_CONSULTA_PET_JSON',
            USER,
            -20003,
            'O pet nao possui consultas cadastradas.'
        );

        DBMS_OUTPUT.PUT_LINE(
            'O pet nao possui consultas cadastradas.'
        );


    WHEN OTHERS THEN

        INSERT INTO Log_erro (
            nome_procedure,
            usuario,
            data_erro,
            codigo_erro,
            mensagem_erro
        )
        VALUES (
            'PR_CONSULTA_PET_JSON',
            USER,
            SYSDATE,
            SQLCODE,
            SQLERRM
        );

        DBMS_OUTPUT.PUT_LINE(
            'Erro inesperado: ' ||
            SQLERRM
        );

END;
/


-- PROCEDURE 2 - RELATORIO DE LEITURAS


CREATE OR REPLACE PROCEDURE pr_relatorio_leituras
IS

    v_especie_anterior Pet.especie%TYPE;
    v_pet_anterior Pet.nome%TYPE;

    v_soma_pet NUMBER := 0;
    v_subtotal_especie NUMBER := 0;
    v_total_geral NUMBER := 0;

    v_primeira_linha NUMBER := 1;

    v_quantidade NUMBER;
    v_valores_invalidos NUMBER;

    e_sem_leituras EXCEPTION;
    e_dados_insuficientes EXCEPTION;
    e_valor_negativo EXCEPTION;

BEGIN

    SELECT COUNT(*)
    INTO v_quantidade

    FROM Leitura l

    JOIN Sensor s
        ON l.id_sensor = s.id_sensor

    WHERE s.tipo = 'ATIVIDADE';


    IF v_quantidade = 0 THEN
        RAISE e_sem_leituras;
    END IF;


    IF v_quantidade < 5 THEN
        RAISE e_dados_insuficientes;
    END IF;


    SELECT COUNT(*)
    INTO v_valores_invalidos

    FROM Leitura l

    JOIN Sensor s
        ON l.id_sensor = s.id_sensor

    WHERE s.tipo = 'ATIVIDADE'
      AND l.valor < 0;


    IF v_valores_invalidos > 0 THEN
        RAISE e_valor_negativo;
    END IF;


    FOR r IN (

        SELECT
            p.especie,
            p.nome,
            l.valor

        FROM Leitura l

        JOIN Sensor s
            ON l.id_sensor = s.id_sensor

        JOIN Pet p
            ON s.id_pet = p.id_pet

        WHERE s.tipo = 'ATIVIDADE'

        ORDER BY
            p.especie,
            p.nome

    )
    LOOP

        IF v_primeira_linha = 1 THEN

            v_especie_anterior := r.especie;
            v_pet_anterior := r.nome;
            v_primeira_linha := 0;

        END IF;


        IF
            r.nome != v_pet_anterior
            OR
            r.especie != v_especie_anterior

        THEN

            DBMS_OUTPUT.PUT_LINE(
                v_especie_anterior ||
                ' | ' ||
                v_pet_anterior ||
                ' | ' ||
                v_soma_pet
            );

            v_soma_pet := 0;

        END IF;


        IF r.especie != v_especie_anterior THEN

            DBMS_OUTPUT.PUT_LINE(
                v_especie_anterior ||
                ' | NULL | ' ||
                v_subtotal_especie
            );

            DBMS_OUTPUT.PUT_LINE(
                '-----------------------'
            );

            v_subtotal_especie := 0;

        END IF;


        v_especie_anterior := r.especie;
        v_pet_anterior := r.nome;

        v_soma_pet :=
            v_soma_pet + r.valor;

        v_subtotal_especie :=
            v_subtotal_especie + r.valor;

        v_total_geral :=
            v_total_geral + r.valor;

    END LOOP;


    IF v_primeira_linha = 0 THEN

        DBMS_OUTPUT.PUT_LINE(
            v_especie_anterior ||
            ' | ' ||
            v_pet_anterior ||
            ' | ' ||
            v_soma_pet
        );

        DBMS_OUTPUT.PUT_LINE(
            v_especie_anterior ||
            ' | NULL | ' ||
            v_subtotal_especie
        );

        DBMS_OUTPUT.PUT_LINE(
            '-----------------------'
        );

        DBMS_OUTPUT.PUT_LINE(
            'NULL | NULL | ' ||
            v_total_geral
        );

    ELSE

        DBMS_OUTPUT.PUT_LINE(
            'Nenhuma leitura encontrada.'
        );

    END IF;


EXCEPTION

    WHEN e_sem_leituras THEN

        DBMS_OUTPUT.PUT_LINE(
            'Nenhuma leitura de atividade encontrada.'
        );


    WHEN e_dados_insuficientes THEN

        DBMS_OUTPUT.PUT_LINE(
            'Quantidade insuficiente de leituras. Minimo: 5.'
        );


    WHEN e_valor_negativo THEN

        DBMS_OUTPUT.PUT_LINE(
            'Existem leituras de atividade com valor negativo.'
        );


    WHEN OTHERS THEN

        INSERT INTO Log_erro (
            nome_procedure,
            usuario,
            data_erro,
            codigo_erro,
            mensagem_erro
        )
        VALUES (
            'PR_RELATORIO_LEITURAS',
            USER,
            SYSDATE,
            SQLCODE,
            SQLERRM
        );

        DBMS_OUTPUT.PUT_LINE(
            'Erro inesperado: ' ||
            SQLERRM
        );

END;
/



-- TRIGGER - AUDITORIA DE CONSULTA


CREATE OR REPLACE TRIGGER trg_auditoria_consulta

AFTER INSERT OR UPDATE OR DELETE
ON Consulta

FOR EACH ROW

BEGIN

    IF INSERTING THEN

        INSERT INTO Auditoria (
            usuario,
            operacao,
            data_operacao,
            valor_antigo,
            valor_novo,
            tabela_afetada
        )
        VALUES (
            USER,
            'INSERT',
            SYSDATE,
            NULL,
            'ID_CONSULTA=' || :NEW.id_consulta ||
            ', STATUS=' || :NEW.status ||
            ', ID_PET=' || :NEW.id_pet,
            'CONSULTA'
        );


    ELSIF UPDATING THEN

        INSERT INTO Auditoria (
            usuario,
            operacao,
            data_operacao,
            valor_antigo,
            valor_novo,
            tabela_afetada
        )
        VALUES (
            USER,
            'UPDATE',
            SYSDATE,

            'ID_CONSULTA=' || :OLD.id_consulta ||
            ', STATUS=' || :OLD.status ||
            ', ID_PET=' || :OLD.id_pet,

            'ID_CONSULTA=' || :NEW.id_consulta ||
            ', STATUS=' || :NEW.status ||
            ', ID_PET=' || :NEW.id_pet,

            'CONSULTA'
        );


    ELSIF DELETING THEN

        INSERT INTO Auditoria (
            usuario,
            operacao,
            data_operacao,
            valor_antigo,
            valor_novo,
            tabela_afetada
        )
        VALUES (
            USER,
            'DELETE',
            SYSDATE,

            'ID_CONSULTA=' || :OLD.id_consulta ||
            ', STATUS=' || :OLD.status ||
            ', ID_PET=' || :OLD.id_pet,

            NULL,
            'CONSULTA'
        );

    END IF;

END;
/