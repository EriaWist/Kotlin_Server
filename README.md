# Kotlin_Server
使用Docker
```
docker build --tag server .
docker run -d -p 80:80 server  
```

---
下指令執行
```
./gradlew installDist
```
檔案放在`/app/build/install/`

---
下指令[打包](https://docs.gradle.org/current/userguide/application_plugin.html)
```
./gradlew distZip 打包成 ZIP
./gradlew distTar 打包成 TAR
```
檔案放在`/app/build/distributions`
