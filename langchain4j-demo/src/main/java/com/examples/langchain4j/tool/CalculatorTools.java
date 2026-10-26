package com.examples.langchain4j.tool;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

public class CalculatorTools {

    @Tool("计算两个整数之和")
    public int add(@P("第一个加数") int a, @P("第二个加数") int b) {
        System.out.println("  [工具被调用] add(" + a + ", " + b + ")");
        return a + b;
    }

    @Tool("查询指定城市的当前天气")
    public String weather(@P("城市名称") String city) {
        System.out.println("  [工具被调用] weather(" + city + ")");
        return city + "：晴，22°C";
    }
}
