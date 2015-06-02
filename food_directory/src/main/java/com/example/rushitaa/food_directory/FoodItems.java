package com.example.rushitaa.food_directory;

public class FoodItems {

        protected String fName;
        protected String fSrc;
        protected String fDesc;
    FoodItems( String n,String a,String d)
        {
            fName = n;
            fSrc= a;
            fDesc = d;

        }
        public String getName(){
            return fName;
        }
        public String getSrc() {
            return fSrc;
        }
        public String getDesc() { return fDesc; }

    }

