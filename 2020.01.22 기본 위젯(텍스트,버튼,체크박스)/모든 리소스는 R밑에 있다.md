모든 리소스는 R밑에 있다

- INTENT

  `Intent {변수이름} = new Intent(Intent.ACTION_VIEW, Uri.parse("{ex. htttp:,tel:}"))`

  `startActivity({변수 이름})`

  하고자 하는 행위를 Uri.parse("") 안에 써주면 (http:,tel:) 하고자 하는 플랫폼을 사용할수 있게 해준다 

  

  

  ##체크리스트 다시 띠우기 

  ``

  ```java
  package com.example.a170p;
  
  import androidx.appcompat.app.AppCompatActivity;
  
  import android.os.Bundle;
  import android.view.View;
  import android.widget.CheckBox;
  import android.widget.RadioButton;
  import android.widget.RadioGroup;
  import android.widget.Switch;
  import android.widget.TextView;
  import android.widget.Toast;
  import android.widget.ToggleButton;
  
  import org.w3c.dom.Text;
  
  public class MainActivity extends AppCompatActivity {
      Switch aSwitch;
      ToggleButton toggleButton;
      CheckBox checkBox,checkBox2;
      RadioButton radioButton,radioButton2;
      RadioGroup radioGroup;
      String sex;
      TextView textView;
      ;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          aSwitch = findViewById(R.id.switch1);
          toggleButton = findViewById(R.id.toggleButton);
          checkBox =findViewById(R.id.checkBox);
          checkBox2 =findViewById(R.id.checkBox2);
          radioButton = findViewById(R.id.radioButton);
          radioButton = findViewById(R.id.radioButton);
          radioGroup = findViewById(R.id.RadioGroup);
          textView = findViewById(R.id.textView2);
          radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if(checkedId == R.id.radioButton){
                     sex = "Female";
                 }else {
                     sex = "Male";
                 }
  
              }
          });
      }
      public void sck(View v){
      if(v.getId()==R.id.switch1){
          if(aSwitch.isChecked() ==true){
              Toast.makeText(this, "활성",Toast.LENGTH_SHORT).show();
          }else if (aSwitch.isChecked() ==false){
              Toast.makeText(this, "비활성",Toast.LENGTH_SHORT).show();
          }
                  }
      }
  
      public void btck(View v){
          String result = "";
          if(toggleButton.isChecked()==true){
              result +="Checked Toggle";
          }else {
              result +="UnChecked Toggle";
  
          }
          if(checkBox.isChecked()== true){
              result += ",Checked Beef";
      }
          if(checkBox2.isChecked()== true){
          result += ",Checked cheese";
      }
      result +=","+sex;
  
      textView.setText(result);
      }
  }
  ```
