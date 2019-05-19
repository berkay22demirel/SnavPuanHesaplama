package com.berkay22demirel.sinavpuanhesaplama.Interface;

import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;

import java.util.List;

public interface BaseDAO {

    public Long put(Object data);

    public Object get(Long examID);

    public Long delete(Long examID);

    public List<Object> getAll() throws DatabaseException;
}
