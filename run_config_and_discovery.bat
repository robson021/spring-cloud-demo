call mvnw.cmd install -DskipTests -pl common
start cmd /c mvnw.cmd spring-boot:run -pl config-server
timeout 6
start cmd /c mvnw.cmd spring-boot:run -pl eureka-server
timeout 6
start cmd /c mvnw.cmd spring-boot:run -pl gateway