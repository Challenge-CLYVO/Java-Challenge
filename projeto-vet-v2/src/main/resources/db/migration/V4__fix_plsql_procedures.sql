
-- V4 - CORRECAO DAS PROCEDURES PL/SQL

-- PROCEDURE 1 - CONSULTAS DO PET EM JSON


CREATE OR REPLACE PROCEDURE pr_consulta_pet_json(
    p_id_pet IN NUMBER
)
IS

    v_quantidade NUMBER;
    v_consultas NUMBER;

    -- Variaveis usadas para guardar o erro do Oracle
    v_codigo_erro NUMBER;
    v_mensagem_erro VARCHAR2(500);

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

        -- Primeiro capturamos o erro PL/SQL
        v_codigo_erro := SQLCODE;
        v_mensagem_erro := SQLERRM;

        -- Depois usamos valores normais no INSERT
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
            v_codigo_erro,
            v_mensagem_erro
        );

        DBMS_OUTPUT.PUT_LINE(
            'Erro inesperado: ' ||
            v_mensagem_erro
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

    -- Variaveis para armazenar erro do Oracle
    v_codigo_erro NUMBER;
    v_mensagem_erro VARCHAR2(500);

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

        v_codigo_erro := SQLCODE;
        v_mensagem_erro := SQLERRM;

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
            v_codigo_erro,
            v_mensagem_erro
        );

        DBMS_OUTPUT.PUT_LINE(
            'Erro inesperado: ' ||
            v_mensagem_erro
        );

END;
/