# SmartLab

## 1. Setup Git
- Clone repo or use `git init` followed by `git remote add origin https://gitlab.diinf.usach.cl/natalia.guzman.s/Intelliware.git`
- Then, copy and execute following commands in a terminal:
```
git remote set-url --add --push origin https://gitlab.diinf.usach.cl/natalia.guzman.s/Intelliware.git
git remote set-url --add --push origin https://github.com/Rhadamys/intelliware.git
```

## 2. How to setup the project
- Install MySQL Server
- Create user `smartlab` with pass `secret`
    - `CREATE USER 'smartlab'@'localhost' IDENTIFIED BY 'secret';`
- Create database `smartlab`
    - `CREATE DATABASE IF NOT EXISTS smartlab;`
- Grant permissions
    - `GRANT ALL ON smartlab.* TO 'smartlab'@'localhost';`
- Have fun!

## 3. Gitflow
- Clone project
- Do `git hf init` in the project folder
- To start a new feature do `git hf feature start <feature_name>`
- To finish a feature do `git hf feature finish <feature_name>`

## 4. How to run
- Run server
    - `gradle bootRun`

## Testing feature