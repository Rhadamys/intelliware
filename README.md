# SmartLab

## 1. Setup Git
- Clone repo or use `git init` followed by `git remote add origin https://github.com/Rhadamys/intelliware.git`
- Then, copy and execute following commands in a terminal:
```
git remote set-url --add --push origin https://github.com/Rhadamys/intelliware.git
git remote set-url --add --push origin https://gitlab.diinf.usach.cl/natalia.guzman.s/Intelliware.git
```

## 2. How to setup the project
- Install MySQL Server
- Create user `smartlab` with pass `secret`
- Create database `smartlab`
- Have fun!

## 3. Gitflow
- Clone project
- Do `git hf init` in the project folder
- To start a new feature do `git hf feature start <feature_name>`
- To finish a feature do `git hf feature finish <feature_name>`