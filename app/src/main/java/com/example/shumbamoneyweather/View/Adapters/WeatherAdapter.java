package com.example.shumbamoneyweather.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shumbamoneyweather.Helper.Utility;
import com.example.shumbamoneyweather.Model.ForecastList;
import com.example.shumbamoneyweather.R;
import com.example.shumbamoneyweather.View.Activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    Context context;
    ArrayList<ForecastList> forecastLists;

    public WeatherAdapter(Context context, ArrayList<ForecastList> forecastLists) {
        this.context = context;
        this.forecastLists = forecastLists;
    }

    @NonNull
    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_forecast, parent, false);
        return new WeatherViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherViewHolder holder, int position) {
        //        Load image using picasso Library
        String icon = forecastLists.get(position).getWeather().get(0).getIcon();
        String iconUrl = "http://openweathermap.org/img/wn/" + icon + "@2x.png";
        Picasso.get().load(iconUrl).into(holder.iconImg);

//        Utility class contains helper functions
        Utility utility = new Utility();

        holder.txttemp.setText(utility.formatTemperature(forecastLists.get(position).getMain().getTemp())[0]);
        holder.tempCond.setText(utility.formatTemperature(forecastLists.get(position).getMain().getTemp())[1]);
        holder.tvDesCription.setText(forecastLists.get(position).getWeather().get(0).getMain());
//        Wind array contain 3 strings wind speed, wind direction and string which combines wind speed and wind direction
        holder.txtWind.setText(utility.formatWind(forecastLists.get(position).getWind().getSpeed(), forecastLists.get(position).getWind().getDeg())[2]);
        holder.txtvMaxMin.setText(utility.formatTemp(forecastLists.get(position).getMain().getTempMax(), forecastLists.get(position).getMain().getTempMin())[2]);
        holder.tvDate.setText(utility.formatDate(forecastLists.get(position).getDt())[0]);
        holder.txtTime.setText(utility.formatDate(forecastLists.get(position).getDt())[1]);
    }


    @Override
    public int getItemCount() {
        return forecastLists.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, txtTime, txtvMaxMin, tempCond, txtWind;
        ImageView iconImg;
        TextView tvDesCription, txttemp;
        TextView detailsbtn;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.txtvdate);
            tempCond = itemView.findViewById(R.id.forecast_txtv_temp_cond);
            txttemp = itemView.findViewById(R.id.forecast_temp);
            txtWind = itemView.findViewById(R.id.forecast_txtv_wind);
            txtTime = itemView.findViewById(R.id.txtvtime);
            tvDesCription = itemView.findViewById(R.id.forecast_txtv_weatherdesc);
            txtvMaxMin = itemView.findViewById(R.id.forecast_txtv_max_min);
            detailsbtn = itemView.findViewById(R.id.btn_details);
            iconImg = itemView.findViewById(R.id.forecast_weather_icon);
            detailsbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ((MainActivity) context).viewDetails(getAdapterPosition());
                }
            });
        }

    }
}
