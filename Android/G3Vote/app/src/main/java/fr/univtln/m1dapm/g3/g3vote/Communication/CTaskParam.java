package fr.univtln.m1dapm.g3.g3vote.Communication;

/**
 * Created by Ookami on 18/05/2015.
 */
public class CTaskParam {
    private CRequestTypesEnum mRequestType;
    private Object mObject;
    private Object mType;

    public CRequestTypesEnum getRequestType() {
        return mRequestType;
    }

    public Object getObject() {
        return mObject;
    }

    public Object getType() {
        return mType;
    }

    public CTaskParam(CRequestTypesEnum pRequestType){
        mRequestType=pRequestType;
    }

    public CTaskParam(CRequestTypesEnum pRequestType, Object pObject) {
        mRequestType=pRequestType;
        mObject=pObject;
    }

    public CTaskParam(CRequestTypesEnum pRequestType, Object pObject,Object pType) {
        mRequestType=pRequestType;
        mObject=pObject;
        mType=pType;
    }
}
