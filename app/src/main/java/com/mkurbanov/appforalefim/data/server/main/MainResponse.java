package com.mkurbanov.appforalefim.data.server.main;

import com.mkurbanov.appforalefim.interfaces.itemTypes;

import java.util.ArrayList;
import java.util.List;

public class MainResponse implements itemTypes {
    public String image;

    @Override
    public int getTypeItem() {
        return itemTypes.TYPE_ITEM;
    }
}
