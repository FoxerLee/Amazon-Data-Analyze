# -*- coding: utf-8 -*-
from amazon.api import AmazonAPI
from Support import ACCESS_KEY, SECRET_KEY, USER_NAME, ID_LIST
import mysql.connector
import datetime
import csv
import sys
import time
reload(sys)
sys.setdefaultencoding('utf-8')

amazon = AmazonAPI(ACCESS_KEY, SECRET_KEY, USER_NAME)
product = amazon.lookup(ItemId='5559947962')
# config = {'host': '10.60.42.201', 'user': 'root', 'password': '123456', 'port': 13142, 'database': 'warehouse',
#               'charset': 'utf8'}
# conn = mysql.connector.connect(**config)
# cursor = conn.cursor()
#
# sql = 'SELECT id FROM product'
# cursor.execute(sql)
# ids = []
# for res in cursor:
#     id = res[0]
#     ids.append(id)
#     print id
#
# for id in ids:
#     sql = "INSERT INTO id_" + '1' + " VALUE (\'" + id + "\');"
#     try:
#         cursor.execute(sql)
#     except:
#         continue
#
#
#
# cursor.execute("Commit;")
# cursor.close()
# conn.close()
# 需要从HTML拿
# print product.actors
title = product.title
result = []
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
features = product.features
print features
print product.get_attribute('Rating')
print product.mpn
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
running_time = product.running_time
print product.publication_date
print product.release_date
a = str(product.browse_nodes[-1].name)
print a
print type(a)

#
# result.append(('0739033980', title, directors, sales_rank, price, genre, languages, binding, studio, running_time,
#                publication_date, release_date))
#
# cursor.execute('DELETE FROM id_' + ID_LIST + ' WHERE product_id = ' + '0739033980')
# cursor.execute('commit;')
# cursor.close()
# conn.close()
# print '0739033980'