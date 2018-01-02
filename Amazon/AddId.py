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


def run():
    csvFile = open("connect_local.csv", "rb")
    reader = csv.reader(csvFile)
    asins = [row for row in reader]

    csvFile1 = open("connect_local1.csv", "ab")
    writer = csv.writer(csvFile1)
    id = 0
    for asin in asins:
        asin.insert(0, id)
        writer.writerow(asin)
        id += 1

    csvFile.close()
    csvFile1.close()







if __name__ == '__main__':
    run()