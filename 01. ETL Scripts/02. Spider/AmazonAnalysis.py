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

# 分析html并且爬取数据
config = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'warehouse',
              'charset': 'utf8'}
PATH = '/Volumes/未来科技公司/未来科技有限公司亚马逊电影分析部/'
sql = "INSERT INTO connect_id VALUES (%s,%s)"
def file_name(file_dir):
    L=[]
    for root, dirs, files in os.walk(file_dir):
        for file in files:
            if os.path.splitext(file)[1] == '.html':
                L.append(os.path.join(root, file))
    return L


def run(i):
    start = datetime.datetime.now()
    L = file_name(i + '/one')

    # fw = open('result.csv', 'w')
    # writer = csv.writer(fw)
    # file_header = ['id', 'filename']
    # writer.writerow(file_header)
    # 记录错误情况
    # fe = open('error.csv', 'w')
    # error_title = ['title']

    for l in L:
        f = open(name=l, mode='r')
        text = f.read()
        try:
            tree = html.fromstring(text)
        except Exception, e:
            print "miaomiao " + id

        result = set()
        # print l[7:-5]

        index_1 = l.find('one')
        index_2 = l.find('.html')
        oid = l[index_1 + 4:index_2]
        print oid
        # result.add(l[4:-5])
        swatchs = tree.xpath('//*[@id="tmmSwatches"]/ul/li[@class="swatchElement unselected"]')

        for swatch in swatchs:
            try:
                id = swatch.xpath('./span/span/span/a/@href')[0]
                id = id.split('/')[3]
                # print id
                result.add(id)
            except Exception, e:
                print e
                print "swatch error!"
                continue

        tops = tree.xpath('//*[@id="twister"]/div[@class="top-level unselected-row"]')
        for top in tops:
            try:

                id = top.xpath('./span/table/tr/td[1]/a/@href')[0]
                id = id.split('/')[3]
                # print id
                result.add(id)
            except Exception, e:
                print e
                print "top error!"
                continue

        mores = tree.xpath('//*[@id="twister"]/div[9]/div[1]/div[@class="top-level unselected-row"]')
        for more in mores:
            try:
                id = more.xpath('./span/table/tr/td[1]/a/@href')[0]
                id = id.split('/')[3]
                # print id
                result.add(id)
            except Exception, e:
                print e
                print "more error!"
                continue

        ids = []
        if len(result) is 0:
            result.add(oid)

        for id in result:
            ids.append([oid, id])
        # print ids
        try:
            # conn = mysql.connector.connect(**config)
            # cursor = conn.cursor()
            # cursor.executemany(sql, ids)
            # cursor.execute("Commit;")
            csvFile = open("connect_id 1,2,3,5.csv", "ab")
            writer = csv.writer(csvFile)

            writer.writerows(ids)

            csvFile.close()

            print datetime.datetime.now()
            print "succeed! " + oid
            # cursor.close()
            # conn.close()
        except Exception, e:
            print e
            print "write data error!"
            # cursor.close()
            # conn.close()
            continue

    print " finally!"
    print datetime.datetime.now() - start

    # except:
    #     error_title.append(l)

    # 获取content 部分的内容

    # writer.writerow(result)
    # f.close()

    # fw.close()
    # fe_writer = csv.writer(fe)

    # fe_writer.writerow(error_title)
    # fe.close()


if __name__ == "__main__":
    p1 = Process(target=run, args=(PATH+'1',))
    p2 = Process(target=run, args=(PATH+'2',))
    p3 = Process(target=run, args=(PATH+'3',))
    p4 = Process(target=run, args=(PATH + '5',))
    p1.start()
    p2.start()
    p3.start()
    p4.start()



