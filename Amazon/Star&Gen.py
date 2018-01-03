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
sql1 = "SELECT genres, starring, actors FROM product_two WHERE id=%s;"
config1 = {'host': 'localhost', 'user': 'root', 'password': '123456', 'port': 3306, 'database': 'warehouse',
              'charset': 'utf8'}

def run():
    csvFile = open("connect_local.csv", "rb")
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
                    star = str(res[0]).split(",")
                    for s in star:
                        if s != '':
                            stars.add(s)
                    gen = str(res[1]).split(",")
                    for g in gen:
                        if g != '':
                            gens.add(g)
                    act = str(res[2]).split(",")
                    for a in act:
                        if a != '':
                            acts.add(a)

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
