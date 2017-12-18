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
ACCESS_KEY = 'AKIAIDAPDDSEJWGVZVWA'
SECRET_KEY = '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'
USER_NAME = 'molly046-20'

ACCESS_KEY_1 = 'AKIAJOC6LA7TSGMMWLKA'
SECRET_KEY_1 = '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'
USER_NAME_1 = 'wukefei1024-20'

ACCESS_KEY_2 = 'AKIAITZTZQCEI75YF6JQ'
SECRET_KEY_2 = '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'
USER_NAME_2 = 'foxerlee-20'

ACCESS_KEY_3 = 'AKIAICMPO6E4LTJKJU4A'
SECRET_KEY_3 = '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'
USER_NAME_3 = 'nothing04d-20'