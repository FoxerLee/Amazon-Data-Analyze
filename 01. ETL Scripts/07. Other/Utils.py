# -*- coding: utf-8 -*-
import re
import os
import csv
import time
import datetime


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


def temp():


    csvFile = open("miaomiao/miaomiao.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]

    c = open("miaomiao/movies.csv", "ab")
    w = csv.writer(c)


    for data in datas:
        if data[9] != '':
            date = time.strptime(data[9], "%Y-%m-%d")
            d = datetime.datetime(date[0], date[1], date[2])
            day = d.weekday()

            w.writerow((data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], date[0], date[1], day))
        else:
            w.writerow((data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9],
                        data[10], '', '', ''))
        print data[0]
        print datetime.datetime.now()

    print "打死不再改数据了！"

    c.close()


def temp1():
    csvFile = open("miaomiao/re_director.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]

    csvFile = open("miaomiao/d2g.csv", "rb")
    reader = csv.reader(csvFile)
    data1s = [row for row in reader]

    csvFile = open("miaomiao/d2a.csv", "rb")
    reader = csv.reader(csvFile)
    data2s = [row for row in reader]

    c = open("miaomiao/final_director.csv", "ab")
    w = csv.writer(c)

    for data in datas:

        style = ""
        for data1 in data1s:
            if data1[0] == data[0]:
                if data1[2] != 0:
                    style += data1[1] + ','
        if style[len(style)-1] == ',':
            style = style[:-1]

        name = ""
        cop = ""
        for data2 in data1s:
            if data2[0] == data[0]:
                if data2[2] != 0:
                    name += data2[1] + ','
                    cop += data2[2] + ','
        if name[len(name) - 1] == ',':
            name = name[:-1]
        if cop[len(cop) - 1] == ',':
            cop = cop[:-1]

        w.writerow((data[0], data[1], data[2], style, name, cop))

        print data[0]
        print datetime.datetime.now()

    print "打死不再改数据了！"

    c.close()



if __name__ == '__main__':
    temp1()
    # date = time.strptime('2017-08-09', "%Y-%m-%d")
    # d = datetime.datetime(date[0], date[1], date[2])
    #
    # print date[0]