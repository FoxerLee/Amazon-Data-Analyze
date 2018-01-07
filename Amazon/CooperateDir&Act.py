# -*- coding: utf-8 -*-
import csv
import datetime

import sys
reload(sys)
sys.setdefaultencoding('utf-8')


def run():
    csvFile = open("re_director.csv", "rb")
    reader = csv.reader(csvFile)
    ids = [row for row in reader]

    csvFile = open("warehouse_actor.csv", "rb")
    reader = csv.reader(csvFile)
    datas = [row for row in reader]



    new_ids = []
    for id in ids:
        name = id[0]
        movies = id[2].split(',')

        new_ids.append([name, movies])
    # print new_ids[0]

    result = []
    c = open("d2a.csv", "ab")
    w = csv.writer(c)
    for new_id in new_ids:
        n = new_id[0]
        movies = new_id[1]
        temp = []

        for movie in movies:
            for data in datas:
                if data[1] == movie:
                    temp.append(data[0])

        # result.append([n, temp])
        tempSet = set(temp)

        name1 = ''
        count1 = 0
        name2 = ''
        count2 = 0
        for item in tempSet:
            # temp_result.append((item, temp.count(item)))
            # w.writerow((n, item, temp.count(item)))
            # print n
            # print item
            # print datetime.datetime.now()
            count = temp.count(item)
            if count >= count2:
                count2 = count
                name2 = item
            elif count >= count1:
                count1 = count
                name1 = item
        w.writerow((n, name1, count1))
        w.writerow((n, name2, count2))


        print n
        print datetime.datetime.now()

    c.close()




if __name__ == "__main__":
    run()