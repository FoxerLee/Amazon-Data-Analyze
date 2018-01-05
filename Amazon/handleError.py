# -*- coding: utf-8 -*-
from amazon.api import AmazonAPI, LookupException
from Support import *
import mysql.connector
import datetime
import csv
import sys
import time
import signal
from multiprocessing import Process
reload(sys)
sys.setdefaultencoding('utf-8')


def run():
    config = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'warehouse',
              'charset': 'utf8'}
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor()

    sql = 'SELECT id FROM product'
    cursor.execute(sql)

    count = 0
    ids = []
    for res in cursor:
        id = res[0]
        count += 1
        ids.append(id)

    csvfile = open('productId_0.csv', 'r')
    reader = csv.reader(csvfile)

    column = [row[0] for row in reader]
    column = column[1:]

    print len(column)
    print count
    print len(column) - count

    retD = list(set(column).difference(set(ids)))

    print len(retD)


    for i in retD:
        sql = "INSERT INTO id VALUE (\'" + i + "\');"
        cursor.execute(sql)
        print i
    cursor.execute("Commit;")
    cursor.close()
    conn.close
    print "Succeed!"


def genre():
    csvFile = open("miaomiao/genre.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]

    c = open("miaomiao/new_genre.csv", "ab")
    writer = csv.writer(c)
    for i in range((len(datas))):
        datas[i][1] = datas[i][1].replace(',None', '')
        datas[i][1] = datas[i][1].replace('None', '')

        writer.writerow((datas[i][0],datas[i][1]))
        print datetime.datetime.now()
        print datas[i][0]

    print "Finish!"

if __name__ == "__main__":
    # run()
    genre()