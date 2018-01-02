# -*- coding:utf-8 -*-
import sys
import csv
import mysql.connector

reload(sys)  # reload 才能调用 setdefaultencoding 方法
sys.setdefaultencoding('utf-8')  # 设置 'utf-8'

def run():
    config = {'host':'10.60.42.201','user':'root', 'password':'123456', 'port':13142 , 'database':'warehouse', 'charset':'utf8'}
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor()

    csvfile = open('productId_0.csv', 'r')
    reader = csv.reader(csvfile)

    column = [row[0] for row in reader]

    # print type(column)
    # print column[0]
    # print type(column[0])
    #
    column = column[1:]
    # ids = []
    # i = 0
    # count = 0
    # for id in column:
    #     if count == 10000:
    #         c = open('prepare/' + str(i) + '.csv', 'w')
    #         w = csv.writer(c)
    #         w.writerows(ids)
    #         c.close()
    #         ids = []
    #         count = 0
    #         i += 1
    #         print 'Succeed ' + str(i)
    #     ids.append((id,))
    #     count += 1
    #
    # c = open('prepare/' + str(i) + '.csv', 'w')
    # w = csv.writer(c)
    # w.writerows(ids)
    #
    # csvfile.close()
    # for i in range(len(column)):
    #     ids.append((column[i],))
    count = 0
    # j = 0
    for i in range(len(column)):
        try:
            count += 1
            sql = "INSERT INTO id VALUE ('"
            sql += column[i] + "');"
            cursor.execute(sql)
            print column[i]

            # if (count/50000 == j and count < 250000):
            #     j += 1

        except:
            print "error " + str(count) + column[i]
            cursor.close()
            conn.close()
            break
    cursor.execute("Commit;")
    # 一次性插入会出现奇怪的bug，找不到，气死了
    # 找到了，要把list里面的数据转成tuple，辣鸡
    # 但是为了分批次，还是一个一个插吧，md
    # sql = "INSERT INTO product_id VALUE (%s)"
    # cursor.executemany(sql, ids)
    # cursor.execute("Commit;")
    # conn.close()
    # cursor.close()



if __name__ == '__main__':
    run()



