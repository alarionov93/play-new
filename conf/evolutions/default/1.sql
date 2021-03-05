# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table task (
  id                            bigserial not null,
  title                         varchar(255),
  date_published                timestamptz,
  date_created                  timestamptz,
  number_of_pages               integer not null,
  label                         varchar(255),
  constraint pk_task primary key (id)
);


# --- !Downs

drop table if exists task cascade;

