call mvnw.cmd clean compile
start cmd /c mvnw.cmd spring-boot:run -pl config-server
timeout 4
start cmd /c mvnw.cmd spring-boot:run -pl eureka-server
timeout 7
start cmd /c mvnw.cmd spring-boot:run -pl weather-service
