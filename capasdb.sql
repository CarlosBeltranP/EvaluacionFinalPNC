PGDMP     8            
        x            capasdb    12.2    12.2 $    4           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            5           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            6           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            7           1262    25537    capasdb    DATABASE     �   CREATE DATABASE capasdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE capasdb;
                postgres    false            �            1259    25897    departamento    TABLE     t   CREATE TABLE public.departamento (
    departamento_id integer NOT NULL,
    departamento character varying(100)
);
     DROP TABLE public.departamento;
       public         heap    postgres    false            �            1259    25916    escuela    TABLE     y   CREATE TABLE public.escuela (
    escuela character varying(100),
    estado boolean,
    escuela_id integer NOT NULL
);
    DROP TABLE public.escuela;
       public         heap    postgres    false            �            1259    25928    escuela_escuela_id_seq1    SEQUENCE     �   ALTER TABLE public.escuela ALTER COLUMN escuela_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.escuela_escuela_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    208            �            1259    25935    materia    TABLE     y   CREATE TABLE public.materia (
    materia_id integer NOT NULL,
    materia character varying(100),
    estado boolean
);
    DROP TABLE public.materia;
       public         heap    postgres    false            �            1259    25938    materia_materia_id_seq    SEQUENCE     �   ALTER TABLE public.materia ALTER COLUMN materia_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.materia_materia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    210            �            1259    25753    role    TABLE     �   CREATE TABLE public.role (
    role_id bigint NOT NULL,
    description character varying(255),
    name character varying(255)
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    25751    role_role_id_seq    SEQUENCE     y   CREATE SEQUENCE public.role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.role_role_id_seq;
       public          postgres    false    203            8           0    0    role_role_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.role_role_id_seq OWNED BY public.role.role_id;
          public          postgres    false    202            �            1259    25762 
   user_roles    TABLE     ]   CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.user_roles;
       public         heap    postgres    false            �            1259    25769    users    TABLE     �  CREATE TABLE public.users (
    id bigint NOT NULL,
    first_name character varying(8),
    last_name character varying(255),
    password character varying(60),
    username character varying(255),
    municipio character varying(255),
    direccion character varying(255),
    estado boolean,
    nacimiento date,
    edad integer,
    d_departamento integer,
    escuela integer
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    25767    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    206            9           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    205            �
           2604    25756    role role_id    DEFAULT     l   ALTER TABLE ONLY public.role ALTER COLUMN role_id SET DEFAULT nextval('public.role_role_id_seq'::regclass);
 ;   ALTER TABLE public.role ALTER COLUMN role_id DROP DEFAULT;
       public          postgres    false    202    203    203            �
           2604    25772    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    205    206            -          0    25897    departamento 
   TABLE DATA           E   COPY public.departamento (departamento_id, departamento) FROM stdin;
    public          postgres    false    207   �'       .          0    25916    escuela 
   TABLE DATA           >   COPY public.escuela (escuela, estado, escuela_id) FROM stdin;
    public          postgres    false    208   I(       0          0    25935    materia 
   TABLE DATA           >   COPY public.materia (materia_id, materia, estado) FROM stdin;
    public          postgres    false    210   �(       )          0    25753    role 
   TABLE DATA           :   COPY public.role (role_id, description, name) FROM stdin;
    public          postgres    false    203   )       *          0    25762 
   user_roles 
   TABLE DATA           6   COPY public.user_roles (user_id, role_id) FROM stdin;
    public          postgres    false    204   A)       ,          0    25769    users 
   TABLE DATA           �   COPY public.users (id, first_name, last_name, password, username, municipio, direccion, estado, nacimiento, edad, d_departamento, escuela) FROM stdin;
    public          postgres    false    206   l)       :           0    0    escuela_escuela_id_seq1    SEQUENCE SET     F   SELECT pg_catalog.setval('public.escuela_escuela_id_seq1', 16, true);
          public          postgres    false    209            ;           0    0    materia_materia_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.materia_materia_id_seq', 2, true);
          public          postgres    false    211            <           0    0    role_role_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.role_role_id_seq', 3, true);
          public          postgres    false    202            =           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 30, true);
          public          postgres    false    205            �
           2606    25901    departamento departamento_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_pkey PRIMARY KEY (departamento_id);
 H   ALTER TABLE ONLY public.departamento DROP CONSTRAINT departamento_pkey;
       public            postgres    false    207            �
           2606    25934    escuela escuela_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.escuela
    ADD CONSTRAINT escuela_pkey PRIMARY KEY (escuela_id);
 >   ALTER TABLE ONLY public.escuela DROP CONSTRAINT escuela_pkey;
       public            postgres    false    208            �
           2606    25761    role role_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    203            �
           2606    25766    user_roles user_roles_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);
 D   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_roles_pkey;
       public            postgres    false    204    204            �
           2606    25777    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    206            �
           2606    25783 &   user_roles fkhfh9dx7w3ubf1co1vdev94g3f    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);
 P   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f;
       public          postgres    false    204    2722    206            �
           2606    25778 &   user_roles fkrhfovtciq1l558cw6udg0h0d3    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkrhfovtciq1l558cw6udg0h0d3 FOREIGN KEY (role_id) REFERENCES public.role(role_id);
 P   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT fkrhfovtciq1l558cw6udg0h0d3;
       public          postgres    false    2718    203    204            �
           2606    25902    users users_departamento_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_departamento_fkey FOREIGN KEY (d_departamento) REFERENCES public.departamento(departamento_id);
 G   ALTER TABLE ONLY public.users DROP CONSTRAINT users_departamento_fkey;
       public          postgres    false    206    2724    207            -   �   x�%�M�0��3���*�-	[HL��<��fjh��x���Tw��}yo�.������*HR�<�ى�=�L�)eC@;���h����H� ��[>Q\o�v�I16z���銙�6^���GX)*�9���@9�AV�ge-g�����jY�a�/�|<�      .   q   x�s�Sp�SJ��,�W�M,*9�6/�J! 3/5%����Ј���+���J�����Û��oL,ɛ@�R�J��LNTpKMI-J�QpN�+)�O�M-��M3����� I�'      0   )   x�3��M,I�=��$39���ˈ�'5/�41+ȉ���� ��
P      )   .   x�3���q�wt�����\�!g� O?G� N$6W� ���      *      x�3�4�2�� ��1W� $��      ,   w  x��Ͻr�@��z�
[�]~�PT"LP��IsB�]ـ��ݤL���Ƃa�:�)�y�9YP0~B&eeq��Ѐ�`$�%5E���j��~(�R$�&)7K7vp���Q��`�:���ɪP��^<$з�1��YJ���Q�����H��$�p;쵧��5=!������� �6�u�g.��z\�$v��E�Ū6�i囁H���!����k��k`gx���ca�DADG���s�f�&�/h~i�x4\#���=��qV��B������2��6U�8��[i��z���z�j�DP҈�*�'��#��+hůЮ-^���?�O�Hơ>�<|s+�R%����x��d�z0�vZ����ia]l@E����2��z����     