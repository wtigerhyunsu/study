> CREATE TABLE airline_delay(
> Year INT,
> MONTH INT,
> DayofMonth INT,
> DayofWeek INT,
> DepTime INT,
> CRSDepTime INT,
> ArrTime INT,
> CRSArrTime INT,
> UniqueCarrier STRING,
> FlightNum INT,
> TailNum STRING,
> ActualElapsedTime INT,
> CRSElapsedTime INT,
> AirTime INT,
> ArrDelay INT,
> DepDelay INT,
> Origin STRING,
> Dest STRING,
> Distance INT,
> TaxiIn INT,
> TaxiOut INT,
> Cancelled INT,
> CancellationCode STRING
> COMMENT 'A = carrier, B = weather, C = NAS, D = security',
> Diverted INT COMMENT '1 = yes, 0 = no',
> CarrierDelay STRING,
> WeatherDelay STRING,
> NASDelay STRING,
> SecurityDelay STRING,
> LateAircraftDelay STRING)
> COMMENT 'TEST DATA'
> PARTITIONED BY (delayYear INT)
> ROW FORMAT DELIMITED
>     FIELDS TERMINATED BY ','
>     LINES TERMINATED BY '\n'
>     STORED AS TEXTFILE;

데이블 만들기 show tables; 내가 만든 테이블을 확인할수 있고

describe[테이블명 ]하면 구조를 볼쉬있다

drop table [테이블 이름 ] 테이블을 삭제함>> 모든 데이터가 삭제된

파일 넣는 문구

넣기 전에 파일에 

> sed -e '1d' 2008.csv > 2008_new.csv 이런 식



LOAD DATA LOCAL INPATH '/root/down/2008_new.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear=2008);

LOAD DATA LOCAL INPATH '/root/down/2007_new.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear=2007);

LOAD DATA LOCAL INPATH '/root/down/2006_new.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear=2006);

![파일 넣는 문장](C:\Users\student\study\20200110\파일 넣는 문장.PNG)

 SELECT year, month, deptime, arrtime,flightnum FROM airline_delay WHERE delayYear =2006 LIMIT 10;

> partiton 을 2006 으로 설정을 했기 떄문에 2006파일에서 만 데이터를 가져온다 좋은거 같다 (월래는 저파일 3개가 함쳐져서 저렇게 가져오기 힘들었을꺼 같은데 간단하게 가져올수있다는게 좋은점 같다)

SELECT month,AVG(depdelay), AVG(arrdelay) FROM airline_delay WHERE delayYear=2006 GROUP BY month;

> sql문 공부 다시 하자







