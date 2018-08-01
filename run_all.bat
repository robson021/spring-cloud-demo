call mvnw.cmd clean install -DskipTests -pl common
if %errorlevel% neq 0 exit /b %errorlevel%
:: run all apps
start cmd /c mvnw.cmd spring-boot:run -pl config-server
timeout 4
start cmd /c mvnw.cmd spring-boot:run -pl eureka-server
timeout 12
start cmd /c mvnw.cmd spring-boot:run -pl weather-service
start cmd /c mvnw.cmd spring-boot:run -pl currency-service
start cmd /c mvnw.cmd spring-boot:run -pl country-service
timeout 15
start cmd /c mvnw.cmd spring-boot:run -pl info-service
timeout 8
start cmd /c mvnw.cmd spring-boot:run -pl gateway