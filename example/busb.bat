@echo off
set directory=%~dp0
set parameter=%1
if %parameter%==--run %directory%run_busb.bat
if %parameter%==--help %directory%help.bat
if %parameter%==--version %directory%version.bat
