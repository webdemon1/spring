package com.alibaba.demon.databus;

/**
 * @author: Demon
 * @create: 2019-04-28
 **/
public class AbstractDataType implements DataType {

    private DataBus dataBus;

    @Override
    public DataBus getDataBus() {
        return dataBus;
    }

    @Override
    public void setDataBus(DataBus dataBus) {
this.dataBus = dataBus;
    }
}
