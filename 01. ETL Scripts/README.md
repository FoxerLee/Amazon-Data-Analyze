## ETL脚本说明

ETL脚本共分为7个文件夹，各文件夹下脚本含义如下：

1. Handle Origin Review Data
   - 处理原始的9G评论数据，从中提取出产品的ASIN值，以及每一个评论对产品的评分。
2. Spider
   - 通过一个第三方的Amazon的[API](https://github.com/yoavaviram/python-amazon-simple-product-api)获取产品的原始数据，共有13个字段
   - 通过抓取到的Amazon原始html网页，整合得到同一部电影的各种版本的产品ASIN
3. Handle HTML Data
   - 对从API获取到的产品原始数据进行分别处理，根据电影的各个版本的产品信息，提取出电影的actor、director、binding、studio、starring、genre、title、date等字段信息
4. Connection
   - 整合获取actors与directors之间的关系，并且求得actor最喜欢的director和director最喜欢的actor
   - 获得actor与director的风格
5. Integrate To Csv
   - 将3、4部分求到的数据进行整合，合并到数据库表对应的csv文件，方便导入
6. Csv To Database
   - 分别利用kettle和sql脚本将数据导入MySQL和Hive中
7. Other
   - 数据清洗过程中的部分异常、bug处理
   - 爬虫的辅助脚本：正则表达式、随机header