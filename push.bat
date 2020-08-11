git add .
set /p msg=Enter Commit Message: 
git commit -m "%msg%"
git push
pause