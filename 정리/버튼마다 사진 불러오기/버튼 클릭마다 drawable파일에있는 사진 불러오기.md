

![사진 바꾸기](C:\Users\student\Desktop\정리\사진 바꾸기.PNG)

```java
public void ck(View v) {
    if (v.getId() == R.id.button3) {
        iv.setImageResource(R.drawable.m2);
    } else if (v.getId() == R.id.button4) {

        iv.setImageResource(R.drawable.m4);
    } else if (v.getId() == R.id.button5) {
        iv.setImageResource(R.drawable.q2);
    } else if (v.getId() == R.id.button6) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }
}
```

> 각각 버튼을 눌렀을때 다른 사진이 올라오는 app 
>
> ImageView 핸들링하기위해 변수 선언과 findView()를 해준다음에 진행 했다 ck 라는 함수를 만들어 버튼에 onclick 설정을 ck로 설정한다 
>
> if 문을 사용해서 조건을 버튼 id로 설정해준다 버튼이 눌리면 drawable 파일에 저장되어있는 사진이 올라온다 모든 파일은 R밑에 있기 떄문에 R.으로 해결할수있다 불러오는 코드 ```setImageResource```  기억하자
>
> intent 내가 사용하자는 행위를 말한다 변수를 만들어서 new Intent(현재화면에서, 하고싶은일 )이렇식으로 설정해줄수있다 intent를 사용 했으면    startActivity()로 마무리 해야한다



