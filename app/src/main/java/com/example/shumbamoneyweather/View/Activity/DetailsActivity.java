package com.example.shumbamoneyweather.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shumbamoneyweather.Helper.Utility;
import com.example.shumbamoneyweather.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView txtvDate,txtvTime,txtvRainChance,txtvTempCondition,
            txtvTemp,txtvWeather,txtvCloud,txtvMinTemp,txtvMaxTemp,
            txtvPrecipitation,txtvHumidity,txtvWindSpeed,txtvshare,
            txtvPressure,txtvWeatherDesc,txtvWindDirection;
    ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imgIcon =findViewById(R.id.weather_icon);
        txtvDate =findViewById(R.id.txtv_date);
        txtvshare =findViewById(R.id.txtv_share);
        txtvTime =findViewById(R.id.txtv_time);
        txtvTempCondition =findViewById(R.id.txtv_temp_condition);
        txtvTemp =findViewById(R.id.txt_temp);
        txtvMaxTemp =findViewById(R.id.txv_max_temp);
        txtvMinTemp =findViewById(R.id.txv_min_temp);
        txtvWeather =findViewById(R.id.txtv_weather);
        txtvHumidity =findViewById(R.id.txtv_humidity);
        txtvPrecipitation=findViewById(R.id.txtv_precipitation);
        txtvRainChance =findViewById(R.id.txtv_rainchance);
        txtvCloud =findViewById(R.id.txtv_cloud);
        txtvWindSpeed = findViewById(R.id.txtv_wind_speed);
        txtvCloud =findViewById(R.id.txtv_cloud);
        txtvWindDirection = findViewById(R.id.txtv_wind_direction);
        txtvWeatherDesc =findViewById(R.id.txv_weather_desc);
        txtvPressure = findViewById(R.id.txtv_preasure);
        Utility utility = new Utility();
        Intent i = getIntent();
        Long  longDate = i.getLongExtra("date",0);
        Double doubleHumidity= i.getDoubleExtra("humidity",0);
        Double doublePop = i.getDoubleExtra("pop",0);
        Double doubleCloud = i.getDoubleExtra("cloud",0);
        Double doubleWindSpeed = i.getDoubleExtra("windSpeed",0);
        Integer intWindDir= i.getIntExtra("windDirection",0);
        Integer intpressure = i.getIntExtra("pressure",0);
        String  weatherDesc = i.getStringExtra("weatherDesc");
        Double doubleMinTemp = i.getDoubleExtra("minTemp",0);
        Double doubleMaxTemp =i.getDoubleExtra("maxTemp",0);
        Double doubleTemp = i.getDoubleExtra("temp",0);
        String stringWeather = i.getStringExtra("weather");
        String stringIcon = i.getStringExtra("icon");
        Double doubleprecipitation = i.getDoubleExtra("rain",0);

//        Load image using picasso Library
        String iconUrl = "http://openweathermap.org/img/wn/" + stringIcon + "@2x.png";
        Picasso.get().load(iconUrl).into(imgIcon);

        String []dateTime = utility.formatDate(longDate);
        txtvDate.setText(dateTime[0]);
        txtvTime.setText(dateTime[1]);

        String humidity = new StringBuilder().append("Humidity:").append(" ").append(String.format("%.0f", doubleHumidity)).append("%").toString();
        txtvHumidity.setText(humidity);

        doublePop=doublePop*100;
        String pop = new StringBuilder().append("Chance of raining:").append(" ").append(String.format("%.0f", doublePop)).append("%").toString();
        txtvRainChance.setText(pop);

        String cloud = new StringBuilder().append("Cloud Cover:").append(" ").append(String.format("%.0f", doubleCloud)).append("%").toString();
        txtvCloud.setText(cloud);

        String [] wind = utility.formatWind(doubleWindSpeed,intWindDir);
        String windDirection = new StringBuilder().append("Wind direction:").append(" ").append(wind[0]).toString();
        txtvWindDirection.setText(windDirection);
        String windSpeed = new StringBuilder().append("Wind speed:").append(" ").append(wind[1]).append("km/h").toString();
        txtvWindSpeed.setText(windSpeed);

       String pressure= new StringBuilder().append("Pressure:").append(" ").append(String.valueOf(intpressure)).append("hpa").toString();
       txtvPressure.setText(pressure);

       txtvWeatherDesc.setText(weatherDesc);
       txtvWeather.setText(stringWeather);

       String [] minMaxTemp = utility.formatTemp(doubleMaxTemp,doubleMinTemp);
       String maxTemp =new StringBuilder().append("Maximum: ").append(" ").append(minMaxTemp[0]).toString();
       txtvMaxTemp.setText(maxTemp);
       String minTemp = new StringBuilder().append("Minimum: ").append(" ").append(minMaxTemp[1]).toString();
       txtvMinTemp.setText(minTemp);

       String precipitation = new StringBuilder().append("Precipitation:").append(" ").append(String.valueOf(doubleprecipitation)).toString();
       txtvPrecipitation.setText(precipitation);

       String [] temperature = utility.formatTemperature(doubleTemp);
       txtvTemp.setText(temperature[0]);
       String tempCondition = new StringBuilder().append("The temperature is").append(" ").append(temperature[1]).toString();
       txtvTempCondition.setText(tempCondition);

       txtvshare.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String subject,title,dialogheader,text;
               title="Share weather";
               subject="Sharing weather with friends";
               dialogheader = "Share using";
               text= new StringBuilder().append(pop).append(" ").append(cloud).append(" ").append(humidity).append(" ").append(windDirection).append(" ").append(windSpeed).append(" ")
                       .append(pressure).append(" ").append(stringWeather).append(" ").append(weatherDesc).append(" ").append(maxTemp).append(" ").append(minTemp).append(" ")
                       .append(precipitation).append(" ").append(temperature[0]).append(" ").append(tempCondition).toString();
            share(text,subject,title,dialogheader);
           }
       });


    }
    public void share(String text, String subject, String title, String dialogHeader) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_TITLE, title);
        startActivity(Intent.createChooser(intent, dialogHeader));
    }

}