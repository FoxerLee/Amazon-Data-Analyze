# -*- coding:utf-8 -*-
import sys
import csv
# from Support import ID_LIST
import mysql.connector

reload(sys)  # reload 才能调用 setdefaultencoding 方法
sys.setdefaultencoding('utf-8')  # 设置 'utf-8'
ID_LIST = '1'

def run(amount):
    config = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'warehouse',
              'charset': 'utf8'}
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor()

    sql = 'SELECT * FROM id LIMIT ' + str(amount)
    cursor.execute(sql)
    count = 0
    ids = []
    for res in cursor:
        id = res[0]
        ids.append(id)


    for id in ids:
        sql = "DELETE FROM id WHERE product_id = \'" + id + "\';"
        try:
            cursor.execute(sql)
        except:
            print "DELETE error! " + id
            break

        count += 1
        sql = "INSERT INTO id_" + ID_LIST + " VALUE (\'" + id + "\');"
        try:
            cursor.execute(sql)
        except:
            print "INSERT error! " + id
            break
        print id
        print count
    cursor.execute("Commit;")
    cursor.close()
    conn.close()


if __name__ == '__main__':
    run(5000)
