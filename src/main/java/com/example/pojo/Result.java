package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//上面三个都是由Lombok提供的注解，用于简化Java代码的编写 lombok是一个Java库，旨在提供注解来减少样板代码的编写
@Data               // 生成get和set方法，以及equals、hashCode、toString方法
@NoArgsConstructor  //无参构造器
@AllArgsConstructor //全参构造器
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    //结果类需要几个方法： 因为我们做的是增删改查的业务，增删改不需要返回数据，查需要数据
    //成功
    public static Result success(){
        return new Result(1,"success",null);
    }
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //失败
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
