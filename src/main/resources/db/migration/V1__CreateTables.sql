create table accounts
(
    id       uuid         not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    role     varchar(255) not null check ((role in ('USER', 'MODERATOR', 'ADMIN'))),
    status   varchar(255) not null check ( (status in ('ACTIVE', 'BLOCKED', 'DELETED')) ),
    primary key (id)
);

create table refresh_tokens
(

    id         uuid         not null,
    account_id uuid         not null,
    expires_at timestamp(6) not null,
    status     varchar(255) check ((status in ('ACTIVE', 'REFRESHED', 'EXPIRED', 'REVOKED'))),
    primary key (id)
);

alter table if exists refresh_tokens
    add constraint refresh_tokens_account_id_to_users_constraint
        foreign key (account_id)
            references accounts;
