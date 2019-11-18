package com.cursoandroid.oliveiragabriel.finddog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BreedsModel {

   @SerializedName("message")
   private List<String> messageList;
   @SerializedName("status")
   private String status;


   public List<String> getMessageList() {
      return messageList;
   }

   public void setMessageList(List<String> messageList) {
      this.messageList = messageList;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}