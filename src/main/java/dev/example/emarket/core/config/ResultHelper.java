package dev.example.emarket.core.config;

public class ResultHelper {

    public static <T> ResultData<T> created(T data){
        return new ResultData<>("201", Msg.CREATED,true , data);
    }
}
