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

# config = {'host': 'localhost', 'user': 'root', 'password': '123456', 'port': 3306, 'database': 'warehouse',
#               'charset': 'utf8'}
config1 = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'dw',
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
        new_datas.append([ids[i], datas[i], 0])

    for i in range(len(new_datas)):
        if new_datas[i][2] == 1:
            continue
        id = new_datas[i][0] 
        data = new_datas[i][1] 
        new_datas[i][2] = 1
        for j in range(len(new_datas)):
            if new_datas[j][2] == 1:
                continue
            else:
                if new_datas[j][0] == id:
                    data += ',' + new_datas[j][1]
                    new_datas[j][2] = 1
        c = open("miaomiao/"+t+".csv", "ab")
        writer = csv.writer(c)
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
        if data[0] == ',':
            data = data[1:]
        print data
        print id
        print datetime.datetime.now()
        writer.writerow((id, data))

    print "Finish!"
    c.close()


def normalize(t):
    csvFile = open("miaomiao/" + t + ".csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]

    csvFile = open("connect_local1.csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row[0] for row in reader]

    c = open("miaomiao/td_" + t + ".csv", "ab")
    writer = csv.writer(c)
    for id in ids:
        data = None
        for j in range(len(datas)):
            if id == datas[j][0]:
                data = datas[j][1]
                break

        writer.writerow((id, data))
        print datetime.datetime.now()
        print id
                # break
    c.close()
    print "Finish!"


def reverse(t):
    csvFile = open("miaomiao/warehouse_" + t + ".csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row[0] for row in reader]

    csvFile = open("miaomiao/warehouse_" + t + ".csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row[1] for row in reader]

    new_datas = []
    for i in range(len(ids)):
        new_datas.append([ids[i], datas[i], 0])

    c = open("miaomiao/re_" + t + ".csv", "ab")
    writer = csv.writer(c)

    for i in range(len(new_datas)):
        if new_datas[i][2] == 1:
            continue
        id = new_datas[i][0]
        # data = new_datas[i][1]
        count = 1
        new_datas[i][2] = 1
        for j in range(len(new_datas)):
            if new_datas[j][2] == 1:
                continue
            else:
                if new_datas[j][0] == id:
                    # data += ',' + new_datas[j][1]
                    count += 1
                    new_datas[j][2] = 1

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
        # if data[0] == ',':
        #     data = data[1:]


        # print data
        print id
        print datetime.datetime.now()
        # writer.writerow((id, count, data))
        writer.writerow((id, count))
    print "Finish!"
    c.close()


if __name__ == '__main__':
    p1 = Process(target=reverse, args=('studio',))
    # p2 = Process(target=run, args=('binding',))
    # p3 = Process(target=run, args=('language',))
    # p4 = Process(target=run, args=('director',))
    # p5 = Process(target=reverse, args=('actor',))
    # p6 = Process(target=normalize, args=('genre',))
    # p7 = Process(target=normalize, args=('starring',))
    p1.start()
    # p2.start()
    # p3.start()
    # p4.start()
    # p5.start()
    # p6.start()
    # p7.start()


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

