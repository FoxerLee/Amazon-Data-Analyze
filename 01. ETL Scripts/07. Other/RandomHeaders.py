import ImNotSpider

def getUserAgent():
    ins = ImNotSpider.ImNotSpider()
    return ins.random()


def getHeaders():

    headers = {
        'Host':'www.amazon.com',
        'User-Agent':getUserAgent(),
    }

    return headers;