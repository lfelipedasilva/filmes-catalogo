// types do postgresql #1
CREATE TYPE public.generos AS ENUM
    ('TERROR', 'MISTERIO', 'SUSPENSE', 'FICCAO', 'SCI_FI', 'DRAMA', 'ROMANCE', 'AVENTURA', 'ACAO', 'COMEDIA');

CREATE TYPE public.classind AS ENUM
    ('LIVRE', '10 ANOS', '12 ANOS', '16 ANOS', '18 ANOS');

//tabela diretores #2
CREATE TABLE IF NOT EXISTS public.diretores
(
    id uuid NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    data_nascimento date NOT NULL,
    data_registro timestamp without time zone,
    data_atualizacao timestamp without time zone,
    CONSTRAINT diretores_pkey PRIMARY KEY (id)
);

// tabela atores #3
CREATE TABLE IF NOT EXISTS public.atores
(
    id uuid NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    idade integer NOT NULL,
    data_registro date,
    data_atualizacao date,
    CONSTRAINT atores_pkey PRIMARY KEY (id)
);

// tabela filmes #4
CREATE TABLE IF NOT EXISTS public.filmes
(
    id uuid NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    diretor_id uuid NOT NULL,
    sinopse character varying(150) COLLATE pg_catalog."default" NOT NULL,
    data_estreia date,
    minutagem integer NOT NULL,
    classificacao_indicativa classind NOT NULL,
    genero generos NOT NULL,
    nota_publico numeric(4,2),
    data_registro timestamp without time zone,
    data_atualizacao timestamp without time zone,
    CONSTRAINT filmes_pkey PRIMARY KEY (id),
    CONSTRAINT filmes_diretor_id_fkey FOREIGN KEY (diretor_id)
    REFERENCES public.diretores (id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT chk_nota_publico CHECK (nota_publico >= 0::numeric AND nota_publico <= 10::numeric),
    CONSTRAINT filmes_minutagem_check CHECK (minutagem > 0)
);

// tabela reviews #5
CREATE TABLE IF NOT EXISTS public.reviews
(
    id uuid NOT NULL,
    filme_id uuid NOT NULL,
    nota numeric(4,2) NOT NULL,
    comentario character varying(100) COLLATE pg_catalog."default" DEFAULT 'SEM COMENTARIOS'::character varying,
    CONSTRAINT reviews_pkey PRIMARY KEY (id),
    CONSTRAINT reviews_filme_id_fkey FOREIGN KEY (filme_id)
    REFERENCES public.filmes (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT chk_nota_review CHECK (nota >= 0::numeric AND nota <= 10::numeric)
    );

// tabela intermediaria entre ator e filme #6
CREATE TABLE IF NOT EXISTS public.ator_filme
(
    filme_id uuid NOT NULL,
    ator_id uuid NOT NULL,
    CONSTRAINT ator_filme_pkey PRIMARY KEY (filme_id, ator_id),
    CONSTRAINT ator_filme_ator_id_fkey FOREIGN KEY (ator_id)
    REFERENCES public.atores (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT ator_filme_filme_id_fkey FOREIGN KEY (filme_id)
    REFERENCES public.filmes (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

