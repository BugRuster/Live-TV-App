package com.app.dbug.model;

import java.util.Date;
import java.util.List;

public class Category {
    public boolean success;
    public List<Datum> data;

    public class Datum{
        public int id;
        public String cat_name;
        public String image;
        public int category_type;
        public String pass;
        public Date created_at;
        public Date updated_at;


    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}


