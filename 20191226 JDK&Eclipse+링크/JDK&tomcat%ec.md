## JDK1.8.0 설치하기

> 1. JDK.tar.gz 파일을 Linux 다운로드 파일에 놓겨 놓는다
> 2. tar xvf [JDK] 해서 압축을 풀어준다
> 3. 받은 파일을 cp /etc 명령어를 이용해서 /etc 밑으로 옮겨준다
> 4. 그후 cd /usr/bin 명령어를 통해 bin위치에 가서 심볼릭 링크를 생성해준다 ln -s /etc /[JDK] /bin /java java    이렇게 생성해주면 /etc/[JDK]/bin 여기 밑에 JDK를 실행시켜주는 java라는 파일이 있는데 심볼릭링크를 걸어줌으로 어디서든 java라는 명령어를 통해 실행할수 있도록 설정해준다(참고로 확인은 java -version 으로 확인 가능하다)

## 이클립스 설치하기

> 1. eclipse 파일을 Linux 다운로드 파일에 놓겨 놓는다
> 2. 압축을 풀어준다
> 3. 받은 파일을 cp /etc 명령어를 이용해서 /etc 밑으로 옮겨준다
> 4. 그후 cd /usr/bin 명령어를 통해 bin위치에 가서 심볼릭 링크를 생성해준다 ln -s /etc/eclipse/eclipse ec
>
> ![이클립스 설치](D:\20191226\이클립스 설치.PNG)

## tomcat install

> 1.  파일을 Linux 다운로드 파일에 놓겨 놓는다
> 2. 압축을 풀어준다
> 3. 받은 파일을 cp /etc 명령어를 이용해서 /etc 밑으로 옮겨준다
> 4. tomcat 파일안에 conf파일 안에 sercer.xml 이라는 파일이 있는데 여기에 들어가서 포트 번호를 사용자에 맞추어줘야된다 (우리는 80으로 설정했다)![tomcat server change](D:\20191226\tomcat server change.PNG)
> 5. 그리고 tomcat폴더에서 bin이라는 폴더를 들어가보면 tomcat서버를 start,stop할수있는 sh파일 이있는데 이파일을 심볼릭 링크를 걸어줌으로써 어디서든 서버를 start stop 할수있게 설정해보자![bin](D:\20191226\bin.PNG)
> 6. 동일하게 /usr/bin 에 가서 심볼릭 링크를 걸어주면 어디서든 실행할수 있다![tomcat install](D:\20191226\tomcat install.PNG)





