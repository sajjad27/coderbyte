--liquibase formatted sql

--changeset sajad:2 failOnError:true runInTransaction:true

--comment:  add dummy users

insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('dinesh', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Dinesh', 'dinesh_pilla@altenwerth.co', 'MALE', 1);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('atreyee', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Atreyee', 'khatri_atreyee@jacobson.co', 'FEMALE', 0);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('chandraprabha', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Chandraprabha', 'chandraprabha_shah@schultz-barton.com', 'FEMALE', 1);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('atreyi', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Atreyi', 'somayaji_atreyi@mayert.info', 'MALE', 1);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('baidehi', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Baidehi', 'ret_bharadwaj_baidehi@reilly-watsica.net', 'FEMALE', 0);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('girindra', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Girindra', 'lld_girindra_chaturvedi@feeney.net', 'MALE', 0);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('girika', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Girika', 'jr_girika_kapoor@batz.info', 'MALE', 1);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('meghnath', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Meghnath', 'dwivedi_meghnath@sanford-bernier.net', 'FEMALE', 1);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('gaurang', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Gaurang', 'gaurang_iyengar@toy.biz', 'FEMALE', 0);
insert 
    into
        user
        (username, password, name, email, gender, is_activated ) 
    values
        ('jitender', '$2a$10$22ibiF/lDNHLV36TsFRQse9PtljMfnsfFavReFxyqB5pUVR.NjaK6', 'Jitender', 'mrs_jitender_varma@cormier.name', 'MALE', 0);
