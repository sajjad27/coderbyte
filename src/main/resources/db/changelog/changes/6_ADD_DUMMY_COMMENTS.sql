--liquibase formatted sql

--changeset sajad:6 failOnError:true runInTransaction:true

--comment:  fill comments table with dummy data 

insert 
    into
        comment
        (postId, name, email, body) 
    values
        (1, 'Garuda Jain', 'jain_garuda@hessel.io', 'Et vel suscipit. Blanditiis sequi quos.');
		
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (1, 'Goswami Iyer', 'goswami_iyer@waters.net', 'Temporibus id debitis. Repellendus placeat ea. Sint hic id. Amet laboriosam sint.');
		
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (1, 'Deepesh Khanna MD', 'deepesh_md_khanna@wunsch.com', 'Adipisci nihil repellat. Voluptatum sit inventore.');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (2, 'Chandraswaroopa Bharadwaj', 'chandraswaroopa_bharadwaj@gerhold.name', 'Sapiente quisquam non. Doloribus tempore voluptatem. Quae laboriosam porro.');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (5, 'Chandini Tandon', 'chandini_tandon@flatley.net', 'Et sit id. Magni vitae consequatur.');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (2, 'Enakshi Varman', 'varman_enakshi@hagenes.io', 'heloo world!');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (3, 'Sarala Achari', 'sarala_achari@homenick-kassulke.name', 'Voluptates qui maiores.');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (4, 'Jagathi Shah', 'shah_jagathi@jenkins-towne.net', 'Fugit quia rerum.');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (5, 'Deeptimayee Mishra', 'mishra_deeptimayee@baumbach.biz', 'Quia sequi quod. Quisquam eos quia. Sit non voluptate.');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (6, 'Aadinath Jha', 'aadinath_jha@jacobson-medhurst.io', 'Ad debitis quis.');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (7, 'Sajad Ahmed', 'sajad@email.com', 'comment from sajad');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (8, 'Abbas Ibrahim', 'ibrahim@test.com', 'comment from abbas');
insert 
    into
        comment
        (postId, name, email, body) 
    values
        (9, 'Mohammed Jasem', 'Jasem-java@java.com', 'comment from jasem');
