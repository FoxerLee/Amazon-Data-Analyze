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
config = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'warehouse',
              'charset': 'utf8'}
PATH = '/Volumes/未来科技公司/未来科技有限公司亚马逊电影分析部/'

def file_name(file_dir):

    L = []
    for root, dirs, files in os.walk(file_dir):
        for file in files:
            if os.path.splitext(file)[1] == '.html':
                L.append(os.path.join(root, file))

    # print "Succeed! " + str(datetime.datetime.now())

    return L


def run(i):
    L = file_name(i + '/two')

    for l in L:
        f = open(name=l, mode='r')
        index_1 = l.find('two', 1)
        index_2 = l.find('.html', 1)
        id = l[index_1 + 4: index_2]
        text = f.read()
        tree = html.fromstring(text)

        url = ""
        try:
            url = tree.xpath('//*[@id="aiv-main-content"]/div[4]/div/div/img/@src')[0]

            # print url
            # print id
        except Exception, e:
            print e
            print "No photo! "

        sql = "UPDATE warehouse.product_two SET url=\'" + url + "\' WHERE id=\'" + id + "\';"

        conn = mysql.connector.connect(**config)
        cursor = conn.cursor()
        try:
            cursor.execute(sql)
            cursor.execute("Commit;")
            cursor.close()
            conn.close()
            print "Succeed! " + id
            print datetime.datetime.now()
        except Exception, e:
            print e
            print "error! " + id
            cursor.close()
            conn.close()


if __name__ == "__main__":
    # run(PATH+"2")
    p1 = Process(target=run, args=(PATH+"9",))
    p2 = Process(target=run, args=(PATH+"12",))
    p3 = Process(target=run, args=(PATH+"17",))
    p4 = Process(target=run, args=(PATH+"19",))
    p5 = Process(target=run, args=(PATH + "21",))
    p1.start()
    p2.start()
    p3.start()
    p4.start()
    p5.start()