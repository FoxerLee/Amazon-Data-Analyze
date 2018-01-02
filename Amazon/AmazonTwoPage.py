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
sql = "INSERT INTO product_two VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)"
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
        id = l[index_1+4: index_2]
        text = f.read()
        tree = html.fromstring(text)

        IMDb = None
        try:
            IMDb = tree.xpath('//*[@id="aiv-main-content"]/div[3]/div/span[2]/strong/text()')[0]
            IMDb = str(IMDb)
        except Exception, e:
            print e
            print "error IMDb! " + id

        trs = tree.xpath('//*[@id="dv-center-features"]/div[1]/div/table/tr')

        genres = ""
        starrings = ""
        actors = ""
        rating = ""
        for tr in trs:
            name = str(tr.xpath('./th/text()')[0])
            if name.find('Genres') != -1:
                try:
                    gs = tr.xpath('./td/a')
                    for g in gs:
                        genre = g.xpath('./text()')[0]

                        genres += genre + ","
                except Exception, e:
                    print e
                    print "error genres! " + id

            if name.find('Starring') != -1:
                try:
                    ss = tr.xpath('./td/a')

                    for s in ss:
                        starring = s.xpath('./text()')[0]
                        starrings += starring + ","
                except Exception, e:
                    print e
                    print "error Starrings! " + id

            if name.find('Supporting actors') != -1:
                try:
                    aas = tr.xpath('./td/a')

                    for aa in aas:
                        actor = aa.xpath('./text()')[0]
                        actors += actor + ","
                except Exception, e:
                    print e
                    print "error Actors! " + id

            if name.find('MPAA rating') != -1:
                try:
                    rating = str(tr.xpath('./td/text()')[0])
                    rating = rating.lstrip()
                    rating = rating.rstrip()

                except Exception, e:
                    print e
                    print "error Rating! " + id

        review_count = None
        try:
            review_count = tree.xpath('//*[@id="reviewSummary"]/div[1]/a/div/div/div[2]/div/span/text()')[0]
            review_count = str(review_count)
            review_count = review_count.replace(',', '')
        except Exception, e:
            print e
            print "error review_count! " + id

        review = None
        try:
            review = tree.xpath('//*[@id="reviewSummary"]/div[2]/span/a/span/text()')[0]
            review = str(review)
            review = review.replace('out of 5 stars', '')
            review = review.rstrip()
        except Exception, e:
            print e
            print "error review! " + id

        result = []
        result.append((id, IMDb, genres, starrings, actors, rating, review_count, review, None))
        try:
            conn = mysql.connector.connect(**config)
            cursor = conn.cursor()
            cursor.executemany(sql, result)
            cursor.execute("Commit;")
            print datetime.datetime.now()
            print "succeed! " + id
            cursor.close()
            conn.close()

        except Exception, e:
            print e
            print "write data error!" + id
            # sql1 = "INSERT INTO error_two VALUE ( \'" + id + "\');"
            # cursor.execute(sql1)
            # cursor.execute("Commit;")
            cursor.close()
            conn.close()
            continue



if __name__ == "__main__":
    #file_name(PATH + '2')
    # run(PATH + '2')
    p1 = Process(target=run, args=(PATH+'1',))
    p2 = Process(target=run, args=(PATH+'3',))
    # p3 = Process(target=run, args=(PATH+'8',))
    # p4 = Process(target=run, args=('9',))
    p1.start()
    p2.start()
    # p3.start()
    # p4.start()