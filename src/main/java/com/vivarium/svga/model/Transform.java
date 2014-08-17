package com.vivarium.svga.model;

/**
 * Created by neurons on 8/9/14.
 */
public class Transform {
    public static final double DELTA = 0.01;
    private Unit[][] matrix;

    public Transform(Unit[][] m){
        matrix = m;
    }

    public static Transform NO_TRANSFORM = new Transform(new Unit[][]{
            {new Unit(1.0f),    new Unit(),     new Unit()},
            {new Unit(),        new Unit(1.0f), new Unit()},
            {new Unit(),        new Unit(),     new Unit(1.0f)}});

    public static Transform translate(Unit x, Unit y){
        return new Transform( new Unit[][]{
                {new Unit(1.0f),    new Unit(),     x},
                {new Unit(),        new Unit(1.0f), y},
                {new Unit(),        new Unit(),     new Unit(1.0f)}});
    }

    public static Transform scale(float x, float y){
        return new Transform(new Unit[][]{
                {new Unit(x),       new Unit(),     new Unit()},
                {new Unit(),        new Unit(y),    new Unit()},
                {new Unit(),        new Unit(),     new Unit(1.0f)}});
    }

    public static Transform rotate(float x){
        return new Transform(new Unit[][]{
                {new Unit((float)Math.cos(x)),      new Unit(-(float)Math.sin(x)),         new Unit()},
                {new Unit((float)Math.sin(x)),      new Unit((float)Math.cos(x )),         new Unit()},
                {new Unit(),                        new Unit(),                             new Unit(1.0f)}});
    }

    public static Transform skewX(float x){
        return new Transform(new Unit[][]{
                {new Unit(1),       new Unit((float)Math.tan(x)),   new Unit()},
                {new Unit(),        new Unit(1),                    new Unit()},
                {new Unit(),        new Unit(),                     new Unit(1)}});
    }

    public static Transform skewY(float x){
        return new Transform( new Unit[][]{
                {new Unit(1),                   new Unit(),   new Unit()},
                {new Unit((float)Math.tan(x)),  new Unit(1),  new Unit()},
                {new Unit(),                    new Unit(),   new Unit(1)}});
    }

    public static Transform addTransform(Transform t1, Transform t2){

        Unit[][] m = new Unit[3][3];
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                Unit u = new Unit();
                for(int k = 0 ; k < 3 ; k++){
                    //u.addMul(t1.matrix[i][k], t2.matrix[k][j]);
                    u.addMul(t1.matrix[k][j], t2.matrix[i][k]);
                }
                m[i][j] = u;
            }
        }
        Transform result = new Transform(m);
        return result;
    }

    public static Unit[] transformPoint(Unit x, Unit y, Transform t){
        Unit[] result = new Unit[2];
        result[0] = new Unit(t.matrix[0][2]);
        result[0].addMul(t.matrix[0][0], x);
        result[0].addMul(t.matrix[0][1], y);

        result[1] = new Unit(t.matrix[1][2]);
        result[1].addMul(t.matrix[1][0], x);
        result[1].addMul(t.matrix[1][1], y);
        return result;
    }
}
