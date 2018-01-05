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


def run():
    csvFile = open("date.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]

    new_datas = []
    for i in range(len(datas)):
        new_datas.append([datas[i][1], datas[i][0], 0])

    c = open("re_date.csv", "ab")
    writer = csv.writer(c)
    for i in range(len(new_datas)):
        if new_datas[i][2] == 1:
            continue
        id = new_datas[i][0]
        data = new_datas[i][1]
        count = 1
        new_datas[i][2] = 1
        for j in range(len(new_datas)):
            if new_datas[j][2] == 1:
                continue
            else:
                if new_datas[j][0] == id:
                    data += ',' + new_datas[j][1]
                    count += 1
                    new_datas[j][2] = 1

        if data != '' and data is not None:
            if data[0] == ',':
                data = data[1:]
        print data
        print id
        print datetime.datetime.now()
        writer.writerow((id, count, data))

    print "Finish!"
    c.close()

def divide():
    csvFile = open("miaomiao/re_date.csv", "rb")
    reader = csv.reader(csvFile)
    dates = [row[0] for row in reader]

    csvFile = open("miaomiao/re_date.csv", "rb")
    reader = csv.reader(csvFile)
    counts = [row[1] for row in reader]

    csvFile = open("miaomiao/re_date.csv", "rb")
    reader = csv.reader(csvFile)
    movies = [row[2] for row in reader]


    c = open("miaomiao/real_re_date.csv", "ab")
    w = csv.writer(c)
    w.writerow(("id", "year", "month", "week", "count", "movies"))
    id = 0
    for i in range(len(dates)):
        date = dates[i]
        year = date[0:4]
        month = date[4:6]
        week = date[len(date)-1:]

        print date
        print year
        print month
        print week

        if month[0] == '0':
            month = month[1:]

        w.writerow((id, year, month, week, counts[i], movies[i]))
        id += 1
    c.close()


if __name__ == '__main__':
    # run()
    divide()