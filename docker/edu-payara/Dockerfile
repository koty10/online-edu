FROM payara/server-full:5.2020.5-jdk11

USER payara
ENV PAYARA_ARGS --debug
COPY lib/*.jar /tmp
RUN echo 'add-library /tmp/postgresql-42.2.8.jar' > $POSTBOOT_COMMANDS
RUN echo 'create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --driverclassname org.postgresql.Driver --restype javax.sql.DataSource --property user=edu:password=edu:URL="jdbc\\:postgresql\\://edu-db/onlineedu" eduPool' >> $POSTBOOT_COMMANDS
RUN echo 'ping-connection-pool eduPool' >> $POSTBOOT_COMMANDS
RUN echo 'create-jdbc-resource --connectionpoolid eduPool jdbc/onlineedu' >> $POSTBOOT_COMMANDS
RUN echo 'create-auth-realm --classname=com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm --property jaas-context=jdbcRealm:datasource-jndi=jdbc/onlineedu:user-table=user_account:user-name-column=username:password-column=password:group-table=user_account:group-name-column=role:digestrealm-password-enc-algorithm=SHA-256:encoding=Base64 onlineEduRealm' >> $POSTBOOT_COMMANDS
COPY deployment/*.war $DEPLOY_DIR
