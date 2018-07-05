:: build all modules
call mvnw.cmd clean compile
:: fail on maven error
if %errorlevel% neq 0 exit /b %errorlevel%
:: run all apps
start cmd /c mvnw.cmd spring-boot:run -pl config-server
timeout 4
start cmd /c mvnw.cmd spring-boot:run -pl eureka-server
timeout 7
start cmd /c mvnw.cmd spring-boot:run -pl weather-service
timeout 1
start cmd /c mvnw.cmd spring-boot:run -pl weather-service
timeout 1
start cmd /c mvnw.cmd spring-boot:run -pl currency-service
timeout 1
start cmd /c mvnw.cmd spring-boot:run -pl currency-service