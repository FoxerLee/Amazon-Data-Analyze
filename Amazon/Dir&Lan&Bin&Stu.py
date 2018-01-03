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
sql1 = "SELECT directors, languages, binding, studio FROM product WHERE id=%s;"
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
        dirs = set()
        lans = set()
        bins = set()
        stus = set()


        id = ""
        for i in range(len(line)):
            if i == 0:
                id = line[0]
                continue

            cursor.execute(sql1, (line[i],))

            for res in cursor:
                dir = str(res[0]).split(",")
                for d in dir:
                    dirs.add(d)
                lan = str(res[1]).split(",")
                for l in lan:
                    lans.add(l)
                bin = str(res[2]).split(",")
                for b in bin:
                    bins.add(b)
                stu = str(res[3]).split(",")
                for s in stu:
                    stus.add(s)

        print dirs
        print lans
        print bins
        print stus

        try:
            sql2 = "INSERT INTO director (movie_id, name) VALUE (%s, %s);"
            for dir in dirs:
                cursor1.execute(sql2, (id, dir))
            sql3 = "INSERT INTO language (movie_id, language) VALUE (%s, %s);"
            for lan in lans:
                cursor1.execute(sql3, (id, lan))
            sql4 = "INSERT INTO binding (movie_id, binding) VALUE (%s, %s);"
            for bin in bins:
                cursor1.execute(sql4, (id, bin))
            sql5 = "INSERT INTO studio (movie_id, studio) VALUE (%s, %s);"
            for stu in stus:
                cursor1.execute(sql5, (id, stu))
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







if __name__ == "__main__":
    run()
    # conn = mysql.connector.connect(**config)
    # cursor = conn.cursor(buffered=True)
    # cursor.execute("SELECT id_1, id_2, has FROM test;")
    #
    # for miao in cursor:
    #     print str(miao[0]) + " " + str(miao[1]) + " " + str(miao[2])