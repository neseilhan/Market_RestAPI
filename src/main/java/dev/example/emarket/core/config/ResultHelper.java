package dev.example.emarket.core.config;

public class ResultHelper {

    public static <T> ResultData<T> created(T data){
        return new ResultData<>("201", Msg.CREATED,true , data);
    }
    public static <T> ResultData<T> validateError(T data){
        return new ResultData<>("400", Msg.VALIDATE_ERROR, false, data );
    }
    public static <T> ResultData<T> success(T data){
        return new ResultData<>("200", Msg.OK, true, data );
    }

    public static Result notFoundError(String msg){
        return new Result("404", msg, false );
    }
}
