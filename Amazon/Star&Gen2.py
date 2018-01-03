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
config = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'warehouse',
              'charset': 'utf8'}
sql1 = "SELECT actors, genre FROM product WHERE id=%s;"
config1 = {'host': 'localhost', 'user': 'root', 'password': '123456', 'port': 3306, 'database': 'warehouse',
              'charset': 'utf8'}

def run():
    csvFile = open("connect_local1.csv", "rb")
    reader = csv.reader(csvFile)

    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)

    conn1 = mysql.connector.connect(**config1)
    cursor1 = conn1.cursor(buffered=True)
    for line in reader:
        stars = set()
        gens = set()
        acts = set()

        id = ""
        for i in range(len(line)):
            if i == 0:
                id = line[0]
                continue


            cursor.execute(sql1, (line[i],))

            if cursor.rowcount != 0:
                for res in cursor:
                    act = str(res[0]).split(",")
                    if act[0] != '':
                        stars.add(act[0])
                    for a in act:
                        if a != '':
                            acts.add(a)

                    gens.add(str(res[1]))

        print stars
        print gens
        print acts

        try:
            sql2 = "INSERT INTO starring (movie_id, name) VALUE (%s, %s);"
            for star in stars:
                cursor1.execute(sql2, (id, star))
            sql3 = "INSERT INTO genre (movie_id, genre) VALUE (%s, %s);"
            for gen in gens:
                cursor1.execute(sql3, (id, gen))
            sql4 = "INSERT INTO actor (movie_id, name) VALUE (%s, %s);"
            for act in acts:
                cursor1.execute(sql4, (id, act))
            cursor1.execute("Commit;")
            print "Succeed! " + id
            print datetime.datetime.now()
        except Exception, e:
            print e
            print "write data error! " + id

    cursor.close()
    conn.close()
    cursor1.close()
    conn1.close()



if __name__ == '__main__':
    run()
