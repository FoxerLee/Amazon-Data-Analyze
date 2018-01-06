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
    csvFile = open("miaomiao/re_actor.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]



