mc.war를 하나 만들어서 

바탕화면에 만든다

복사해서 리룩스 시스템에다가 넣는다(다운로드)

"vmware덕분에 복사 붙여넣기 하는데 월래는 시스템에다가 띠우고 다운받아야된다"![war 파일](D:\20191227\war 파일.PNG)

"war파일은 tomcat 진행 시키면 자동으로 풀린다"

두개의 컴퓨터로  하나는 db로 돌리고 하나는 웹으로 돌려서



다른 컴퓨터에서 들어올수있도록 꾸며보는게 오늘의 목표

"우리의 리눅스를 독립적인 컴퓨터로 설정해주기 위해 반에 있는 ip조사 했다"





240 내자리 리룩스 ip주소

### MariDB

> 하둡을 할떄 마리아 디비사용한다

아마존 서비스에다가 내 리룩스 도매인을 하나 받아서 사용하는게 요즘 트렌드



## oracle(571p)

- oracle 다운받기위한 저장공간확보

> 573p step2 중요함
>
> 4GB 필요한데 그럼 처음 설치할떄 6GB로 설피하면 되지 왜 이렇게해서 하나
>
> swap을 공부하기위해서 나중에 프젝 할떄는 6GB로 설정하고 시작
>
> swapon -s 를 쳐보면 swap이 어떻게 설정이 되어있는 지 확인할수 있다.
>
> ![swap 용략](C:\Users\student\Desktop\20191227\swap 용략.PNG)
>
> 이걸 늘리기 위해 서 작업을 진행한다
>
> dd if=/dev/zero of=/swapfile bs=1024 count=4194304>>>가상 메모리용 파일 생성 (4GB)
>
>  
>
> 이렇게 진행하고 컴퓨터를 끄면 내가 만든 스왑영역이 다시키면 끊어 진다 그렇기위해 설정을 하나해주는 설정cd /etc/ rc.d 안에 있는 rc.local 이라는 파일이 컴퓨터를 키면 초기 설정을 해주는 파일 여기에다가 설정을 한다(574p 까지 내용)

- 다운로드후 환경설정(575p)

> yum -y localinstall oracle-xe-11.2.0-1.0.x86_64.rpm  --설치 하기
>
> service oracle-xe configure--설정하는 문구 받는 명령어
>
> 엔터 2번후 암호 입력 111111  111111 두번(확인 까지 )>>y 입력 후 Enter
>
> 설치는 끝
>
> 실행하는 방법 /etc/init.d/oracle-xe startus

- 환경변수 세팅

> . /u01/app/oracle/product/11.2.0/xe/bin/oracle_env.sh
>
> 환경설정을 컴퓨터를 키자마자 세팅되게 하려면  vi /etc/bashrc
>
> 여기에 들어가서 위에 만들어준 . /u01/app/oracle/product/11.2.0/xe/bin/oracle_env.sh 이문구를 넣어주면 된다
>
> /etc/init.d/oracle-xe status >> 오라클 상태를 확인할수있는 메세지
>
> http://192.168.111.101:8080/apex/ 관리자 모드로 확인할수있음
>
> http://[ip 주소]:8080/apex/ ![ip 바꾼거](D:\20191227\ip 바꾼거.PNG)
>
> 오라클 관리자 영역은 internal
>
> id =admin, pwd=111111

- DB만들기

> 마리아 디비는 유저가 table을 만들었다 orcle은 관리자가 workspaces를 만들어주고 사용자까지 만들어 주어야 된다
>
> 앱플리케이션으로 접속할떄는 스크마의 name과 pwd로 들어가게 된다스키마가 db의 저장공간 이 되는 셈
>
> 
>
> ![스키마 만들기](C:\Users\student\Desktop\20191227\스키마 만들기.PNG)
>
> ![스키마 결과물](D:\20191227\스키마 결과물.PNG)
>
> ![스키마 만들어진거 확인](D:\20191227\스키마 만들어진거 확인.PNG)
>
> 앱플리 케이션 접근하는 사용자를 만드는게 create user 해당 스키마를 핸들링 할수있는 권한이 주어짐 
>
> 
>
> 이 유저는 스키마 안에서 작업하는 게 됩니다
>
> ![사용자 만들기](D:\20191227\사용자 만들기.PNG)
>
> ![user 생성](D:\20191227\user 생성.PNG)
>
> 
>
> ![user 결과](D:\20191227\user 결과.PNG)CREATE TABLE USERS(
>
> id VARCHAR2(10) PRIMARY KEY,
>
> pwd VARCHAR2(10),
>
> name VARCHAR2(10),
>
> age NUMBER(3)
>
> );
>
> INSERT INTO USERS VALUES('id01','pwd01','이말숙','10')
>
> INSERT INTO USERS VALUES('id02','pwd02','홍말숙','20')
>
> INSERT INTO USERS VALUES('id03','pwd03','김말숙','30')

### 브릿지

> 하나의 컴퓨터에 2개의 아이피을 할당받는 것을 말한다
>
> 
>
> 핑을 통해 ip가 사용가능 한지 확인한다 신호가 없어야 사용가능
>
> [핑]![핑](C:\Users\student\Desktop\20191227\핑.PNG)
>
> ![확인 핑](D:\20191227\확인 핑.PNG)
>
> ![ip 바꾼거](C:\Users\student\Desktop\20191227\브릿지.PNG)

> ![ip 바꾼거](C:\Users\student\Desktop\20191227\ip 바꾼거.PNG)
>
> ![확인 핑](C:\Users\student\Desktop\20191227\확인 핑.PNG)

db랑 workspaece와 분리 시킨다



> db를 세팅하고 ip를 바꾸면 db가 망가진다
>
> ### 오라클설치
>
> yum -y remove oracle-xe 오라클 삭제
>
> yum -y localinstall oracle-xe-11.2.0-1.0.x86_64.rpm--설치 하고  
>
> service oracle-xe configure--설정하는 문구 받는 명령어
>
> 이러면 관리자로 들어갈수이쓴ㄴ 지 확인
>
> http://[ip 주소]:8080/apex/ ![orcle 관리자](D:\20191227\orcle 관리자.PNG)
>
> 
>
> 
>
> 

> 



> 
>
> 