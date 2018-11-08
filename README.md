# Android_Demo
demo练习

Microsoft Windows [版本 10.0.17134.345]
(c) 2018 Microsoft Corporation。保留所有权利。

F:\Android\Android_Demo>adb shell
generic_x86_64:/ $ su
generic_x86_64:/ # cd data/data
generic_x86_64:/data/data # cd com.alizhezi.demo
generic_x86_64:/data/data/com.alizhezi.demo # ls
cache  code_cache  gavin,db  gavin.db  gavin.db-journal
generic_x86_64:/data/data/com.alizhezi.demo # sqlite3 gavin.db
SQLite version 3.18.2 2017-07-21 07:56:09
Enter ".help" for usage hints.
sqlite> select * from t_user;
1|huihui|6116
1|huihui|6116
sqlite>


