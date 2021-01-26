gradle build
mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)
docker build -t vasiliyanushkevich/gs-spring-boot-docker .
docker push vasiliyanushkevich/gs-spring-boot-docker
docker run -p 8085:8080 -t vasiliyanushkevich/gs-spring-boot-docker -e "SPRING_PROFILES_ACTIVE=prod"


#######################################################################################################################
Debug inside the docker
docker run -e "JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8085:8080 -p 5005:5005 -t vasiliyanushkevich/gs-spring-boot-docker

