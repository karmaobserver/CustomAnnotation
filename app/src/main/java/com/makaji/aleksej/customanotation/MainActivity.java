package com.makaji.aleksej.customanotation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animal animals = new Animal("Garfild", "Bubi");
        Human human = new Human("Pera", "Peric");
        Car car = new Car("BMW");

        StringUtil.getcat(animals);

        TextView textView = findViewById(R.id.textView);
        textView.setText(human.toString());

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(car.toStringCustom());

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText( StringUtil.getcat(animals));

    }
}
