# -*- coding: utf-8 -*-
import re
import os
from lxml import html
import csv
import datetime
import time
import mysql.connector
from multiprocessing import Process

import sys
reload(sys)
sys.setdefaultencoding('utf-8')
config = {'host': 'localhost', 'user': 'root', 'password': '123456', 'port': 3306, 'database': 'warehouse',
              'charset': 'utf8'}

def run():
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)

    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row[0] for row in reader]

    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    r_dates = [row[12] for row in reader]

    # csvFile = open("warehouse_product.csv", "rb")
    # reader = csv.reader(csvFile)
    # r_dates = [row[13] for row in reader]

    c = open("p_date.csv", "ab")
    writer = csv.writer(c)
    for i in range(len(ids)):

        r_date = r_dates[i].split('-')

        if r_date[0] == '':
            writer.writerow((ids[i], '', '', ''))
        else:

            # print p_date[1]
            if r_date[1] == '0':
                r_date[1] = r_date[1][1:]
            # print p_date[1]
            # print "miaomiao"

            date = time.strptime(r_dates[i], "%Y-%m-%d")
            date = datetime.datetime(date[0], date[1], date[2])
            day = date.weekday()

            writer.writerow((ids[i], r_date[0]+r_date[1]+str(day), r_date[0], r_date[1], day))
            print ids[i]
            print datetime.datetime.now()

    c.close()
    print "Finish!"

def find():
    csvFile = open("p_date.csv", "rb")
    reader = csv.reader(csvFile)
    ps = [row for row in reader]

    csvFile = open("r_date.csv", "rb")
    reader = csv.reader(csvFile)
    rs = [row for row in reader]

    csvFile = open("connect_local1.csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row for row in reader]

    start = datetime.datetime.now()

    c = open("date.csv", "ab")
    writer = csv.writer(c)
    for i in ids:

        id = i.pop(0)
        date = 8888888
        for j in range(len(i)):
            for p in range(len(ps)):
                if ps[p][0] == i[j]:
                    print ps[p][1]
                    if ps[p][1] == '':
                        continue
                    temp = int(ps[p][1])
                    if temp < date:
                        date = temp

            for r in range(len(rs)):
                if rs[r][0] == i[j]:
                    print rs[r][1]
                    if rs[r][1] == '':
                        continue
                    temp = int(rs[r][1])
                    if temp < date:
                        date = temp

        print date
        print id
        print datetime.datetime.now()
        date = str(date)
        month = date[4:6]
        if month[0] == '0':
            month = month[1:]

        writer.writerow((id, date, date[0:4], month, date[-1]))

    print "Finish!"
    print datetime.datetime.now() - start

if __name__ == '__main__':
    # run()
    find()
    # str = '2018-01-02'
    # date = time.strptime(str, "%Y-%m-%d")
    # date = datetime.datetime(date[0], date[1], date[2])
    # day = date.weekday()
    # print day
