package fr.univtln.m1dapm.g3.g3vote.Communication;

/**
 * Created by Ookami on 18/05/2015.
 */
public class CTaskParam {
    private CRequestTypesEnum mRequestType;
    private Object mObject;

    public CRequestTypesEnum getRequestType() {
        return mRequestType;
    }

    public Object getObject() {
        return mObject;
    }

    public CTaskParam(CRequestTypesEnum pRequestType){
        mRequestType=pRequestType;
    }

    public CTaskParam(CRequestTypesEnum pRequestType, Object pObject) {
        mRequestType=pRequestType;
        mObject=pObject;
    }
}
