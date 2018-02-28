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
    csvFile = open("connect_local1.csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row for row in reader]

    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]


    c = open("miaomiao/title.csv", "ab")
    writer = csv.writer(c)
    for i in ids:
        id = i[0]

        title = ''
        for miao in i:
            for data in datas:
                if miao == data[0]:
                    if data[1] != '' and data[1] is not None:
                        title = data[1]
                        break

        writer.writerow((id, title))
        print id
        print datetime.datetime.now()

    c.close()
    print "miaomiao"

if __name__ == '__main__':
    run()