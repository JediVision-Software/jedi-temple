DROP KEYSPACE IF EXISTS starwars;
CREATE KEYSPACE IF NOT EXISTS starwars WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'}  AND durable_writes = true;
CREATE TABLE IF NOT EXISTS starwars.jedi (
    name text,
    id timeuuid,
    planet text,
    creation_date timestamp,
    PRIMARY KEY (name, id)
) WITH CLUSTERING ORDER BY (id DESC);