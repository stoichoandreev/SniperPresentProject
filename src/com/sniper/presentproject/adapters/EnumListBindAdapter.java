package com.sniper.presentproject.adapters;

// Source https://github.com/yqritc/RecyclerView-MultipleViewTypesAdapter


import com.sniper.presentproject.adapters.binders.DataBinder;

/**
 * Created by sniper on 4/26/15.
 */
public abstract class EnumListBindAdapter<E extends Enum<E>> extends ListBindAdapter {

    public <T extends DataBinder> T getDataBinder(E e) {
        return getDataBinder(e.ordinal());
    }
}
