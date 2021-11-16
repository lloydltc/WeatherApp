package com.example.shumbamoneyweather.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shumbamoneyweather.Model.ForecastList;
import com.example.shumbamoneyweather.R;
import com.example.shumbamoneyweather.View.Activity.MainActivity;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    Context context;
    ArrayList<ForecastList> forecastLists;
    public WeatherAdapter(Context context,ArrayList<ForecastList> forecastLists){
        this.context =context;
        this.forecastLists =forecastLists;
    }

    @NonNull
    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_forecast, parent, false);
        return new WeatherViewHolder(view);
   
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherViewHolder holder, int position) {

        holder.tvDate.setText(forecastLists.get(position).getDtTxt());
        Double a = forecastLists.get(position).getMain().getTemp();
        Double b = a-273;
        if(b>25){

            holder.tvDesCription.setText("Hot"+ String.format("%.2f",b));
        }else{

        holder.tvDesCription.setText("cold"+String.format("%.2f",b)) ;}
    }

    @Override
    public int getItemCount() {
        return forecastLists.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvDesCription;
        Button detailsbtn;
        public WeatherViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.forecast_txtv_date);
            tvDesCription = itemView.findViewById(R.id.forecast_temp);
            detailsbtn = itemView.findViewById(R.id.btn_details);
            detailsbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ((MainActivity) context).viewDetails(getAdapterPosition());
                }
            });
        }

    }
}
