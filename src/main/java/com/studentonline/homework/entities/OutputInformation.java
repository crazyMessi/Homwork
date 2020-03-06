package com.studentonline.homework.entities;


/**
 * @author 19892
 */
public class OutputInformation<T> {
    private int code=0;
    private String message="success";
    private T data;

    public void setCode(int code){this.code=code;}

    public int getCode(){return code;}

    public void setMessage(String message){this.message=message;}

    public String getMessage(){return message;}

    public void setData(T data){this.data=data;}

    public T getData(){return data;}

    public static OutputInformation of(int code){
        com.studentonline.homework.entities.OutputInformation outPutInformation=new com.studentonline.homework.entities.OutputInformation();
        outPutInformation.setCode(code);
        switch (code){
            case 1:outPutInformation.setMessage("成功！");
                break;
            case -1:outPutInformation.setMessage("登陆状态错误或密码错误");
                break;
            default:break;
        }
        return outPutInformation;
    }
}

