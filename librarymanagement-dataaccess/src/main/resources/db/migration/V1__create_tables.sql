create table public.roles(
id text not null,
name text not null,
CONSTRAINT roles_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.roles
    OWNER to postgres;

create table public.users(
id text not null,
user_name text not null,
password text not null,
email text not null,
mobile_num numeric not null,
CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;

create table public.user_roles(
user_id text not null,
role_id text not null,
CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
CONSTRAINT role_foreign_key FOREIGN KEY (role_id)
 REFERENCES public.roles(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
 CONSTRAINT user_foreign_key FOREIGN KEY (user_id)
        REFERENCES public.users(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION       
)

TABLESPACE pg_default;

ALTER TABLE public.user_roles
    OWNER to postgres;

create table public.student(
user_id text not null,
reg_no text,
name text,
department text,
CONSTRAINT student_pkey PRIMARY KEY (user_id),
CONSTRAINT student_fkey FOREIGN KEY(user_id)
REFERENCES public.users (id) MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION

)

TABLESPACE pg_default;

ALTER TABLE public.student
    OWNER to postgres;
    
create table public.books(
id text not null,
title text,
author text,
book_code text,
no_of_books_aval numeric,
CONSTRAINT books_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.books
    OWNER to postgres;

create table public.issued_books(
id text not null,
title text,
author text,
book_code text,
stud_id text,
issued timestamp without time zone,
CONSTRAINT issued_books_pkey PRIMARY KEY (id),
CONSTRAINT issued_books_fkey FOREIGN KEY (stud_id)
	REFERENCES public.student (user_id) MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.issued_books
    OWNER to postgres;

