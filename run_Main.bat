echo %JAVA_HOME%
echo off
call delete_logs
call setenv_1.8.0_73
set CLASSPATH=%CLASSPATH%;.\deploy\jbones_core.jar;.\deploy\jbones_core-config.jar;
echo using classpath ...
echo %CLASSPATH%

"%JAVA_HOME%\bin\java" -classpath %CLASSPATH% org.jbones.core.Main

pause
