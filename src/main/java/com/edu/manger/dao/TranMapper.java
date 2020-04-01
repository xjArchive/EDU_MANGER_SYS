package com.edu.manger.dao;

import com.edu.manger.entry.Tran;

import java.util.List;

public interface TranMapper {

    int insert(Tran tran);
    List<Tran> findList(Tran tran);
    Tran get(Integer id);

    int update(Tran tran);

    int delete(Integer tran);


}
