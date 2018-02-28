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
ACCESS_KEY = ''
SECRET_KEY = ''
USER_NAME = ''

ACCESS_KEY_1 = ''
SECRET_KEY_1 = ''
USER_NAME_1 = ''

ACCESS_KEY_2 = ''
SECRET_KEY_2 = ''
USER_NAME_2 = ''

ACCESS_KEY_3 = ''
SECRET_KEY_3 = ''
USER_NAME_3 = ''