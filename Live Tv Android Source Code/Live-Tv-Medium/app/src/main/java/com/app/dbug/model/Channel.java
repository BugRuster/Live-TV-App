package com.app.dbug.model;

import java.util.Date;
import java.util.List;

public class Channel {

    public boolean success;
    public String message;
    public List<Datum> data;

    public static class Datum{
        public int id;
        public int cat_id;
        public int url_id;
        public String name;
        public String image;
        public String description;
        public String url;
        public String user_agent;
        public String token;
        public String extra;
        public Date created_at;
        public Date updated_at;
        public String cat_name;
        public int category_type;

        public Datum(int id, int cat_id, int url_id, String name, String image, String description, String url, String user_agent, String token, String extra, Date created_at, Date updated_at, String cat_name, int category_type) {
            this.id = id;
            this.cat_id = cat_id;
            this.url_id = url_id;
            this.name = name;
            this.image = image;
            this.description = description;
            this.url = url;
            this.user_agent = user_agent;
            this.token = token;
            this.extra = extra;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.cat_name = cat_name;
            this.category_type = category_type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCat_id() {
            return cat_id;
        }

        public void setCat_id(int cat_id) {
            this.cat_id = cat_id;
        }

        public int getUrl_id() {
            return url_id;
        }

        public void setUrl_id(int url_id) {
            this.url_id = url_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUser_agent() {
            return user_agent;
        }

        public void setUser_agent(String user_agent) {
            this.user_agent = user_agent;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public Date getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Date created_at) {
            this.created_at = created_at;
        }

        public Date getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Date updated_at) {
            this.updated_at = updated_at;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public int getCategory_type() {
            return category_type;
        }

        public void setCategory_type(int category_type) {
            this.category_type = category_type;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
