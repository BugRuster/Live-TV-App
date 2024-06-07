package com.app.dbug.model;

import java.util.Date;

public class SettingModle {

    public Data data;


    public class Data{
        public int id;
        public String privacy_url;
        public String settings;
        public String fcm_api;
        public String one_signalID;
        public String support_email;
        public String token;
        public String home_dialogue_link;
        public String visibility_home_dialogue;
        public Object created_at;
        public Date updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrivacy_url() {
            return privacy_url;
        }

        public void setPrivacy_url(String privacy_url) {
            this.privacy_url = privacy_url;
        }

        public String getSettings() {
            return settings;
        }

        public void setSettings(String settings) {
            this.settings = settings;
        }

        public String getFcm_api() {
            return fcm_api;
        }

        public void setFcm_api(String fcm_api) {
            this.fcm_api = fcm_api;
        }

        public String getOne_signalID() {
            return one_signalID;
        }

        public void setOne_signalID(String one_signalID) {
            this.one_signalID = one_signalID;
        }

        public String getSupport_email() {
            return support_email;
        }

        public void setSupport_email(String support_email) {
            this.support_email = support_email;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHome_dialogue_link() {
            return home_dialogue_link;
        }

        public void setHome_dialogue_link(String home_dialogue_link) {
            this.home_dialogue_link = home_dialogue_link;
        }

        public String getVisibility_home_dialogue() {
            return visibility_home_dialogue;
        }

        public void setVisibility_home_dialogue(String visibility_home_dialogue) {
            this.visibility_home_dialogue = visibility_home_dialogue;
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
    }
}
