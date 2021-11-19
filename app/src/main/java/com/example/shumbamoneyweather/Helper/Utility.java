package com.example.shumbamoneyweather.Helper;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utility {

    public  String [] formatTemperature(Double tempKelvins){

        Double tempCelsius = tempKelvins-273;
        String strtempCelsius = "21°C";
        String tempCondition= "Hot";
        if(tempCelsius>25){

            strtempCelsius =String.format("%.0f",tempCelsius)+"°C";
            tempCondition = "Hot";

        }else if(tempCelsius<10){
            strtempCelsius =  String.format("%.0f",tempCelsius)+"°C";
            tempCondition = "Cold";
            

    }else {
            strtempCelsius =  String.format("%.0f",tempCelsius)+"°C";
            tempCondition ="Cool";
        }
String [] tempArr = new String[2];
        tempArr[0]= strtempCelsius;
        tempArr[1]= tempCondition;


        return tempArr;
    }
    public String [] formatWind(Double windSpeed, Integer windDeg){
        String windFormat= "North East 9km/h";
        String formatedwindSpeed = "9kmh";
        String windDirection= "North East";
        formatedwindSpeed = String.format("%.0f",(windSpeed*3.6));
//                String.valueOf(windSpeed*3.6);

        if (windDeg >= 337 || windDeg < 22) {
            windDirection = "North";
        } else if (windDeg >= 22 && windDeg < 67) {
            windDirection = "North East";
        } else if (windDeg >= 67 && windDeg < 112) {
            windDirection = "East";
        } else if (windDeg >= 112 && windDeg < 157) {
            windDirection = "South East";
        } else if (windDeg >= 157 && windDeg < 202) {
            windDirection = "South";
        } else if (windDeg >= 202 && windDeg < 247) {
            windDirection = "South West";
        } else if (windDeg >= 247 && windDeg < 292) {
            windDirection = "West";
        } else if (windDeg >= 292 && windDeg < 337) {
            windDirection = "North West";
        }
        windFormat= windDirection+" "+formatedwindSpeed+"km/h";
        String [] windArr = new String[3];
        windArr[0]= windDirection;
        windArr[1]= formatedwindSpeed;
        windArr[2]=windFormat;

    return windArr;
    }

    public String [] formatTemp(Double tempMax, Double tempMin){
        String maxMinTemp = "Max 25° Min 20°";
        tempMax= tempMax-273;
        tempMin=tempMin-273;

        maxMinTemp= new StringBuilder().append("Wind: Max ").append(String.format("%.0f", tempMax)).append("°").append(" ").append("Min").append(" ").append(String.format("%.0f", tempMin)).append("°").toString();

        String [] minMaxtempArr = new String[3];
        minMaxtempArr[0]= String.format("%.0f",tempMax);
        minMaxtempArr[1]= String.format("%.0f",tempMin);
        minMaxtempArr[2]= maxMinTemp;
        return minMaxtempArr;
    }

    public  String []formatDate(long date){
        String fomartedDate = "18 Nov 2021";
        String fomartTime = "14:21";
        fomartedDate= new SimpleDateFormat("EEE d MMM yyy", Locale.US)
                .format(new Date(date*1000));
        fomartTime=new SimpleDateFormat("H:mm a")
                .format(new Date(date*1000));
        String [] dateArr = new String[2];
        dateArr[0]= fomartedDate;
        dateArr[1]= fomartTime;

        return dateArr;
    }
}
