package com.czl.xyyq;

import com.czl.xyyq.constant.UserConstant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class XyyqMsBackendApplicationTests {

    @Test
    void generatePassword() {
        String encryptPassword = DigestUtils.md5DigestAsHex((UserConstant.SALT + "123456").getBytes());
        System.out.println(encryptPassword);
    }

    @Test
    public void test01(){
        String s = "1111111";
        System.out.println(Integer.parseInt(s));
    }
    @Test
    public void test02(){
        int i =1;
        boolean result = i==1  &&(i==0||i==1);
        System.out.println(result);
    }


}
