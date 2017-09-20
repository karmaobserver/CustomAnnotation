package com.makaji.aleksej.customanotation.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.makaji.aleksej.customanotation.Pojo.Animal;
import com.makaji.aleksej.customanotation.Pojo.Car;
import com.makaji.aleksej.customanotation.Pojo.Human;
import com.makaji.aleksej.customanotation.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instance objects
        Animal animals = new Animal("Garfild", "Bubi");
        Human human = new Human("Pera", "Peric", animals);
        Car car = new Car("BMW");

        //Show human object as string
        TextView textView = findViewById(R.id.textView);
        textView.setText(human.toString());

        //Show car object as string
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(car.toStringCustom());

        //Show animal object as string
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText(animals.toString());

    }
}
