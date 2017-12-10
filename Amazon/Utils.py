# -*- coding: utf-8 -*-
import re
import os
import csv


def delete_format(s):
    # delete \r\n
    # p = re.compile('\r\n')
    # s = re.sub(p, '', s)
    # # delete blank
    # s.strip()
    s = re.sub('\s+', ' ', s)

    return s


def file_name(file_dir):
    L=[]
    for root, dirs, files in os.walk(file_dir):
        for file in files:
            if os.path.splitext(file)[1] == '.html':
                L.append(os.path.join(root, file))
    return L


def collect_error(list):
    fe = open('error.csv', 'a')
    fe_writer = csv.writer(fe)
    fe_writer.writerow(list)
    fe.close()

# def try_find_child(element):
#     children = element.getchildren()
#     if len(children):
#         return element.text + " " + children[0].text
#     else:
#         return element.text