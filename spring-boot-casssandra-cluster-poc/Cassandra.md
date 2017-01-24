## Setup multiple node of cassandra on single system guide

* Make two copies of conf folder in cassandra directory (e.g. conf2, conf3)

* Inside each conf folder change cassandra.yml file (# - number of conf folder):
    1) cluster_name: 'MyCluster' (same for each yml file)
    2) data_file_directories: - /var/lib/cassandra/cassandra#/data 
    3) commitlog_directory: /var/lib/cassandra/cassandra#/commitlog
    4) saved_caches_directory: /var/lib/cassandra/cassandra#/saved_caches
    5) listen_address: 127.0.0.#
    6) rpc_address: 127.0.0.#
    
* Inside each conf folder change cassandra-env.sh file:
    1) JMX_PORT="7199" (conf folder)
    2) JMX_PORT="7188" (conf2 folder)
    3) JMX_PORT="7177" (conf3 folder)
    
* Inside bin folder make two copies of cassandra.in.sh file and change them:
    1) CASSANDRA_CONF="$CASSANDRA_HOME/conf" (cassandra.in.sh)
    2) CASSANDRA_CONF="$CASSANDRA_HOME/conf2" (cassandra2.in.sh)
    3) CASSANDRA_CONF="$CASSANDRA_HOME/conf3" (cassandra3.in.sh)
    
* Inside bin folder make two copies of cassandra file and change them (70-74 lines):
    70) for include in "`dirname "$0"`/cassandra#.in.sh"
    71) "$HOME/.cassandra#.in.sh"
    72) /usr/share/cassandra/cassandra#.in.sh
    73) /usr/local/share/cassandra/cassandra#.in.sh
    74) /opt/cassandra/cassandra#.in.sh; do 

* Create listening address with command:
    - sudo ifconfig lo0 alias 172.0.0.2
    - sudo ifconfig lo0 alias 172.0.0.3
    
