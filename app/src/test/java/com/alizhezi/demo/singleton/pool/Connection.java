package com.alizhezi.demo.singleton.pool;

import java.util.HashMap;
import java.util.Map;

public class Connection {

    private static int MAX = 5;

    private static Map<Integer, Connection> map = new HashMap<>();

    private static int key = 1;

    public static Connection getConnection() {

        Connection conn = map.get(key);

        if (conn == null) {

            synchronized (Connection.class) {

                if (conn == null) {

                    conn = new Connection();

                    map.put(key, conn);
                }
            }
        }

        if (++key > MAX) {

            key = 1;
        }

        return conn;
    }
}
