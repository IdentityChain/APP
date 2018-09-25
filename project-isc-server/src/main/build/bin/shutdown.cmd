@echo off
@setlocal


@REM ==== START VALIDATION ====
set "AW_COMPONENT_HOME=%~dp0.."
if not "%AW_COMPONENT_HOME%"=="" goto stripAWHome
echo AW_COMPONENT_HOME is none: %AW_COMPONENT_HOME% >&2
goto end

:stripAWHome
if not "_%AW_COMPONENT_HOME:~-1%"=="_\" goto checkJHome
set "AW_COMPONENT_HOME=%AW_COMPONENT_HOME:~0,-1%"
goto stripAWHome


:checkJHome

set "JRE_LOCATION=%AW_COMPONENT_HOME%\..\jre"

if exist "%JRE_LOCATION%" set "JAVA_HOME=%JRE_LOCATION%"
if not "%JAVA_HOME%"=="" goto OkJHome
for %%i in (java.exe) do set "JAVACMD=%%~$PATH:i"
goto checkJCmd

:OkJHome
set "JAVACMD=%JAVA_HOME%\bin\java.exe"

:checkJCmd
if exist "%JAVACMD%" goto run

echo The JAVA_HOME environment variable is not defined correctly >&2
echo This environment variable is needed to run this program >&2
echo NB: JAVA_HOME should point to a JDK not a JRE >&2
goto end


@REM ==== END VALIDATION ====

:run

for %%i in ("%AW_COMPONENT_HOME%"\lib\*.jar) do set BOOT_JAR="%%i"

REM "%JAVACMD%" %JAVA_OPTS% -DAW_COMPONENT_HOME="%AW_COMPONENT_HOME%" -jar %BOOT_JAR% "shutdown" "--spring.config.location=%AW_COMPONENT_HOME%/conf/"

TIMEOUT 5 > NUL

if not exist "%AW_COMPONENT_HOME%\bin\application.pid" goto end
set /p pid=<"%AW_COMPONENT_HOME%\bin\application.pid"
TASKKILL /F /PID %pid%
echo Shutdown process [%pid%]
del "%AW_COMPONENT_HOME%\bin\application.pid"

:end
@endlocal



