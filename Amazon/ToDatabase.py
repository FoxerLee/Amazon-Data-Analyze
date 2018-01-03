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
config1 = {'host': 'localhost', 'user': 'root', 'password': '123456', 'port': 3306, 'database': 'dw',
              'charset': 'utf8'}


def studio():
    sql1 = "SELECT * FROM studio LIMIT 1;"
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    conn1 = mysql.connector.connect(**config1)
    cursor1 = conn1.cursor(buffered=True)

    while(True):
        cursor.execute(sql1)

        if cursor.rowcount == 0:
            print "Finish! "
            print datetime.datetime.now()

        else:
            res = ""
            id = ""

            for data in cursor:
                sql2 = "SELECT * FROM studio WHERE movie_id = %s;"
                id = str(data[1])
                cursor.execute(sql2, (str(data[1]),))

                if cursor.rowcount != 0:
                    for n in cursor:
                        if str(n[0]) != '':
                            res += str(n[0]) + ","

            res = res[:-1]
            print res
            sql3 = "UPDATE movie SET studios = %s WHERE id = %s;"
            cursor1.execute(sql3, (res, id))
            cursor1.execute("Commit;")
            sql4 = "DELETE FROM studio WHERE movie_id = %s;"
            cursor.execute(sql4, (id,))
            cursor.execute("Commit;")
            print id
            print datetime.datetime.now()

    cursor.close()
    conn.close()
    cursor1.close()
    conn1.close()


def binding():
    sql1 = "SELECT * FROM binding LIMIT 1;"
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    conn1 = mysql.connector.connect(**config1)
    cursor1 = conn1.cursor(buffered=True)

    while(True):
        cursor.execute(sql1)

        if cursor.rowcount == 0:
            print "Finish! "
            print datetime.datetime.now()

        else:
            res = ""
            id = ""

            for data in cursor:
                sql2 = "SELECT * FROM binding WHERE movie_id = %s;"
                id = str(data[1])
                cursor.execute(sql2, (str(data[1]),))

                if cursor.rowcount != 0:
                    for n in cursor:
                        if str(n[0]) != '':
                            res += str(n[0]) + ","

            res = res[:-1]
            print res
            sql3 = "UPDATE movie SET binding = %s WHERE id = %s;"
            cursor1.execute(sql3, (res, id))
            cursor1.execute("Commit;")
            sql4 = "DELETE FROM binding WHERE movie_id = %s;"
            cursor.execute(sql4, (id,))
            cursor.execute("Commit;")
            print id
            print datetime.datetime.now()

    cursor.close()
    conn.close()
    cursor1.close()
    conn1.close()


def language():
    sql1 = "SELECT * FROM language LIMIT 1;"
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    conn1 = mysql.connector.connect(**config1)
    cursor1 = conn1.cursor(buffered=True)

    while(True):
        cursor.execute(sql1)

        if cursor.rowcount == 0:
            print "Finish! "
            print datetime.datetime.now()

        else:
            res = ""
            id = ""

            for data in cursor:
                sql2 = "SELECT * FROM language WHERE movie_id = %s;"
                id = str(data[1])
                cursor.execute(sql2, (str(data[1]),))

                if cursor.rowcount != 0:
                    for n in cursor:
                        if str(n[0]) != '':
                            res += str(n[0]) + ","

            res = res[:-1]
            print res
            sql3 = "UPDATE movie SET languages = %s WHERE id = %s;"
            cursor1.execute(sql3, (res, id))
            cursor1.execute("Commit;")
            sql4 = "DELETE FROM language WHERE movie_id = %s;"
            cursor.execute(sql4, (id,))
            cursor.execute("Commit;")
            print id
            print datetime.datetime.now()

    cursor.close()
    conn.close()
    cursor1.close()
    conn1.close()


def director():
    sql1 = "SELECT * FROM director LIMIT 1;"
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    conn1 = mysql.connector.connect(**config1)
    cursor1 = conn1.cursor(buffered=True)

    while(True):
        cursor.execute(sql1)

        if cursor.rowcount == 0:
            print "Finish! "
            print datetime.datetime.now()

        else:
            res = ""
            id = ""

            for data in cursor:
                sql2 = "SELECT * FROM director WHERE movie_id = %s;"
                id = str(data[1])
                cursor.execute(sql2, (str(data[1]),))

                if cursor.rowcount != 0:
                    for n in cursor:
                        if str(n[0]) != '':
                            res += str(n[0]) + ","

            res = res[:-1]
            print res
            sql3 = "UPDATE movie SET directors = %s WHERE id = %s;"
            cursor1.execute(sql3, (res, id))
            cursor1.execute("Commit;")
            sql4 = "DELETE FROM director WHERE movie_id = %s;"
            cursor.execute(sql4, (id,))
            cursor.execute("Commit;")
            print id
            print datetime.datetime.now()

    cursor.close()
    conn.close()
    cursor1.close()
    conn1.close()

