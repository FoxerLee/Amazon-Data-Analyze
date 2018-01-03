# -*- coding: utf-8 -*-
import re
import os
from lxml import html
import csv
import datetime
import mysql.connector
from multiprocessing import Process

import sys
reload(sys)
sys.setdefaultencoding('utf-8')
config = {'host': 'localhost', 'user': 'root', 'password': '123456', 'port': 3306, 'database': 'warehouse',
              'charset': 'utf8'}


sql = "DELETE FROM connect_id WHERE id_1= %s AND id_2= %s;"
def run():
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    while(True):
        cursor.execute("SELECT * FROM connect_id LIMIT 1;")
        reasult = set()
        toFind = set()
        if cursor.rowcount != 0:
            for ids in cursor:

                reasult.add(str(ids[0]))
                reasult.add(str(ids[1]))

                toFind.add(str(ids[1]))
                toFind.add(str(ids[0]))

                cursor.execute(sql, (str(ids[0]), str(ids[1])))
                cursor.execute("Commit;")
                # cursor.close()
                # conn.close()


            while True:
                try:
                    id = toFind.pop()
                except Exception, e:
                    print "No id needed to find!"
                    break

                # conn = mysql.connector.connect(**config)
                # cursor = conn.cursor(buffered=True)
                cursor.execute("SELECT * FROM connect_id WHERE id_1 = \'"+id+"\';")

                for ids in cursor:
                    reasult.add(str(ids[0]))
                    reasult.add(str(ids[1]))

                    toFind.add(str(ids[1]))
                    toFind.add(str(ids[0]))

                    cursor.execute(sql, (str(ids[0]), str(ids[1])))
                    cursor.execute("Commit;")
                    # cursor.close()
                    # conn.close()

            reasult = list(reasult)
            csvFile = open("connect_local.csv", "ab")
            writer = csv.writer(csvFile)
            writer.writerow(reasult)
            csvFile.close()

            # cursor.close()
            # conn.close()
            print "Succeed! "
            print datetime.datetime.now()
        else:
            print "Finish! "
            print datetime.datetime.now()
            cursor.close()
            conn.close()
            break









if __name__ == "__main__":
    run()

