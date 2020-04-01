package com.edu.manger.service;

import com.edu.manger.constants.RestResponse;
import com.edu.manger.entry.Tran;

import java.util.List;


public interface TranService {


    public RestResponse save(Tran tran);

    public List<Tran> findList(Tran tran);

    public Tran get(Integer id);

    public RestResponse update(Tran tran);

    public RestResponse delete(Integer id);
}
