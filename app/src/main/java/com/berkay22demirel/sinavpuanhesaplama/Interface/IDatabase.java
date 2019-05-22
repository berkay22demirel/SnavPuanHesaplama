package com.berkay22demirel.sinavpuanhesaplama.Interface;

public interface IDatabase {

    public Long put(Object data);

    public Object get(Long examID);

    public Long delete(Long examID);
}
