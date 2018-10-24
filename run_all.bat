call mvnw.cmd clean
call mvnw.cmd install -DskipTests -pl common
call mvnw.cmd package -DskipTests
if %errorlevel% neq 0 exit /b %errorlevel%
:: run all apps
start cmd /c mvnw.cmd spring-boot:run -pl config-server
timeout 8
start cmd /c mvnw.cmd spring-boot:run -pl eureka-server
timeout 8
start cmd /c mvnw.cmd spring-boot:run -pl weather-service
timeout 3
start cmd /c mvnw.cmd spring-boot:run -pl currency-service
timeout 3
start cmd /c mvnw.cmd spring-boot:run -pl country-service
timeout 3
::start cmd /c mvnw.cmd spring-boot:run -pl info-service
timeout 25
start cmd /c mvnw.cmd spring-boot:run -pl gateway