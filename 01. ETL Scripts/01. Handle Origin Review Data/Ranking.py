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

# 获取各个段评论分数的个数
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

def statistics():
    csvFile = open("connect_local1.csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row for row in reader]

    csvFile = open("rank.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]

    c = open("miaomiao/final_ranking.csv", "ab")
    writer = csv.writer(c)

    for i in ids:
        id = i[0]

        rank_1 = 0
        rank_2 = 0
        rank_3 = 0
        rank_4 = 0
        rank_5 = 0
        for miao in i:
            for data in datas:
                if miao == data[0]:
                    if data[1] <= 1:
                        rank_1 += 1
                    elif 1 < data[1] <= 2:
                        rank_2 += 1
                    elif 2 < data[1] <= 3:
                        rank_3 += 1
                    elif 3 < data[1] <= 4:
                        rank_4 += 1
                    else:
                        rank_5 += 1

        writer.writerow((id, rank_1, rank_2, rank_3, rank_4, rank_5))
        print id
        print datetime.datetime.now()
    c.close()

    print "miaomiao"


if __name__ == '__main__':
    statistics()