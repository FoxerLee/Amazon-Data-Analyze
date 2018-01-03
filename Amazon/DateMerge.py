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


if __name__ == '__main__':
    run()
