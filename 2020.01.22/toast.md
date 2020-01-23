## toast

```java
 public void bt2(View v){
    //나는 새로운 화면을 만들껀데 toastlayout 을 이용할꺼야ViewGroup가 판때기야
     LayoutInflater inflater = getLayoutInflater();
     View layout =inflater.inflate(
             R.layout.toastlayout,(ViewGroup) findViewById(R.id.toast_linear)
     );
    TextView mytext=
            layout.findViewById(R.id.textView);

    mytext.setText("mytoast");

 Toast t = new Toast(this);
 t.setDuration(Toast.LENGTH_LONG);
// t.setGravity(Gravity.CENTER,0,0);
 t.setView(layout);
 t.show();

 }
```