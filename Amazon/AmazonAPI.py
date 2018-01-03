# -*- coding: utf-8 -*-
from amazon.api import AmazonAPI, LookupException, AsinNotFound
from Support import *
import mysql.connector
import datetime
import csv
import sys
import time
import signal
from multiprocessing import Process
reload(sys)
sys.setdefaultencoding('utf-8')


# mysql 不知道为什么会断连，现在全部返工，改成csv2csv
# 找到bug了，改成用函数超时来控制api接口的异常，抛不出异常直接卡死
def getId(ID_LIST):
    config = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'warehouse',
              'charset': 'utf8'}
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor()
    sql = 'SELECT * FROM id_' + ID_LIST
    cursor.execute(sql)
    # csvfile = open('prepare/' + ID_LIST + '.csv', 'r')
    # reader = csv.reader(csvfile)

    # column = [row[0] for row in reader]
    ids = []
    for id in cursor:
        id = str(id[0])
        # print type(id)
        ids.append(id)
        print id

    cursor.close()
    conn.close()
    return ids

# def getProduct(id, amazon, ID_LIST):
#     time.sleep(0.5)
#     try:
#         product = amazon.lookup(ItemId=id)
#
#     except Exception:



def run(ID_LIST,ACCESS_KEY, SECRET_KEY, USER_NAME):
    def handler(signum, frame):
        raise AssertionError

    start_time = datetime.datetime.now()
    amazon = AmazonAPI(ACCESS_KEY, SECRET_KEY, USER_NAME)
    ids = getId(ID_LIST)
    # conn = None
    # cursor = None
    #
    config = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'warehouse',
              'charset': 'utf8'}
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor()

    # csvfile = open('error/error_id.csv', 'w')
    # writer = csv.writer(csvfile)
    # writer.writerow(['ProductId'])

    result = []
    # # error_ids = []
    # count = 0

    sql = "INSERT INTO product VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"

    # cw = open('result/' + ID_LIST + '.csv', 'w')
    # writer = csv.writer(cw)

    for id in ids:
        cursor.execute("DELETE FROM id_" + ID_LIST + " WHERE product_id = \'" + id + "\'")
        time.sleep(1)
        #
        # if count == 100:
        #     writer.writerows(result)
        #     cw.flush()
        #     result = []
        #     print 'Write Succeed!'
        #     count = 0
        #     cursor.executemany(sql, result)
        #     cursor.execute("Commit;")
        #     result = []
        #     # error_ids = []
        #     count = 0
        #     cursor.close()
        #     conn.close()
        #     print 'Commit Succeed!'
        #     t
        #     conn = mysql.connector.connect(**config)
        #     cursor = conn.cursor()
        #     print 'Reconnect Succeed!'
        try:
            signal.signal(signal.SIGALRM, handler)
            signal.alarm(10)
            product = amazon.lookup(ItemId=id)

        except AssertionError:
            print 'timeout!' + id
            print 'Error1 '
            cursor.execute("INSERT INTO error VALUE (\'" + id + "\');")
            cursor.execute("Commit;")
            continue
        except AsinNotFound:
            print 'No ASIN found! '
            print 'Error2 ' + id
            cursor.execute("INSERT INTO no_page VALUE (\'" + id + "\');")
            cursor.execute("Commit;")
            continue
        except Exception, e:
            print e
            print 'Error2 ' + id
            cursor.execute("INSERT INTO error VALUE (\'" + id + "\');")
            cursor.execute("Commit;")
            continue
            # except LookupException, e:
            #     print 'Connect error ' + id + e
            #     writer.writerow((id,))
            #     csvfile.flush()
            #     continue
        finally:
            signal.alarm(0)
        try:
            title = product.title
            actors = ""
            if product.actors != None or product.actors != "":
                for i in product.actors:
                    actors += i + ","
                actors = actors[:-1]

            directors = ""
            if product.directors != None or product.directors != "":
                for i in product.directors:
                    directors += i + ","
                directors = directors[:-1]

            sales_rank = product.sales_rank
            price = product.formatted_price
            if price != None:
                price = float(price[1:])

            # 拿第2类HTML去补
            genre = product.genre

            languages = ""
            l = product.languages
            if l != None or l != "":
                for i in range(len(l)):
                    languages += l.pop() + ","
                languages = languages[:-1]

            binding = product.binding
            studio = product.studio

            # print product.edition
            # print product.brand
            # ??? 这个拿不到吧
            # print type(product.browse_nodes)
            # print type(product.browse_nodes[0])
            # print str(product.browse_nodes[0])
            # 不要
            # print len(product.browse_nodes)
            # for i in range(len(product.browse_nodes)):
            #     print product.browse_nodes[i].name
            browse_node = str(product.browse_nodes[-1].name)
            running_time = product.running_time
            publication_date = product.publication_date
            release_date = product.release_date

            # sql = "INSERT INTO product VALUES(\'" + id + "\',\'" + title + "\',\'" + actors + "\',\'" + directors + "\'," + sales_rank + "," + price + ",\'" + genre + "\',\'" + languages + "\',\'" + binding + "\',\'" + studio + "\',\'" + browse_node + "\',\'"+ running_time + "\',\'" + publication_date + "\',\'" + release_date + "\');"
            result.append((id, title, actors, directors, sales_rank, price, genre, languages, binding, studio, browse_node, running_time, publication_date, release_date))
            # count += 1
            # cursor.execute(sql)
            cursor.execute(sql, result[0])
            cursor.execute("Commit;")
            result = []

            print 'Succeed ' + id
            print datetime.datetime.now()
        except Exception, e:
            # writer.writerow((id,))
            # csvfile.flush()
            # writer.flush()
            result = []
            cursor.execute("INSERT INTO error VALUE (\'" + id + "\');")
            cursor.execute("Commit;")
            print e
            print 'Error3 ' + id


    # cursor.executemany(sql, result)
    # cursor.execute("Commit;")
    cursor.close()
    conn.close()

    # csvfile.close()
    print datetime.datetime.now() - start_time


if __name__ == '__main__':
    # run('1')
    p1 = Process(target=run, args=('1', ACCESS_KEY, SECRET_KEY, USER_NAME))
    # p2 = Process(target=run, args=('2', ACCESS_KEY_2, SECRET_KEY_2, USER_NAME_2))
   #  p3 = Process(target=run, args=('3', ACCESS_KEY_1, SECRET_KEY_1, USER_NAME_1))
    # p4 = Process(target=run, args=('4', ACCESS_KEY_3, SECRET_KEY_3, USER_NAME_3))
    #p5 = Process(target=run, args=('5', ACCESS_KEY_2, SECRET_KEY_2, USER_NAME_2))
    p1.start()
    # p2.start()
    # p3.start()
    # p4.start()
    #p5.start()

