package com.app.dbug.model;

import java.util.Date;

public class Ads {
    public boolean success;
    public Data data;

    public class Data{

        public int id;
        public String admob_inter;
        public String admob_banner;
        public String admob_native;
        public String admob_appopen;
        public String admob_reward;
        public String fb_inter;
        public String fb_banner;
        public String fb_native;
        public String fb_reward;
        public String appnex_inter;
        public String appnex_banner;
        public String startapp_app_id;
        public int add_count;
        public int add_count_native;
        public int addtype_id;
        public Object created_at;
        public Date updated_at;

        public String getStartapp_app_id() {
            return startapp_app_id;
        }

        public void setStartapp_app_id(String startapp_app_id) {
            this.startapp_app_id = startapp_app_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAdmob_inter() {
            return admob_inter;
        }

        public void setAdmob_inter(String admob_inter) {
            this.admob_inter = admob_inter;
        }

        public String getAdmob_banner() {
            return admob_banner;
        }

        public void setAdmob_banner(String admob_banner) {
            this.admob_banner = admob_banner;
        }

        public String getAdmob_native() {
            return admob_native;
        }

        public void setAdmob_native(String admob_native) {
            this.admob_native = admob_native;
        }

        public String getAdmob_appopen() {
            return admob_appopen;
        }

        public void setAdmob_appopen(String admob_appopen) {
            this.admob_appopen = admob_appopen;
        }

        public String getAdmob_reward() {
            return admob_reward;
        }

        public void setAdmob_reward(String admob_reward) {
            this.admob_reward = admob_reward;
        }

        public String getFb_inter() {
            return fb_inter;
        }

        public void setFb_inter(String fb_inter) {
            this.fb_inter = fb_inter;
        }

        public String getFb_banner() {
            return fb_banner;
        }

        public void setFb_banner(String fb_banner) {
            this.fb_banner = fb_banner;
        }

        public String getFb_native() {
            return fb_native;
        }

        public void setFb_native(String fb_native) {
            this.fb_native = fb_native;
        }

        public String getFb_reward() {
            return fb_reward;
        }

        public void setFb_reward(String fb_reward) {
            this.fb_reward = fb_reward;
        }

        public String getAppnex_inter() {
            return appnex_inter;
        }

        public void setAppnex_inter(String appnex_inter) {
            this.appnex_inter = appnex_inter;
        }

        public String getAppnex_banner() {
            return appnex_banner;
        }

        public void setAppnex_banner(String appnex_banner) {
            this.appnex_banner = appnex_banner;
        }


        public int getAdd_count() {
            return add_count;
        }

        public void setAdd_count(int add_count) {
            this.add_count = add_count;
        }

        public int getAddtype_id() {
            return addtype_id;
        }

        public void setAddtype_id(int addtype_id) {
            this.addtype_id = addtype_id;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Date getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Date updated_at) {
            this.updated_at = updated_at;
        }

        public int getAdd_count_native() {
            return add_count_native;
        }

        public void setAdd_count_native(int add_count_native) {
            this.add_count_native = add_count_native;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
