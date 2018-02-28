# -*- coding: utf-8 -*-
import re
import os
import csv
from Support import *

import sys
reload(sys)
sys.setdefaultencoding('utf-8')

# 处理原始的txt文件
def errorData():
    fe = open('error/dislocation_error.txt', 'w')
    fn = open('new_movies.txt', 'w')
    count = 0
    tmp = ""
    with open('movies.txt', 'r') as fo:
        for line in fo:
            count += 1
            if line == '\n':
                fn.write(line)
                continue
            if len(re.findall(pattern=dislocationPattern, string=line)) == 0:
                error = "error_" + str(count)
                print error
                fe.write(error + " | " + tmp + " | " + line + "\n")
                print tmp + " | " + line

            else:
                tmp = line
                fn.write(line)
    fn.close()
    fo.close()
    fe.close()


def run():
    fe = open('error/error.txt', 'w')
    i = 1
    product = []
    products = []
    productsId = set()
    count = 0
    with open('new_movies.txt', 'r') as f:
        for line in f:
            count += 1
            if line == '\n':
                continue
            else:
                product.append(line.strip('\n'))

            if (len(product) == 8):
                try:
                    productId = re.findall(pattern=productIdPattern, string=product[0])[0].strip(' ')
                    userId = re.findall(pattern=userIdPattern, string=product[1])[0].strip(' ')

                    profileName = re.findall(pattern=profileNameParttern, string=product[2])[0]
                    # delete useless information
                    index = profileName.find("\"")
                    profileName = profileName[:index-1]

                    helpfulness = re.findall(pattern=helpfulnessParttern, string=product[3])[0].strip(' ')
                    score = re.findall(pattern=scoreParttern, string=product[4])[0].strip(' ')
                    time = re.findall(pattern=timeParttern, string=product[5])[0].strip(' ')
                    summary = re.findall(pattern=summaryParttern, string=product[6])[0]
                    text = re.findall(pattern=textParttern, string=product[7])[0]

                    product_tuple = (productId, userId, profileName, helpfulness, score, time, summary, text)

                    products.append(product_tuple)

                    product = []

                    productsId.add(productId)
                except:
                    print 'error_' + str(count)
                    fe.write('error_' + str(count))
                    fe.write(productId)
                    fe.write(userId)
                    fe.write(profileName)
                    fe.write(helpfulness)
                    fe.write(score)
                    fe.write(time)
                    fe.write(summary)
                    fe.write(text + '\n')

                    break

            if (count / 200000 == i):
                wrilteReview(products, i)
                products = []
                i += 1
                print str(count) + " reviews have been handled!"

    wrilteReview(products, i)

    print str(count) + " reviews have been handled!"
    f.close()
    fe.close()

    wrilteId(productsId, 0)


def wrilteReview(products, i):
    csvfile = open('csv/reviews_' + str(i) + '.csv', 'w')
    writer = csv.writer(csvfile)
    writer.writerow(['ProductId', 'UserId', 'ProfileName', 'Helpfulness', 'Score', 'Time', 'Summary', 'Text'])
    writer.writerows(products)


def wrilteId(productsId, j):
    productsId = list(productsId)
    csvfile = open('productId_' + str(j) + '.csv', 'w')
    writer = csv.writer(csvfile)
    writer.writerow(['ProductId'])
    for i in range(len(productsId)):
        writer.writerow([productsId[i]])
    print str(len(productsId)) + " product Id get!"
    csvfile.close()



if __name__ == '__main__':
    # errorData()
    run()