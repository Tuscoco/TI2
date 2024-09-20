package com.ti2.cc;

public class User {
    
    private String name;
    private String email;
    private int age;


    public User(){}
    
    
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Override
    public String toString(){
        return "User{ Name= " + name + " | Email= " + email + "| Age= " + age + "}";
    }

}
