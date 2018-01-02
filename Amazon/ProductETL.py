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

sql = "INSERT INTO m_product (product_id, price, sales_rank, binding, running_time) VALUES (%s,%s,%s,%s,%s)"
def run():
    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    pros = [row[0] for row in reader]

    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    pris = [row[5] for row in reader]

    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    sals = [row[4] for row in reader]

    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    bins = [row[8] for row in reader]

    csvFile = open("warehouse_product.csv", "rb")
    reader = csv.reader(csvFile)
    runs = [row[11] for row in reader]

    count = 0
    result = []
    # for column in columns:
    #     if count == 20000:
    #         conn = mysql.connector.connect(**config)
    #         cursor = conn.cursor(buffered=True)
    #         cursor.executemany(sql, result)
    #         result = []
    #         count = 0

    for i in range(len(pros)):
        if count == 20000:
            conn = mysql.connector.connect(**config)
            cursor = conn.cursor(buffered=True)
            cursor.executemany(sql, result)
            cursor.execute("Commit;")
            result = []
            count = 0
            cursor.close()
            conn.close()
            print datetime.datetime.now()
            print "Commit succeed!"

        if pris[i] == '':
            pris[i] = None

        if sals[i] == '':
            sals[i] = None
        if runs[i] == '':
            runs[i] = None
        result.append((pros[i], pris[i], sals[i], bins[i], runs[i]))
        count += 1

    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)
    cursor.executemany(sql, result)
    cursor.execute("Commit;")
    cursor.close()
    conn.close()
    print datetime.datetime.now()
    print "Commit succeed!"

    print "Finally!"




if __name__ == '__main__':
    run()




