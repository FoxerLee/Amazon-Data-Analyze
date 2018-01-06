# -*- coding: utf-8 -*-
import re
import os
from lxml import html
import csv
import datetime
from multiprocessing import Process

import sys
reload(sys)
sys.setdefaultencoding('utf-8')
PATH = '/Volumes/未来科技公司/未来科技有限公司亚马逊电影分析部/csv/'


def run():
    c = open('hive_rank.csv', "ab")
    w = csv.writer(c)

    id = 0
    for i in range(1, 358):
        csvFile = open(PATH+'reviews_'+str(i)+'.csv', "rb")
        reader = csv.reader(csvFile)
        datas = [row for row in reader]

        for i in range(len(datas)):
            if i == 0:
                continue

            w.writerow((id, datas[i][0], datas[i][4]))
            print datas[i][0]
            print datetime.datetime.now()
            id += 1
    c.close()

if __name__ == '__main__':
    run()