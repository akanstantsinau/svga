package com.vivarium.svga.model;

/**
 * Created by neurons on 8/9/14.
 */
public class Unit {
    private float value;
    private UnitType type;
    public Unit(float v, UnitType t){
        value=v;
        type=t;
    }
    public Unit(){
        type=UnitType.ZERO;
        value=0.f;
    }
    public Unit(float v){
        value=v;
        type=UnitType.SCALAR;
    }

    public void addMul(Unit a, Unit b){
        UnitType resultType;
        if(a.type==UnitType.ZERO || a.type == UnitType.SCALAR || a.type==b.type){
            resultType=b.type;
        }else if(b.type == UnitType.ZERO || b.type == UnitType.SCALAR){
            resultType = a.type;
        }else{
            throw new IllegalArgumentException(
                    String.format("Unable to multiply different non-scalar types: %s and %s",
                            a.type.name(),b.type.name()) );
        }

        if(type == UnitType.ZERO){
            type = resultType;
        }

        if(type != resultType){
            throw new IllegalArgumentException(
                    String.format("Unable to add different types: %s and %s",
                            type.name(),resultType.name()) );
        }

        value = value + a.value * b.value;
    }
}
