# -*- coding: utf-8 -*-
import csv
import datetime

import sys
reload(sys)
sys.setdefaultencoding('utf-8')


def temp1():
    csvFile = open("re_actor.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]

    csvFile = open("a2g.csv", "rb")
    reader = csv.reader(csvFile)
    data1s = [row for row in reader]

    csvFile = open("a2d.csv", "rb")
    reader = csv.reader(csvFile)
    data2s = [row for row in reader]

    c = open("final_actor.csv", "ab")
    w = csv.writer(c)

    for data in datas:

        style = ""
        for data1 in data1s:
            if data1[0] == data[0]:
                if data1[2] != '0':
                    if style != data1[1] + ',':
                        style += data1[1] + ','

        if style != '':
            if style[len(style)-1] == ',':
                style = style[:-1]

        name = ""
        cop = ""
        for data2 in data2s:
            if data2[0] == data[0]:
                if data2[2] != '0':
                    name += data2[1] + ','
                    cop += data2[2] + ','
                    
        if name != '':
            if name[len(name) - 1] == ',':
                name = name[:-1]
        if cop != '':
            if cop[len(cop) - 1] == ',':
                cop = cop[:-1]

        w.writerow((data[0], data[1], data[2], style, name, cop))

        print data[0]
        print datetime.datetime.now()

    print "打死不再改数据了！"

    c.close()



if __name__ == '__main__':
    temp1()