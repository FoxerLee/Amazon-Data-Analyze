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

config = {'host': 'localhost', 'user': 'root', 'password': '123456', 'port': 3306, 'database': 'warehouse',
              'charset': 'utf8'}
config1 = {'host': 'localhost', 'user': 'root', 'password': '123456', 'port': 3306, 'database': 'dw',
              'charset': 'utf8'}



def run():
    csvFile = open("connect_local1.csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row[0] for row in reader]

    csvFile = open("miaomiao/td_actor.csv", "rb")
    reader = csv.reader(csvFile)
    actors = [row[1] for row in reader]

    csvFile = open("miaomiao/td_binding.csv", "rb")
    reader = csv.reader(csvFile)
    bindings = [row[1] for row in reader]

    csvFile = open("miaomiao/td_director.csv", "rb")
    reader = csv.reader(csvFile)
    directors = [row[1] for row in reader]

    csvFile = open("miaomiao/td_genre.csv", "rb")
    reader = csv.reader(csvFile)
    genres = [row[1] for row in reader]

    csvFile = open("miaomiao/td_language.csv", "rb")
    reader = csv.reader(csvFile)
    languages = [row[1] for row in reader]

    csvFile = open("miaomiao/td_starring.csv", "rb")
    reader = csv.reader(csvFile)
    starrings = [row[1] for row in reader]

    csvFile = open("miaomiao/td_studio.csv", "rb")
    reader = csv.reader(csvFile)
    studios = [row[1] for row in reader]

    csvFile = open("miaomiao/td_studio.csv", "rb")
    reader = csv.reader(csvFile)
    studios = [row[1] for row in reader]

    csvFile = open("miaomiao/td_title.csv", "rb")
    reader = csv.reader(csvFile)
    titles = [row[1] for row in reader]

    csvFile = open("miaomiao/td_date.csv", "rb")
    reader = csv.reader(csvFile)
    dates = [row[5] for row in reader]

    csvFile = open("connect_local1.csv", "rb")
    reader = csv.reader(csvFile)
    all_products = [row for row in reader]

    products = []

    for all_product in all_products:
        product = ''
        for i in range(len(all_product)):
            if i == 0:
                continue
            product += all_product[i] + ','

        product = product[:-1]
        products.append(product)

    a1 = re.compile("\[.*?\]")
    a2 = re.compile("\(.*?\)")



    c = open("miaomiao/miaomiao.csv", "ab")
    writer = csv.writer(c)
    writer.writerow(('id', 'actors', 'binding', 'directors', 'genres', 'languages', 'starrings', 'studios', 'title', 'date', 'products'))
    for i in range(len(ids)):
        titles[i] = a1.sub('', titles[i])
        titles[i] = a2.sub('', titles[i])

        if studios[i] != '' and studios[i] is not None:
            if studios[i][0] == ' ':
                studios[i] = studios[i][1:]
        writer.writerow((ids[i], actors[i], bindings[i], directors[i], genres[i], languages[i], starrings[i], studios[i], titles[i], dates[i], products[i]))
        print ids[i]
        print datetime.datetime.now()
    c.close()

    print 'miaomiao'

if __name__ == '__main__':
    run()



