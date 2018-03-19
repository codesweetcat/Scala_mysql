# User schema

# --- !Ups
create table `user1` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `firstName` TEXT NOT NULL,
  `lastName` TEXT NOT NULL
)


# --- !Downs
drop table `user1`