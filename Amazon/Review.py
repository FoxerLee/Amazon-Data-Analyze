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
    L = file_name(i + '/one')
    c = open(i+".csv", "ab")
    w = csv.writer(c)
    for l in L:
        f = open(name=l, mode='r')
        index_1 = l.find('one', 1)
        index_2 = l.find('.html', 1)
        id = l[index_1+4: index_2]
        text = f.read()
        tree = html.fromstring(text)

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


        w.writerow((id, review, review_count))
        print id
        print datetime.datetime.now()
    c.close()


if __name__ == '__main__':
    # run(PATH+'2')
    p1 = Process(target=run, args=(PATH + '5',))
    p2 = Process(target=run, args=(PATH + '7',))
    p3 = Process(target=run, args=(PATH + '8',))
    p4 = Process(target=run, args=(PATH + '9',))
    p1.start()
    p2.start()
    p3.start()
    p4.start()