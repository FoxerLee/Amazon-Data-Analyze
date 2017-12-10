# -*- coding: utf-8 -*-
import re
import os
from lxml import html
import csv
from Utils import *

import sys
reload(sys)
sys.setdefaultencoding('utf-8')


def run():
    L = file_name('one')

    fw = open('result.csv', 'w')
    writer = csv.writer(fw)
    file_header = ['id', 'filename']
    writer.writerow(file_header)
    # 记录错误情况
    # fe = open('error.csv', 'w')
    error_title = ['title']

    for l in L:
        f = open(name=l, mode='r')

        text = f.read()
        tree = html.fromstring(text)

        result = []
        result.append(l[4:])
        # 读取title 电影名
        try:
            title = str(tree.xpath('//*[@id="productTitle"]/text()')[0])
            print title
            title = delete_format(title)
            result.append(title)
        except:
            error_title.append(l)

        # 获取content 部分的内容


        writer.writerow(result)
        f.close()

    fw.close()
    # fe_writer = csv.writer(fe)


    # fe_writer.writerow(error_title)
    # fe.close()






