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


def run(t):
    csvFile = open("miaomiao/warehouse_"+t+".csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row[1] for row in reader]

    csvFile = open("miaomiao/warehouse_"+t+".csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row[0] for row in reader]


    new_datas = []
    for i in range(len(ids)):
        new_datas.append((ids[i], datas[i], 0))

    for i in range(len(new_datas)):
        if new_datas[i][2] == 1:
            continue
        id = ids[i]
        data = datas[i]
        for j in range(len(new_datas)):
            if new_datas[j][2] == 1:
                continue
            else:
                if new_datas[j][0] == 
    # c = open("miaomiao/"+t+".csv", "ab")
    # writer = csv.writer(c)
    # for i in range(len(ids)):
    #     id = ids[i]
    #     data = datas[i]
    #     ids.pop(i)
    #     datas.pop(i)
    #     print i
    #     for (id, data) in range(i+1, len(ids)):
    #         print j
    #         if ids[j] == id:
    #             data += datas[j] + ','
    #             datas.pop(j)
    #             ids.pop(j)
    #
    #     print data
    #     print id
    #     print datetime.datetime.now()
        # writer.writerow(id, data)

    print "Finish!"



if __name__ == '__main__':
    run("actor")
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