def actor():
    sql1 = "SELECT * FROM actor LIMIT 1;"
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    conn1 = mysql.connector.connect(**config1)
    cursor1 = conn1.cursor(buffered=True)

    while(True):
        cursor.execute(sql1)

        if cursor.rowcount == 0:
            print "Finish! "
            print datetime.datetime.now()

        else:
            res = ""
            id = ""

            for data in cursor:
                sql2 = "SELECT * FROM actor WHERE movie_id = %s;"
                id = str(data[1])
                cursor.execute(sql2, (str(data[1]),))

                if cursor.rowcount != 0:
                    for n in cursor:
                        if str(n[0]) != '':
                            res += str(n[0]) + ","

            res = res[:-1]
            print res
            sql3 = "UPDATE movie SET actors = %s WHERE id = %s;"
            cursor1.execute(sql3, (res, id))
            cursor1.execute("Commit;")
            sql4 = "DELETE FROM actor WHERE movie_id = %s;"
            cursor.execute(sql4, (id,))
            cursor.execute("Commit;")
            print id
            print datetime.datetime.now()

    cursor.close()
    conn.close()
    cursor1.close()
    conn1.close()


def genre():
    sql1 = "SELECT * FROM genre LIMIT 1;"
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    conn1 = mysql.connector.connect(**config1)
    cursor1 = conn1.cursor(buffered=True)

    while(True):
        cursor.execute(sql1)

        if cursor.rowcount == 0:
            print "Finish! "
            print datetime.datetime.now()

        else:
            res = ""
            id = ""

            for data in cursor:
                sql2 = "SELECT * FROM genre WHERE movie_id = %s;"
                id = str(data[1])
                cursor.execute(sql2, (str(data[1]),))

                if cursor.rowcount != 0:
                    for n in cursor:
                        if str(n[0]) != '':
                            res += str(n[0]) + ","

            res = res[:-1]
            print res
            sql3 = "UPDATE movie SET genres = %s WHERE id = %s;"
            cursor1.execute(sql3, (res, id))
            cursor1.execute("Commit;")
            sql4 = "DELETE FROM genre WHERE movie_id = %s;"
            cursor.execute(sql4, (id,))
            cursor.execute("Commit;")
            print id
            print datetime.datetime.now()

    cursor.close()
    conn.close()
    cursor1.close()
    conn1.close()


def starring():
    sql1 = "SELECT * FROM starring LIMIT 1;"
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    conn1 = mysql.connector.connect(**config1)
    cursor1 = conn1.cursor(buffered=True)

    while(True):
        cursor.execute(sql1)

        if cursor.rowcount == 0:
            print "Finish! "
            print datetime.datetime.now()

        else:
            res = ""
            id = ""

            for data in cursor:
                sql2 = "SELECT * FROM starring WHERE movie_id = %s;"
                id = str(data[1])
                cursor.execute(sql2, (str(data[1]),))

                if cursor.rowcount != 0:
                    for n in cursor:
                        if str(n[0]) != '':
                            res += str(n[0]) + ","

            res = res[:-1]
            print res
            sql3 = "UPDATE movie SET starrings = %s WHERE id = %s;"
            cursor1.execute(sql3, (res, id))
            cursor1.execute("Commit;")
            sql4 = "DELETE FROM starring WHERE movie_id = %s;"
            cursor.execute(sql4, (id,))
            cursor.execute("Commit;")
            print id
            print datetime.datetime.now()

    cursor.close()
    conn.close()
    cursor1.close()
    conn1.close()




if __name__ == '__main__':
    # studio()
    # csvFile = open("connect_local1.csv", "rb")
    # reader = csv.reader(csvFile)
    # runs = [row[0] for row in reader]
    #
    # conn1 = mysql.connector.connect(**config1)
    # cursor1 = conn1.cursor(buffered=True)
    # sql = "INSERT INTO movie (id) VALUE (%s);"
    # for run in runs:
    #     cursor1.execute(sql, (run, ))
    #     print datetime.datetime.now()
    #     print run
    #     cursor1.execute("Commit;")
    #
    # print "miaomiao"
    p1 = Process(target=studio, )
    p2 = Process(target=binding, )
    p3 = Process(target=language, )
    p4 = Process(target=director, )
    p5 = Process(target=actor, )
    p6 = Process(target=genre, )
    p7 = Process(target=starring, )

    p1.start()
    p2.start()
    p3.start()
    p4.start()
    p5.start()
    p6.start()
    p7.start()

