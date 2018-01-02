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

    # csvFile = open("warehouse_product.csv", "rb")
    # reader = csv.reader(csvFile)
    # p_dates = [row[12] for row in reader]

    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    r_dates = [row[13] for row in reader]

    c = open("r_date.csv", "ab")
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

            writer.writerow((ids[i], r_date[0], r_date[1], day))
            print ids[i]
            print datetime.datetime.now()

    c.close()
    print "Finish!"




if __name__ == '__main__':
    run()

    # str = '2018-01-02'
    # date = time.strptime(str, "%Y-%m-%d")
    # date = datetime.datetime(date[0], date[1], date[2])
    # day = date.weekday()
    # print day
