package com.msb.service;


import com.msb.bean.TblUserRecord;
import com.msb.mapper.TblUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;

    public TblUserRecord login(String username, String password){
        System.out.println("login service ...");
        return tblUserRecordMapper.login(username,password);
    }
}
