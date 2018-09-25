@echo off
@setlocal

title Coframe
@REM ==== START VALIDATION ====
set "AW_COMPONENT_HOME=%~dp0.."
if not "%AW_COMPONENT_HOME%"=="" goto stripAWHome
echo AW_COMPONENT_HOME is none: %AW_COMPONENT_HOME% >&2
goto end

:stripAWHome
if not "_%AW_COMPONENT_HOME:~-1%"=="_\" goto checkPid
set "AW_COMPONENT_HOME=%AW_COMPONENT_HOME:~0,-1%"
goto stripAWHome

:checkPid
if not exist "%AW_COMPONENT_HOME%\bin\application.pid" goto checkJHome
set /p pid=<"%AW_COMPONENT_HOME%\bin\application.pid"
TASKLIST /FI "PID eq %pid%" | FINDSTR "%pid%"> NUL
if not "%ERRORLEVEL%"=="0" goto checkJHome
echo Existed [%AW_COMPONENT_HOME%\bin\application.pid], and check process [%pid%] is running >&2
goto end


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
set JAVA_OPTS=%JAVA_OPTS% -Xms128m -Xmx2048m 
set "AW_LOG_DIR=%AW_COMPONENT_HOME%\logs"
set "FILE_PID=%AW_COMPONENT_HOME%\bin\application.pid"

for %%i in ("%AW_COMPONENT_HOME%"\lib\*.jar) do set BOOT_JAR="%%i"

"%JAVACMD%" %JAVA_OPTS% -DAW_COMPONENT_HOME="%AW_COMPONENT_HOME%" -Dspring.pid.file="%FILE_PID%" -DLOG_PATH="%AW_LOG_DIR%" -jar %BOOT_JAR% "--spring.config.location=%AW_COMPONENT_HOME%/conf/"
exit 0

:end
pause
@endlocal