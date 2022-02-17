SET JAVA_HOME="C:\Program Files\Java\jdk-17.0.2\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
cd D:\2022\Primer_Semestre\Compi1\LAB\Proyecto1\proyecto1_Main\src\analizador
java -jar D:\2022\Primer_Semestre\Compi1\LAB\Proyecto1\proyecto1_Main\lib\java-cup-11b.jar -parser sintactico -symbols sym sintactico.cup
pause

