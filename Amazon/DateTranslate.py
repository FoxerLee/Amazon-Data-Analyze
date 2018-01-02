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

sql = "SELECT id, publication_date, release_date FROM product"
def run():
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor(buffered=True)

    cursor.execute(sql)

    for data in cursor:
        print str
