FROM openjdk:17

EXPOSE 8080

WORKDIR /usrapp/bin

COPY /certificates /usrapp/bin/certificates
COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency


CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.ASE.app.Main"]