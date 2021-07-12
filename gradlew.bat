	@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off	@if "%DEBUG%" == "" @echo off
@rem ##########################################################################	@rem ##########################################################################
@rem	@rem
@@ -13,15 +29,18 @@ if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0	set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%	set APP_HOME=%DIRNAME%


@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.	@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=	set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"


@rem Find java.exe	@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome	if defined JAVA_HOME goto findJavaFromJavaHome


set JAVA_EXE=java.exe	set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1	%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init	if "%ERRORLEVEL%" == "0" goto execute


echo.	echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.	echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
@@ -35,7 +54,7 @@ goto fail
set JAVA_HOME=%JAVA_HOME:"=%	set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe	set JAVA_EXE=%JAVA_HOME%/bin/java.exe


if exist "%JAVA_EXE%" goto init	if exist "%JAVA_EXE%" goto execute


echo.	echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%	echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
@@ -45,28 +64,14 @@ echo location of your Java installation.


goto fail	goto fail


:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute	:execute
@rem Setup the command line	@rem Setup the command line


set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar	set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar



@rem Execute Gradle	@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%	"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*


:end	:end
@rem End local scope for the variables with windows NT shell	@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd	if "%ERRORLEVEL%"=="0" goto mainEnd
:fail	:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of	rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!	rem the _cmd.exe /c_ return code!
if  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1	if  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1	exit /b 1
:mainEnd	:mainEnd
if "%OS%"=="Windows_NT" endlocal	if "%OS%"=="Windows_NT" endlocal
:omega	:omega
