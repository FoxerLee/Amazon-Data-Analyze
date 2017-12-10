# -*- coding: utf-8 -*-
import re

# remove dislocation data
dislocationPattern = re.compile(r': (.*)')

# get fields from reviews
productIdPattern = re.compile(r'product/productId: (.*)')
userIdPattern = re.compile(r'review/userId: (.*)')
profileNameParttern = re.compile(r'review/profileName: (.*)')
helpfulnessParttern = re.compile(r'review/helpfulness: (.*)')
scoreParttern = re.compile(r'review/score: (.*)')
timeParttern = re.compile(r'review/time: (.*)')
summaryParttern = re.compile(r'review/summary: (.*)')
textParttern = re.compile(r'review/text: (.*)')

# Amazon API
ACCESS_KEY = 'AKIAITZTZQCEI75YF6JQ'
SECRET_KEY = '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'
USER_NAME = 'foxerlee-20'

ID_LIST = '1'