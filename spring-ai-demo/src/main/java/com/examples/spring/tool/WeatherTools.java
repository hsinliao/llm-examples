package com.examples.spring.tool;

import org.springframework.ai.tool.annotation.Tool;


public class WeatherTools {

    @Tool(description = "查询指定城市的当前天气")
    String getWeather(String city) {
        return city + "：晴，22°C";
    }
}
